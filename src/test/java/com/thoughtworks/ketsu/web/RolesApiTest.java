package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.roles.RoleRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.roleJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.prepareRoleWithDefaultInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class RolesApiTest extends ApiSupport {
    @Inject
    RoleRepo roleRepo;

    String baseUrl = "/roles";

    @Test
    public void should_201_when_import_role_success() throws Exception {
        final Response post = post(baseUrl, roleJsonForTest());

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toString().matches(".*roles/\\d+$"), is(true));
    }


    @Test
    public void should_400_when_import_role_given_incomplete_input() throws Exception {
        final Response post = post(baseUrl, new HashMap());

        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_200_when_get_all() throws Exception {
        prepareRoleWithDefaultInfo(roleRepo);
        final Response response = get(baseUrl);

        assertThat(response.getStatus(), is(200));
        Map res = response.readEntity(Map.class);
        assertThat(res.get("totalCount"), is(1));
        assertThat(((List)res.get("roles")).size(), is(1));
    }
}
