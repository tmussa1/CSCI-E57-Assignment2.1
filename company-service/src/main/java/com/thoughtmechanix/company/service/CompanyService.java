package com.thoughtmechanix.company.service;

import com.thoughtmechanix.company.model.Company;
import com.thoughtmechanix.company.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company getCompany(String id){
        return companyRepository.findByCompanyId(id);
    }

    public Company addCompany(Company company){

        company.setCompanyId(UUID.randomUUID().toString());
        return companyRepository.save(company);
    }

    public Company updateCompany(Company companyOld, Company companyNew){

        BeanUtils.copyProperties(companyNew, companyOld);

        return companyRepository.save(companyOld);
    }

    public void deleteCompany(Company company){
        companyRepository.delete(company);
    }
}
