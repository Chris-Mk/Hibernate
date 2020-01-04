package com.mkolongo.judgesystem.repositories;

import com.mkolongo.judgesystem.domain.models.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    Strategy findStrategyByName(String name);
}
