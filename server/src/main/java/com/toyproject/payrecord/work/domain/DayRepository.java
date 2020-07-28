package com.toyproject.payrecord.work.domain;

import com.toyproject.payrecord.work.domain.keys.DayId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, DayId> {

}
