package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

@Path("departments")
public class DepartmentsApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> info,
                               @Context DepartmentRepo departmentRepo,
                               @Context Routes routes) {

        validate(info, all(
                fieldNotEmpty("name")
        ));

        Department save = departmentRepo.save(new Department(info.get("name").toString()));
        return Response.created(routes.resourceUrl(routes.DEPARTMENT_RESOURCE, save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getAll(@Context DepartmentRepo departmentRepo) {
        return new ArrayList<>();
    }

    @Path("{id}")
    public DepartmentApi getUser(@PathParam("id") long id,
                           @Context DepartmentRepo departmentRepo) {
        return departmentRepo.ofId(id)
                .map(DepartmentApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
