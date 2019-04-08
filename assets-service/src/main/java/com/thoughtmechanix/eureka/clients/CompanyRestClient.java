package com.thoughtmechanix.eureka.clients;

import com.thoughtmechanix.eureka.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompanyRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Company getCompanyRestTemplate(String companyId){
        return restTemplate.exchange("http://company-service/v1/companys/{companyId}",
                HttpMethod.GET, null, Company.class, companyId).getBody();
    }
}
