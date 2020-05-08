package com.doc.lab2.service;

import com.doc.lab2.exceptions.ExistsCandidatesForVacancyException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.domain.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SalaryService {
    @Autowired
    com.doc.lab2.Repository.VacancyRepository VacancyRepository;
    private boolean ascending;

    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    public List<Salary> getAllSalaries() {
        return VacancyRepository.findAll();
    }

    public Salary getSalary(Long class_id) throws NoSuchVacancyException {
        Salary vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        System.out.println(vacancy);
        if (vacancy == null) throw new NoSuchVacancyException();
        return vacancy;
    }

    @Transactional
    public void createSalary(Salary vacancy) {
        VacancyRepository.save(vacancy);
    }

    @Transactional
    public void updateSalary(Salary uVacancy, Long class_id) throws NoSuchVacancyException {
        Salary vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7

        if (vacancy == null) throw new NoSuchVacancyException();
        vacancy.setWorkers(uVacancy.getWorkers());
        VacancyRepository.save(uVacancy);
    }

    @Transactional
    public void deleteSalary(Long class_id) throws NoSuchVacancyException, ExistsCandidatesForVacancyException {
        Salary vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (vacancy == null) throw new NoSuchVacancyException();
        if (vacancy.getWorkers().size() != 0) throw new ExistsCandidatesForVacancyException();
        VacancyRepository.delete(vacancy);
    }


}
