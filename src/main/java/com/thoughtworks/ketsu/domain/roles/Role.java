package com.thoughtworks.ketsu.domain.roles;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Role implements Record {
    private long id;
    private String title;

    private Role() {
    }

    public Role(String title) {
        this.id = IdGenerator.next();
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("id", id);
            put("title", title);
            put("role_url", routes.resourceUrl(routes.ROLE_RESOURCE, id));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
