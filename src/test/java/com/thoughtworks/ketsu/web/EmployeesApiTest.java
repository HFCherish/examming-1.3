package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class EmployeesApiTest extends ApiSupport {
    @Inject
    EmployeeRepo employeeRepo;

    @Inject
    DepartmentRepo departmentRepo;

    @Inject
    RoleRepo roleRepo;

    String baseUrl = "/employees";
    private Department department;
    private Role role;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        department = prepareDepartmentWithDefaultInfo(departmentRepo);
        role = prepareRoleWithDefaultInfo(roleRepo);
    }

    @Test
    public void should_201_when_import_employee_success() throws Exception {
        final Response post = post(baseUrl, employeeJsonForTest(department.getId(), role.getId()));

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toString().matches(".*employees/\\d+$"), is(true));
    }


    @Test
    public void should_400_when_import_employee_given_incomplete_input() throws Exception {
        final Response post = post(baseUrl, new HashMap());

        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_400_when_import_employee_given_invalid_role_id() throws Exception {
        Map<String, Object> withInvalidRoleId = employeeJsonForTest(department.getId(), 1l);
        final Response post = post(baseUrl, withInvalidRoleId);

        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_200_when_get_all() throws Exception {
        prepareEmployeeWithDefaultInfo(employeeRepo, department, role);
        final Response response = get(baseUrl);

        assertThat(response.getStatus(), is(200));
        Map res = response.readEntity(Map.class);
        assertThat(res.get("totalCount"), is(1));
        assertThat(((List)res.get("employees")).size(), is(1));
    }
}
