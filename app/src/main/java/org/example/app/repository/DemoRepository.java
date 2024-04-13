package org.example.app.repository;

import org.example.app.entity.Demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends CrudRepository<Demo, Long> {
    Optional<Demo> findById(Long id);
}