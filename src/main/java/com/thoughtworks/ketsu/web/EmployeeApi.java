package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;
import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

public class EmployeeApi {


    private Employee employee;

    public EmployeeApi(Employee employee) {

        this.employee = employee;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee() {
        return employee;
    }


    @DELETE
    public Response delete(@Context EmployeeRepo employeeRepo) {
        employeeRepo.delete(employee.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> info,
                           @Context DepartmentRepo departmentRepo,
                           @Context RoleRepo roleRepo,
                           @Context EmployeeRepo employeeRepo) {

        validate(info, all(
                fieldNotEmpty("name"),
                fieldNotEmpty("department_id"),
                fieldNotEmpty("role_id"),
                fieldNotEmpty("gender"),
                fieldIsEnum("gender", Gender.class)
        ));

        Department department = departmentRepo.ofId(Long.valueOf(info.get("department_id").toString())).orElseThrow(() -> new BadRequestException("department not exists"));
        Role role = roleRepo.ofId(Long.valueOf(info.get("role_id").toString())).orElseThrow(() -> new BadRequestException("role not exists"));
        employeeRepo.update(employee.getId(), new Employee(info.get("name").toString(),
                department,
                role,
                Gender.valueOf(info.get("gender").toString().toUpperCase())
        ));

        return Response.noContent().build();
    }
}
