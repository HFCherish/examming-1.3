package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
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
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(ApiTestRunner.class)
public class EmployeeApiTest extends ApiSupport {
    @Inject
    EmployeeRepo employeeRepo;

    @Inject
    DepartmentRepo departmentRepo;

    @Inject
    RoleRepo roleRepo;

    private Employee employee;
    private Department department;
    private Role role;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        department = prepareDepartmentWithDefaultInfo(departmentRepo);
        role = prepareRoleWithDefaultInfo(roleRepo);
        employee = prepareEmployeeWithDefaultInfo(employeeRepo, department, role);
    }

    private String getBaseUrl(long employeeId) {
        return "/employees/" + employeeId;
    }

    @Test
    public void should_200_when_get_one(){
        final Response response = get(getBaseUrl(employee.getId()));

        assertThat(response.getStatus(), is(200));
        assertThat(Long.valueOf(response.readEntity(Map.class).get("id").toString()), is(employee.getId()));
    }

    @Test
    public void should_404_when_get_one(){
        final Response response = get(getBaseUrl(1l));

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_204_when_udpate(){
        Map<String, Object> input = employeeJsonForTest(department.getId(), role.getId());
        input.replace("name", "change");

        final Response response = put(getBaseUrl(employee.getId()), input);

        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void should_204_when_delete(){

        final Response response = delete(getBaseUrl(employee.getId()));

        assertThat(response.getStatus(), is(204));
    }
}
