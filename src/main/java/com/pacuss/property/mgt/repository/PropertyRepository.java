package com.pacuss.property.mgt.repository;

import com.pacuss.property.mgt.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwnerId(Long userId);
}
