package com.doc.lab2.DTO.impl;

import com.doc.lab2.controller.WorkerController;
import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.Salary;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class SalaryDTO extends DTO<Salary> {
    public SalaryDTO(Salary vacancy, Link link) throws NoSuchVacancyException, NoSuchInterviewResultException, NoSuchCandidateException {
        super(vacancy, link);
        add(ControllerLinkBuilder.linkTo(methodOn(WorkerController.class).getWorkersBySalariesID(getEntity().getId())).withRel("candidates"));
    }

    public Long getVacancyId() {
        return getEntity().getId();
    }

    public String getVacancyName() {
        return getEntity().getName();
    }

    public Integer getVacancySalary() {
        return getEntity().getSalary();
    }

    public String getVacancyDescription() {
        return getEntity().getDescription();
    }

    public Set<Worker> getCandidates() {
        return getEntity().getWorkers();
    }
}
