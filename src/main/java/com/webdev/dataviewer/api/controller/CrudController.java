package com.webdev.dataviewer.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudController<T> {

    @PostMapping
    T create(@RequestBody T requestModel);

    @GetMapping("/{id}")
    T findById(@PathVariable Integer id);

    @GetMapping
    List<T> findAll();

    @PutMapping("/{id}")
    T update(@PathVariable Integer id, @RequestBody T requestModel);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id);
}
