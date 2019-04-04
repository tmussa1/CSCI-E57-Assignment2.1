package com.thoughtmechanix.company.controller;

import com.thoughtmechanix.company.model.Company;
import com.thoughtmechanix.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value="/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable("companyId") String companyId){
        Company company = companyService.getCompany(companyId);

        if(company == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping(value="/{companyId}")
    public ResponseEntity addCompany(@RequestBody Company company){
        try{
            //I accounted for DB not being available for whatever reason
            companyService.addCompany(company);

            return ResponseEntity.ok().build();

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @PutMapping(value="/{companyId}")
    public ResponseEntity updateCompany(@PathVariable("companyId") String companyId, @RequestBody Company companyNew){
        Company companyOld = companyService.getCompany(companyId);

        if(companyOld == null){
            //Create it if it doesn't exist
            companyService.addCompany(companyNew);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }

        //Modify it if it exists
        companyService.updateCompany(companyOld, companyNew);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable("companyId") String companyId){

        //It may not be found or some DB error hence the try/catch
        try{

            Company company = companyService.getCompany(companyId);
            companyService.deleteCompany(company);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
