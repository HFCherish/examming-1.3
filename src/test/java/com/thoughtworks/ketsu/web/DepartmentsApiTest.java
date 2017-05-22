package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;

import static com.thoughtworks.ketsu.support.TestHelper.departmentJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class DepartmentsApiTest extends ApiSupport {
    @Inject
    DepartmentRepo departmentRepo;

    String baseUrl = "/departments";

    @Test
    public void should_201_when_import_department_success() throws Exception {
        final Response post = post(baseUrl, departmentJsonForTest());

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toString().matches(".*departments/\\d+$"), is(true));
    }


    @Test
    public void should_400_when_import_department_given_incomplete_input() throws Exception {
        final Response post = post(baseUrl, new HashMap());

        assertThat(post.getStatus(), is(400));
    }
}
