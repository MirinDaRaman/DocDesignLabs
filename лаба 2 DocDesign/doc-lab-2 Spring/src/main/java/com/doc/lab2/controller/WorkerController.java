package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.DTO.impl.WorkerDTO;
import com.doc.lab2.domain.Worker;
import com.doc.lab2.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class WorkerController {
    @Autowired
    WorkerService WorkerService;

    // get worker by class id
    @GetMapping(value = "/api/worker/salary/{salary_id}")
    public ResponseEntity<List<WorkerDTO>> getWorkersBySalariesID(@PathVariable Long salary_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        Set<Worker> workerSet = WorkerService.getWorkerBySalaryId(salary_id);

        Link link = linkTo(methodOn(WorkerController.class).getAllWorkers()).withSelfRel();

        List<WorkerDTO> workersDTO = new ArrayList<>();
        for (Worker entity : workerSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            WorkerDTO dto = new WorkerDTO(entity, selfLink);
            workersDTO.add(dto);
        }

        return new ResponseEntity<>(workersDTO, HttpStatus.OK);
    }

    // get Worker
    @GetMapping(value = "/api/worker/{worker_id}")
    public ResponseEntity<WorkerDTO> getWorker(@PathVariable Long worker_id) throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        Worker worker = WorkerService.getWorker(worker_id);
        Link link = linkTo(methodOn(WorkerController.class).getWorker(worker_id)).withSelfRel();

        WorkerDTO workerDTO = new WorkerDTO(worker, link);

        return new ResponseEntity<>(workerDTO, HttpStatus.OK);
    }

    // get all Workers
    @GetMapping(value = "/api/worker")
    public ResponseEntity<Set<WorkerDTO>> getAllWorkers() throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        List<Worker> allWorkers = WorkerService.getAllWorkers();
        Link link = linkTo(methodOn(WorkerController.class).getAllWorkers()).withSelfRel();

        Set<WorkerDTO> workerDTOS = new HashSet<>();
        for (Worker entity : allWorkers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            WorkerDTO dto = new WorkerDTO(entity, selfLink);
            workerDTOS.add(dto);
        }

        return new ResponseEntity<>(workerDTOS, HttpStatus.OK);
    }

    // add Worker
    @PostMapping(value = "/api/worker/salary/{salary_id}")
    public ResponseEntity<WorkerDTO> addWorker(@RequestBody Worker newWorker, @PathVariable Long salary_id)
            throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {

        WorkerService.createWorker(newWorker, salary_id);
        Link link = linkTo(methodOn(WorkerController.class).getWorker(newWorker.getId())).withSelfRel();

        WorkerDTO workerDTO = new WorkerDTO(newWorker, link);

        return new ResponseEntity<>(workerDTO, HttpStatus.CREATED);
    }

    //update WOrker
    @PutMapping(value = "/api/worker/{worker_id}/salary/{salary_id}")
    public ResponseEntity<WorkerDTO> updateWorker(@RequestBody Worker uWorker,
                                                  @PathVariable Long worker_id, @PathVariable Long salary_id)
            throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        WorkerService.updateWorker(uWorker, worker_id, salary_id);
        Worker worker = WorkerService.getWorker(worker_id);
        Link link = linkTo(methodOn(WorkerController.class).getWorker(worker_id)).withSelfRel();

        WorkerDTO workerDTO = new WorkerDTO(worker, link);

        return new ResponseEntity<>(workerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/worker/{worker_id}")
    public ResponseEntity deleteWorker(@PathVariable Long worker_id) throws NoSuchCandidateException, ExistsInterviewResultForCandidatesException, ExistsInterviewResultForCandidatesException {
        WorkerService.deleteWorker(worker_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

