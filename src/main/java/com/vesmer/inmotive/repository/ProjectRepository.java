package com.vesmer.inmotive.repository;

import com.vesmer.inmotive.model.Project;
import com.vesmer.inmotive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Collection<Project> findByUser(User user);
}
