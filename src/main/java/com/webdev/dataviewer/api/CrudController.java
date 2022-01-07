package com.webdev.dataviewer.api;

import com.webdev.dataviewer.model.api.ConnectionApiModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudController<T> {

    @PostMapping
    public T create(@RequestBody T requestModel);

    @GetMapping("/{id}")
    public T getById(@PathVariable Integer id);

    @GetMapping
    public List<T> getAll();

    @PutMapping("/{id}")
    public T update(@PathVariable Integer id, @RequestBody T requestModel);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id);
}
