package io.learning.demo.service.core;

import java.util.Collection;

public interface GenericService<T, U> {

    Collection<T> findAll();

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void delete(U u);
}
