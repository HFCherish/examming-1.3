package com.thoughtworks.ketsu.domain.employees;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Employee implements Record {
    long id;
    Department department;
    Role role;
    Gender gender;
    String name;

    private Employee() {

    }

    public Employee(String name, Department department, Role role, Gender gender) {
        this.id = IdGenerator.next();
        this.department = department;
        this.role = role;
        this.gender = gender;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Role getRole() {
        return role;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("id", id);
            put("name", name);
            put("gender", gender.toString());
            put("department_id", department.getId());
            put("role_id", role.getId());
            put("employee_url", routes.resourceUrl(routes.EMPLOYEE_RESOURCE, id));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
