package com.htv3.htv3onlinemusic.service;

import java.util.Optional;

public interface GenericService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
