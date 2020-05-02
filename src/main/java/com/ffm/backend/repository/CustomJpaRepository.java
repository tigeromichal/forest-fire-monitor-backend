package com.ffm.backend.repository;

import com.ffm.backend.config.transaction.MandatoryTransactions;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@MandatoryTransactions
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    @Override
    List<T> findAll();

    @Override
    List<T> findAll(Sort sort);

    @Override
    List<T> findAllById(Iterable<ID> iterable);

    @Override
    <S extends T> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();

    @Override
    <S extends T> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<T> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    T getOne(ID id);

    @Override
    <S extends T> List<S> findAll(Example<S> example);

    @Override
    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<T> findAll(Pageable pageable);

    @Override
    <S extends T> S save(S s);

    @Override
    Optional<T> findById(ID id);

    @Override
    boolean existsById(ID id);

    @Override
    long count();

    @Override
    void deleteById(ID id);

    @Override
    void delete(T t);

    @Override
    void deleteAll(Iterable<? extends T> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends T> Optional<S> findOne(Example<S> example);

    @Override
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends T> long count(Example<S> example);

    @Override
    <S extends T> boolean exists(Example<S> example);
}
