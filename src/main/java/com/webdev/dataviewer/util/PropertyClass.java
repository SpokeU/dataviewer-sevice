package com.webdev.dataviewer.util;

import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO DRAFT POC IMPLEMENTATION!!! Rewrite
 */
public abstract class PropertyClass implements Map<String, Object> {

    @Override
    public int size() {
        return FieldUtils.getAllFields(this.getClass()).length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @SneakyThrows
    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @SneakyThrows
    @Override
    public Object get(Object key) {
        return getField(key.toString()).get(this);
    }

    @SneakyThrows
    @Override
    public Object put(String key, Object value) {
        Field field = getField(key);
        field.set(this, value);
        return value;
    }

    @SneakyThrows
    @Override
    public Object remove(Object key) {
        Field field = getField(key.toString());
        Object value = field.get(this);
        field.set(this, null);
        return value;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return FieldUtils.getAllFieldsList(this.getClass()).stream().map(f -> f.getName()).collect(Collectors.toSet());
    }

    @Override
    public Collection values() {
        return FieldUtils.getAllFieldsList(this.getClass()).stream().map(this::getFieldValue).collect(Collectors.toSet());
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }

    private Field getField(String name){
        Field field = FieldUtils.getField(this.getClass(), name, true);
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    private Object getFieldValue(Field f){
        return f.get(this);
    }
}
