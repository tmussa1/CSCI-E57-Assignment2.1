package com.thoughtmechanix.eureka.repository;

import com.thoughtmechanix.eureka.model.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends CrudRepository<Asset, String> {

    //I did contains because if the user had a typo, it will still find it
    public List<Asset>  findAllByOrganizationIdContains(String organizationId);

    public Asset findByOrganizationIdAndAssetId(String organizationId, String assetId);


}
