package com.doc.lab2.service;

import com.doc.lab2.exceptions.ExistsCandidatesForInterviewResultException;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.CommisionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CommisionPlanService {
    @Autowired
    com.doc.lab2.Repository.InterviewResultRepository InterviewResultRepository;

    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    public Set<CommisionPlan> getInterviewResultsByCandidateId(Long Candidate_id) throws NoSuchCandidateException {
        Worker Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        return Candidate.getCommisionPlans();
    }

    public CommisionPlan getCommisionPlan(Long InterviewResult_id) throws NoSuchInterviewResultException {
        CommisionPlan interviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7
        if (interviewResult == null) throw new NoSuchInterviewResultException();
        return interviewResult;
    }

    public List<CommisionPlan> getAllCommisionPlans() {
        return InterviewResultRepository.findAll();
    }

    @Transactional
    public void createCommisionPlan(CommisionPlan interviewResult) {
        InterviewResultRepository.save(interviewResult);
    }

    @Transactional
    public void updateCommisionPlan(CommisionPlan uInterviewResult, Long InterviewResult_id) throws NoSuchInterviewResultException {
        CommisionPlan InterviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7
        if (InterviewResult == null) throw new NoSuchInterviewResultException();
        //update
        InterviewResult.setCommision_plan(uInterviewResult.getCommision_plan());
    }

    @Transactional
    public void deleteCommisionPlan(Long InterviewResult_id) throws NoSuchInterviewResultException, ExistsCandidatesForInterviewResultException {
        CommisionPlan interviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7

        if (interviewResult == null) throw new NoSuchInterviewResultException();
        InterviewResultRepository.delete(interviewResult);
    }
}
