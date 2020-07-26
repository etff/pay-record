package com.toyproject.payrecord.work.domain;

import com.toyproject.payrecord.work.domain.keys.DayId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, DayId> {

}
