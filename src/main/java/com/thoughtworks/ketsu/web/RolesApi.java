package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

@Path("roles")
public class RolesApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> info,
                               @Context RoleRepo roleRepo,
                               @Context Routes routes) {

        validate(info, all(
                fieldNotEmpty("title")
        ));

        Role save = roleRepo.save(new Role(info.get("title").toString()));
        return Response.created(routes.resourceUrl(routes.ROLE_RESOURCE, save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getAll(@Context RoleRepo roleRepo) {
        return roleRepo.findAll();
    }

    @Path("{id}")
    public RoleApi getOne(@PathParam("id") long id,
                           @Context RoleRepo roleRepo) {
        return roleRepo.ofId(id)
                .map(RoleApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
