package com.thoughtworks.ketsu.web.validators;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Validators {
    public interface Validator {
        Optional<String> validate(Map<String, Object> info);
    }

    public static void validate(Map<String, Object> info, Validator validator) {
        Optional<String> res = validator.validate(info);
        if(res.isPresent())
            throw new BadRequestException(res.get());
    }

    public static Validator all(Validator... validators) {
        return info -> {
            List<String> errors = Arrays.asList(validators).stream()
                    .map(validator -> validator.validate(info))
                    .filter(error -> error.isPresent())
                    .map(error -> error.get())
                    .collect(Collectors.toList());

            return errors.size() > 0 ?
                    Optional.of(StringUtils.join(errors, "\n")) :
                    Optional.empty();
        };
    }

    public static Validator fieldNotEmpty(String field) {
        return info -> info.getOrDefault(field, "").toString().isEmpty() ?
                Optional.of(fieldErrorMessage(field, "cannot be empty").toString()) :
                Optional.empty();
    }

    public static Validator fieldIsEnum(String field, Class enumClass) {
        return info -> EnumUtils.isValidEnum(enumClass, info.getOrDefault(field, "").toString().toUpperCase()) ?
                Optional.empty():
                Optional.of(fieldErrorMessage(field, "is not valid enum. valid values are: " + EnumUtils.getEnumList(enumClass)).toString());
    }

    private static Map<String, Object> fieldErrorMessage(String field, String message) {
        return new HashMap(){{
            put("field", field);
            put("message", message);
        }};
    }
}
