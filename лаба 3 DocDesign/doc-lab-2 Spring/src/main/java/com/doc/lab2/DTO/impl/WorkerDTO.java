package com.doc.lab2.DTO.impl;

import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.Salary;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class WorkerDTO extends DTO<Worker> {
    public WorkerDTO(Worker worker, Link link) throws NoSuchInterviewResultException, NoSuchVacancyException, NoSuchCandidateException {
        super(worker, link);
//        add(linkTo(methodOn(ProjectController.class).getProjectByArtistId(getEntity().getId())).withRel("projects"));
    }

    public Long getWorkerId() {
        return getEntity().getId();
    }

    public String getWorkerName() {
        return getEntity().getName();
    }

    public String getWorkerSurname() {
        return getEntity().getSurname();
    }

    public String getWorkerunit_salary() {
        return getEntity().getUnit_salary();
    }

    public Salary getSalaries() {
        return getEntity().getSalaryByWorkers();
    }


}
