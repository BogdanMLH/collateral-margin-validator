package com.euroclear.collateralmarginvalidator.repository;

import com.euroclear.collateralmarginvalidator.model.MarginCallEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarginCallRepository extends JpaRepository<MarginCallEvent, Long> {
}
