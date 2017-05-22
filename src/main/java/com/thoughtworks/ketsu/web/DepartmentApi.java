package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.all;
import static com.thoughtworks.ketsu.web.validators.Validators.fieldNotEmpty;
import static com.thoughtworks.ketsu.web.validators.Validators.validate;

public class DepartmentApi {


    private Department department;

    public DepartmentApi(Department department) {

        this.department = department;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Department getDepartment() {
        return department;
    }


    @DELETE
    public Response delete(@Context DepartmentRepo departmentRepo) {
        departmentRepo.delete(department.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String,Object> info,
                                    @Context DepartmentRepo departmentRepo) {

        validate(info, all(
                fieldNotEmpty("name")
        ));

        departmentRepo.update(department.getId(), new Department(info.get("name").toString()));
        return Response.noContent().build();
    }
}
