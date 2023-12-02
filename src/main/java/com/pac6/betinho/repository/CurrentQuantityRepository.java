package com.pac6.betinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac6.betinho.model.CurrentQuantity;

@Repository
public interface CurrentQuantityRepository extends JpaRepository<CurrentQuantity, Long> {

}
