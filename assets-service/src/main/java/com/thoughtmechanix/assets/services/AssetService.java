package com.thoughtmechanix.assets.services;

import com.thoughtmechanix.assets.model.Asset;
import com.thoughtmechanix.assets.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<Asset> getAssets(String organizationId){
        return assetRepository.findAllByOrganizationIdContains(organizationId);
    }

    public Asset getAsset(String organizationId, String assetId){
        return assetRepository.findByOrganizationIdAndAssetId(organizationId, assetId);
    }

    public Asset createAsset(Asset asset){
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset assetOld, Asset assetNew){

        //We are not setting ids because they refer to the same object
        assetOld.setAssetName(assetNew.getAssetName());
        assetOld.setAssetType(assetNew.getAssetType());
        assetOld.setComment(assetNew.getComment());

        return assetRepository.save(assetOld);
    }

    public void  deleteAsset(Asset asset){
        assetRepository.delete(asset);
    }
}
