package com.pavikumbhar.jpa.repository;



import com.pavikumbhar.jpa.model.OperatingSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long>, JpaSpecificationExecutor<OperatingSystem> {
}