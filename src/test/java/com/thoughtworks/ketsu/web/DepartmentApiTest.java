package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareDepartmentWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(ApiTestRunner.class)
public class DepartmentApiTest extends ApiSupport {
    @Inject
    DepartmentRepo departmentRepo;

    String baseUrl;
    private Department department;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        department = prepareDepartmentWithDefaultInfo(departmentRepo);
        baseUrl = "/departments/" + department.getId();
    }

    @Test
    public void should_200_when_get_one() throws Exception {
        final Response response = get(baseUrl);

        assertThat(response.getStatus(), is(200));
        assertThat(Long.valueOf(response.readEntity(Map.class).get("id").toString()), is(department.getId()));

    }

}
