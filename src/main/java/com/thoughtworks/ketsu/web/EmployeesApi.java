package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;
import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

@Path("employees")
public class EmployeesApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> info,
                           @Context EmployeeRepo employeeRepo,
                           @Context DepartmentRepo departmentRepo,
                           @Context RoleRepo roleRepo,
                           @Context Routes routes) {

        validate(info, all(
                fieldNotEmpty("name"),
                fieldNotEmpty("department_id"),
                fieldNotEmpty("role_id"),
                fieldNotEmpty("gender"),
                fieldIsEnum("gender", Gender.class)
        ));

        Department department = departmentRepo.ofId(Long.valueOf(info.get("department_id").toString())).orElseThrow(() -> new BadRequestException("department not exists"));
        Role role = roleRepo.ofId(Long.valueOf(info.get("role_id").toString())).orElseThrow(() -> new BadRequestException("role not exists"));
        Employee save = employeeRepo.save(new Employee(info.get("name").toString(),
                department,
                role,
                Gender.valueOf(info.get("gender").toString().toUpperCase())
                ));
        return Response.created(routes.resourceUrl(routes.EMPLOYEE_RESOURCE, save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll(@Context EmployeeRepo employeeRepo) {
        return employeeRepo.findAll();
    }

    @Path("{id}")
    public EmployeeApi getOne(@PathParam("id") long id,
                           @Context EmployeeRepo employeeRepo) {
        return employeeRepo.ofId(id)
                .map(EmployeeApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
