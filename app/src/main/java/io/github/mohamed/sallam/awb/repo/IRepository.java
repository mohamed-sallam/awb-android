package io.github.mohamed.sallam.awb.repo;

public interface IRepository<T extends IAggregateRoot> {
    void insert(T entity);
}
