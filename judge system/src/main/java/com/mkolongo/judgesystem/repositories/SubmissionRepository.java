package com.mkolongo.judgesystem.repositories;

import com.mkolongo.judgesystem.domain.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
