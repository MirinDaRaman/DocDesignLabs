package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.service.SalaryService;
import com.doc.lab2.DTO.impl.SalaryDTO;
import com.doc.lab2.domain.Salary;

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
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @GetMapping(value = "/api/salary")
    public ResponseEntity<Set<SalaryDTO>> getAllSalary() throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        List<Salary> salaries = salaryService.getAllSalaries();
        Link link = linkTo(methodOn(SalaryController.class).getAllSalary()).withSelfRel();

        Set<SalaryDTO> salaryDTOS = new HashSet<>();
        for (Salary entity : salaries) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            SalaryDTO dto = new SalaryDTO(entity, selfLink);
            salaryDTOS.add(dto);
        }

        return new ResponseEntity<>(salaryDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/salary/{salary_id}")
    public ResponseEntity<SalaryDTO> getSalary(@PathVariable Long salary_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        Salary salary = salaryService.getSalary(salary_id);
        Link link = linkTo(methodOn(SalaryController.class).getSalary(salary_id)).withSelfRel();
        System.out.println(salary);
        SalaryDTO salaryDTO = new SalaryDTO(salary, link);

        return new ResponseEntity<>(salaryDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/salary/{salary_id}")
    public  ResponseEntity<SalaryDTO> addSalary(@RequestBody Salary newSalary) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        salaryService.createSalary(newSalary);
        Link link = linkTo(methodOn(SalaryController.class).getSalary(newSalary.getId())).withSelfRel();

        SalaryDTO salaryDTO = new SalaryDTO(newSalary, link);

        return new ResponseEntity<>(salaryDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/salary/{salary_id}")
    public  ResponseEntity<SalaryDTO> updateSalary(@RequestBody Salary uSalary, @PathVariable Long salary_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        salaryService.updateSalary(uSalary, salary_id);
        Salary salary = salaryService.getSalary(salary_id);
        Link link = linkTo(methodOn(SalaryController.class).getSalary(salary_id)).withSelfRel();

        SalaryDTO salaryDTO = new SalaryDTO(salary, link);

        return new ResponseEntity<>(salaryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/salary/{salary_id}")
    public  ResponseEntity deleteSalary(@PathVariable Long salary_id) throws NoSuchVacancyException, ExistsPersonsForCityException, ExistsCandidatesForVacancyException {
        salaryService.deleteSalary(salary_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
