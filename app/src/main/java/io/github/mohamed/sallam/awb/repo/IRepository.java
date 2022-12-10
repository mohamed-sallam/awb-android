package io.github.mohamed.sallam.awb.repo;

public interface IRepository<T extends IAggregateRoot, k> {
    void insert(T entity);
    void delete(k id);
}
