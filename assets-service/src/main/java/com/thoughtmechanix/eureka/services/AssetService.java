package com.thoughtmechanix.eureka.services;

import com.thoughtmechanix.eureka.clients.CompanyDiscoveryClient;
import com.thoughtmechanix.eureka.clients.CompanyFeignClient;
import com.thoughtmechanix.eureka.clients.CompanyRestClient;
import com.thoughtmechanix.eureka.model.Asset;
import com.thoughtmechanix.eureka.model.Company;
import com.thoughtmechanix.eureka.repository.AssetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    CompanyDiscoveryClient companyDiscoveryClient;

    @Autowired
    CompanyRestClient companyRestClient;

    @Autowired
    CompanyFeignClient companyFeignClient;

    private static final Logger logger = Logger.getLogger(AssetService.class.getName());

    public List<Asset> getAssetsByCompany(String companyId){
        return assetRepository.findAllByCompanyId(companyId);
    }

    public Asset getAsset(String companyId, String assetId, String clientType){
        Asset asset = assetRepository.findByCompanyIdAndAssetId(companyId, assetId);
        Company company = retrieveCompany(companyId, clientType);

        return asset
                .withCompanyName(company.getCompanyName())
                .withNumOfEmployees(company.getNumOfEmployees())
                .withYearEstablished(company.getYearEstablished())
                .withCapital(company.getCapital());
    }

    public Asset createAsset(Asset asset){

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset assetOld, Asset assetNew){

        BeanUtils.copyProperties(assetNew, assetOld);

        return assetRepository.save(assetOld);
    }

    public void  deleteAsset(Asset asset){
        assetRepository.delete(asset);
    }

    public Company retrieveCompany(String companyId, String clientType){
         Company company = null;

         switch (clientType){
             case "feign":
                 logger.info("Feign client");
                 company = companyFeignClient.getCompany(companyId);
                 break;
             case "rest":
                 logger.info("Rest Client");
                 company = companyRestClient.getCompanyRestTemplate(companyId);
                 break;
             case "discovery":
                 logger.info("Discovery client");
                 company = companyDiscoveryClient.getCompanyDiscovery(companyId);
             default:
                 logger.info("Default client");
                 company = companyRestClient.getCompanyRestTemplate(companyId);
         }

         return company;
    }
}