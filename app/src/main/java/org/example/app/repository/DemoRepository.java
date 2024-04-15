package org.example.app.repository;

import org.example.app.entity.Demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends CrudRepository<Demo, Long> {
}