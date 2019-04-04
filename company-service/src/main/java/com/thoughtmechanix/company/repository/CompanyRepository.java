package com.thoughtmechanix.company.repository;

import com.thoughtmechanix.company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

    Company findByCompanyId(String companyId);
}
