package com.justDab.demo.service;

import com.justDab.demo.dao.AdvisorRepository;
import com.justDab.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository advisorRepository;

    @Autowired
    public AdvisorServiceImpl(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

    @Override
    public Employee getAdvisorById(int advisorId){
        return advisorRepository.getAdvisorById(advisorId);
    }

}
