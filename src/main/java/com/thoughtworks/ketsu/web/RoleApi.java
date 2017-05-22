package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

public class RoleApi {


    private Role role;

    public RoleApi(Role role) {

        this.role = role;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Role getRole() {
        return role;
    }


    @DELETE
    public Response delete(@Context RoleRepo roleRepo) {
        roleRepo.delete(role.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String,Object> info,
                                    @Context RoleRepo roleRepo) {

        validate(info, all(
                fieldNotEmpty("title")
        ));

        roleRepo.update(role.getId(), new Role(info.get("title").toString()));
        return Response.noContent().build();
    }
}
