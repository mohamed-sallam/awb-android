package io.github.mohamed.sallam.awb.repo;

import java.util.UUID;

public interface IRepository<T extends IAggregateRoot, k> {
    void insert(T entity);
    void delete(k id);
}
