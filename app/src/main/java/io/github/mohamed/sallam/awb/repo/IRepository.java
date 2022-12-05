package io.github.mohamed.sallam.awb.repo;

import java.util.UUID;

public interface IRepository<T> {
    void insert(T entity);
}
