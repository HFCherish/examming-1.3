package com.thoughtworks.ketsu.web;

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

import static com.thoughtworks.ketsu.support.TestHelper.roleJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.prepareRoleWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(ApiTestRunner.class)
public class RoleApiTest extends ApiSupport {
    @Inject
    RoleRepo roleRepo;

    private Role role;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        role = prepareRoleWithDefaultInfo(roleRepo);
    }

    private String getBaseUrl(long roleId) {
        return "/roles/" + roleId;
    }

    @Test
    public void should_200_when_get_one(){
        final Response response = get(getBaseUrl(role.getId()));

        assertThat(response.getStatus(), is(200));
        assertThat(Long.valueOf(response.readEntity(Map.class).get("id").toString()), is(role.getId()));
    }

    @Test
    public void should_404_when_get_one(){
        final Response response = get(getBaseUrl(1l));

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_204_when_udpate(){
        Map<String, Object> input = roleJsonForTest();
        input.replace("name", "change");

        final Response response = put(getBaseUrl(role.getId()), input);

        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void should_204_when_delete(){

        final Response response = delete(getBaseUrl(role.getId()));

        assertThat(response.getStatus(), is(204));
    }
}
