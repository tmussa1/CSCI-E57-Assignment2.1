package com.thoughtmechanix.eureka.controllers;

import com.thoughtmechanix.eureka.model.Asset;
import com.thoughtmechanix.eureka.model.AssetType;
import com.thoughtmechanix.eureka.model.Company;
import com.thoughtmechanix.eureka.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/companys/{companyId}/assets")
public class AssetServiceController {

    @Autowired
    AssetService assetService;

    @RequestMapping(path = "/" , method = RequestMethod.GET)
    public ResponseEntity<List<Asset>> getAssets(@PathVariable("companyId") String companyId){
        List<Asset> assets = assetService.getAssetsByCompany(companyId);
        return ResponseEntity.ok(assets);
    }

    @RequestMapping(path = "/{assetId}/{clientType}", method = RequestMethod.GET)
    public ResponseEntity<Asset> getAsset(@PathVariable("companyId") String companyId,
                                          @PathVariable("assetId") String assetId,
                                          @PathVariable("clientType") String clientType){

        return ResponseEntity.ok(assetService.getAsset(companyId, assetId, clientType));
    }

    @RequestMapping(path = "/" , method = RequestMethod.POST)
    public ResponseEntity<String> createAsset(@RequestBody Asset asset, @PathVariable("companyId") String companyId){
        String id = UUID.randomUUID().toString();
        asset.setAssetId(id);
        assetService.createAsset(asset);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "http://192.168.99.100:8080/v1/" + asset.getCompanyId() + "/assets/" +
                    id);
    }

    @RequestMapping(path  = "/{assetId}", method = RequestMethod.PUT)
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset assetNew, @PathVariable("companyId") String companyId,
                                      @PathVariable("assetId") String assetId){

        Asset asset = assetService.getAsset(companyId, assetId, "discovery");

        if(asset == null){
            assetService.createAsset(assetNew);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            asset = assetService.updateAsset(asset, assetNew);
        }
         return ResponseEntity.status(HttpStatus.FOUND)
                 .body(asset);
    }


    @RequestMapping(path = "/{assetId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAsset(@PathVariable("companyId") String companyId,
                                      @PathVariable("assetId") String assetId){

        Asset asset = assetService.getAsset(companyId, assetId, "rest");

        if(asset == null){
            return ResponseEntity.notFound().build();
        }

        assetService.deleteAsset(asset);

        return ResponseEntity.status(204).build();
    }
}
