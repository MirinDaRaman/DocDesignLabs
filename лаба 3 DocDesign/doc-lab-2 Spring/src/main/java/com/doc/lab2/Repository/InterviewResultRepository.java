package com.doc.lab2.Repository;

import com.doc.lab2.domain.CommisionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewResultRepository extends JpaRepository<CommisionPlan, Long> {

}
