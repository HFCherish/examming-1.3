package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
