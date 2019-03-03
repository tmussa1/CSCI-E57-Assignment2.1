package com.thoughtmechanix.assets.model;

import javax.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @Column(name="comment")
    private String comment;

    public Asset() {
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Asset withOrganizationId(String organizationId){
        this.setOrganizationId(organizationId);
        return this;
    }

    public Asset withAssetId(String assetId){
        this.setOrganizationId(assetId);
        return this;
    }

    public Asset withAssetType(AssetType assetType){
        this.setAssetType(assetType);
        return this;
    }

    public Asset withAssetName(String assetName){
        this.setAssetName(assetName);
        return this;
    }

    public Asset withComment(String comment){
        this.setComment(comment);
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
