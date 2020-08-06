package com.pollit.server.util;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;


public class Crud {
    //TODO: Not readty for object types, just for primitive types
    @Transactional
    public void update(Object item, HashMap req) {
        req.forEach((key, value) -> {
            try {
                this.setValue(item,key,value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     *
     * @param item: the entity that will be updated
     * @param key: field name of the item e.g. "name", "description"
     * @param value: the value that will be set to the item
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Transactional
    public void setValue(Object item, Object key, Object value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setMethod =
                item.getClass().getMethod("set" + ("" + key.toString().charAt(0)).toUpperCase() + key.toString().substring(1),
                        item.getClass().getDeclaredField(key.toString()).getType()
                );
        if (value != null) {
            String parameterTypeName = setMethod.getParameterTypes()[0].getName();
            if (parameterTypeName.equals("java.sql.Timestamp")) {
                try {
                    setMethod.invoke(item, Timestamp.from(OffsetDateTime.parse(value + "").toInstant()));
                } catch (DateTimeParseException e) {
                    setMethod.invoke(item, Timestamp.valueOf(((String) value).replace("T" ," ") + ":00"));
                }
            } else if (parameterTypeName.equals("int") || parameterTypeName.equals("java.lang.Integer")) {
                setMethod.invoke(item, Integer.parseInt("" + value));
            } else if (parameterTypeName.equals("float") || parameterTypeName.equals("java.lang.Float")) {
                setMethod.invoke(item, Float.parseFloat("" + value));
            } else if (parameterTypeName.equals("java.sql.Date")) {
                setMethod.invoke(item, Date.valueOf(value + ""));
            } else if (parameterTypeName.equals("java.sql.Time")) {
                setMethod.invoke(item, Time.valueOf(value + ":00"));
            } else {
                setMethod.invoke(item, value);
            }
        }

    }
}
