package com.mkolongo.judgesystem.repositories;

import com.mkolongo.judgesystem.domain.models.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
}
