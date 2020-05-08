package com.doc.lab2.Repository;

import com.doc.lab2.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Salary, Long> {

}
