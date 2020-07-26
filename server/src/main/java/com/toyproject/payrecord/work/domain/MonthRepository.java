package com.toyproject.payrecord.work.domain;

import com.toyproject.payrecord.work.domain.keys.MonthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<Month, MonthId> {
}
