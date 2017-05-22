package com.thoughtworks.ketsu.domain.departments;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Department implements Record {
    private long id;
    private String name;

    private Department() {
    }

    public Department(String name) {
        this.id = IdGenerator.next();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("id", id);
            put("name", name);
            put("department_url", routes.resourceUrl(routes.DEPARTMENT_RESOURCE, id));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
