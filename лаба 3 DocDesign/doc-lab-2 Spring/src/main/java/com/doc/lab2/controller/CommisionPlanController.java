package com.doc.lab2.controller;

import com.doc.lab2.exceptions.ExistsCandidatesForInterviewResultException;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.impl.CommisionPlanDTO;
import com.doc.lab2.domain.CommisionPlan;
import com.doc.lab2.service.CommisionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CommisionPlanController {
    @Autowired
    CommisionPlanService commisionPlanService;

    @Autowired
    WorkerController workerController;

    @GetMapping(value = "/api/commision_plan/{commision_plan_id}")
    public ResponseEntity<CommisionPlanDTO> getCommisionPlan(@PathVariable Long commision_plan_id) throws NoSuchInterviewResultException, NoSuchCandidateException {
        CommisionPlan commisionPlan = commisionPlanService.getCommisionPlan(commision_plan_id);
        Link link = linkTo(methodOn(CommisionPlanController.class).getCommisionPlan(commision_plan_id)).withSelfRel();

        CommisionPlanDTO commisionPlanDTO = new CommisionPlanDTO(commisionPlan, link);

        return new ResponseEntity<>(commisionPlanDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/commision_plan")
    public ResponseEntity<Set<CommisionPlanDTO>> getAllCommisionPlans() throws NoSuchInterviewResultException, NoSuchCandidateException {
        List<CommisionPlan> commisionPlans = commisionPlanService.getAllCommisionPlans();
        Link link = linkTo(methodOn(CommisionPlanController.class).getAllCommisionPlans()).withSelfRel();

        Set<CommisionPlanDTO> commisionPlanDTOS = new HashSet<>();
        for (CommisionPlan entity : commisionPlans) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CommisionPlanDTO dto = new CommisionPlanDTO(entity, selfLink);
            commisionPlanDTOS.add(dto);
        }

        return new ResponseEntity<>(commisionPlanDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/api/commision_plan")
    public ResponseEntity<CommisionPlanDTO> addCommisionPlan(@RequestBody CommisionPlan newCommisionPlan) throws NoSuchInterviewResultException, NoSuchCandidateException {
        commisionPlanService.createCommisionPlan(newCommisionPlan);
        Link link = linkTo(methodOn(CommisionPlanController.class).getCommisionPlan(newCommisionPlan.getId())).withSelfRel();

        CommisionPlanDTO commisionPlanDTO = new CommisionPlanDTO(newCommisionPlan, link);

        return new ResponseEntity<>(commisionPlanDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/commision_plan/{commision_plan_id}")
    public ResponseEntity<CommisionPlanDTO> updateCommisionPlan(@RequestBody CommisionPlan uCommisionPlan, @PathVariable Long commision_plan_id) throws NoSuchInterviewResultException, NoSuchCandidateException {
        commisionPlanService.updateCommisionPlan(uCommisionPlan, commision_plan_id);
        CommisionPlan commisionPlan = commisionPlanService.getCommisionPlan(commision_plan_id);
        Link link = linkTo(methodOn(CommisionPlanController.class).getCommisionPlan(commision_plan_id)).withSelfRel();

        CommisionPlanDTO commisionPlanDTO = new CommisionPlanDTO(commisionPlan, link);

        return new ResponseEntity<>(commisionPlanDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/commision_plan/{commision_plan_id}")
    public  ResponseEntity deleteCommisionPlan(@PathVariable Long commision_plan_id) throws ExistsCandidatesForInterviewResultException, NoSuchInterviewResultException {
        commisionPlanService.deleteCommisionPlan(commision_plan_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
