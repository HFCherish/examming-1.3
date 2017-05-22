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

    private Department department;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        department = prepareDepartmentWithDefaultInfo(departmentRepo);
    }

    private String getBaseUrl(long departmentId) {
        return "/departments/" + departmentId;
    }

    @Test
    public void should_200_when_get_one() throws Exception {
        final Response response = get(getBaseUrl(department.getId()));

        assertThat(response.getStatus(), is(200));
        assertThat(Long.valueOf(response.readEntity(Map.class).get("id").toString()), is(department.getId()));
    }

    @Test
    public void should_404_when_get_one() throws Exception {
        final Response response = get(getBaseUrl(1l));

        assertThat(response.getStatus(), is(404));
    }

}
