package com.smartcode.audit.repository;

import com.smartcode.audit.model.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {

}