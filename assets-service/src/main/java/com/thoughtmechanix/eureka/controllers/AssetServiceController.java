package com.thoughtmechanix.eureka.controllers;

import com.thoughtmechanix.eureka.model.Asset;
import com.thoughtmechanix.eureka.model.AssetType;
import com.thoughtmechanix.eureka.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/assets")
public class AssetServiceController {

    @Autowired
    AssetService assetService;

    @GetMapping("/")
    public ResponseEntity<List<Asset>> getAssets(@PathVariable("organizationId") String organizationId){
        List<Asset> assets = assetService.getAssets(organizationId);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAsset(@PathVariable("organizationId") String organizationId,
                                          @PathVariable("assetId") String assetId){
        Asset asset = assetService.getAsset(organizationId, assetId);

        if(asset == null){
            asset = new Asset()
                    .withAssetId(assetId)
                    .withOrganizationId(organizationId)
                    .withAssetName("Default House")
                    .withAssetType(AssetType.Tangible)
                    .withComment("Default Comment");
            assetService.createAsset(asset);
        }
        return ResponseEntity.ok(asset);
    }

    @PostMapping("/")
    public ResponseEntity<String> createAsset(@RequestBody Asset asset){
        String id = UUID.randomUUID().toString();
        asset.setAssetId(id);
        assetService.createAsset(asset);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "http://{your.docker.ip}:8080/v1/" + asset.getOrganizationId() + "/eureka/" +
                    id
        );
    }

    @RequestMapping(path  = "/{assetId}", method = RequestMethod.PUT)
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset assetNew, @PathVariable("organizationId") String organizationId,
                                      @PathVariable("assetId") String assetId){

        Asset asset = assetService.getAsset(organizationId, assetId);

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
    public ResponseEntity deleteAsset(@PathVariable("organizationId") String organizationId,
                                      @PathVariable("assetId") String assetId){

        Asset asset = assetService.getAsset(organizationId, assetId);

        if(asset == null){
            return ResponseEntity.notFound().build();
        }

        assetService.deleteAsset(asset);

        return ResponseEntity.status(204).build();
    }
}
