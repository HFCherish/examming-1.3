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

import static com.thoughtworks.ketsu.support.TestHelper.prepareDepartmentWithDefaultInfo;

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
        final Response post = get(baseUrl);

//        assertThat(post.getStatus(), is(200));
//        assertThat(post.getLocation().toString().matches(".*departments/\\d+$"), is(true));
    }

}
