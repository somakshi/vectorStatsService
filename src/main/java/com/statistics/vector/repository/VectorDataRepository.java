package com.statistics.vector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statistics.vector.entity.VectorData;

/**
 * Repository for the VECTOR_DATA table
 *
 */
public interface VectorDataRepository extends JpaRepository<VectorData, Long> {

}
