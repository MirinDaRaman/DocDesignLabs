package com.doc.lab2.service;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.domain.Salary;
import com.doc.lab2.domain.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class WorkerService {
    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    @Autowired
    com.doc.lab2.Repository.VacancyRepository VacancyRepository;

    @Autowired
    com.doc.lab2.Repository.InterviewResultRepository InterviewResultRepository;

    public Set<Worker> getWorkerBySalaryId(Long class_id) throws NoSuchVacancyException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Salary Vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (Vacancy == null) throw new NoSuchVacancyException();
        return Vacancy.getWorkers();
    }

    public Worker getWorker(Long Canidate_id) throws NoSuchCandidateException {
        Worker Candidate = CandidateRepository.findById(Canidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        return Candidate;
    }

    public List<Worker> getAllWorkers() {
        return CandidateRepository.findAll();
    }

    @Transactional
    public void createWorker(Worker Candidate, Long Vacancy_id) throws NoSuchVacancyException {
        if (Vacancy_id > 0) {
            Salary Vacancy = VacancyRepository.findById(Vacancy_id).get();//2.0.0.M7
            if (Vacancy == null) throw new NoSuchVacancyException();
            Candidate.setSalaryByWorkers(Vacancy);
        }
        CandidateRepository.save(Candidate);
    }

    @Transactional
    public void updateWorker(Worker uCandidate, Long Candidate_id, Long class_id) throws NoSuchVacancyException, NoSuchCandidateException {
        Salary Vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (class_id > 0) {
            if (Vacancy == null) throw new NoSuchVacancyException();
        }
        Worker Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        //update
        Candidate.setSurname(uCandidate.getSurname());
        Candidate.setName(uCandidate.getName());
        Candidate.setUnit_salary(uCandidate.getUnit_salary());
        if (class_id > 0) Candidate.setSalaryByWorkers(Vacancy);
        else Candidate.setSalaryByWorkers(null);
        CandidateRepository.save(Candidate);
    }

    @Transactional
    public void deleteWorker(Long Candidate_id) throws NoSuchCandidateException, ExistsInterviewResultForCandidatesException {
        Worker Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        if (Candidate.getCommisionPlans().size() != 0) throw new ExistsInterviewResultForCandidatesException();
        CandidateRepository.delete(Candidate);
    }
}
