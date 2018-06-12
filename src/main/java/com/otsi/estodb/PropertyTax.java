package com.otsi.estodb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PropertyTax")
public class PropertyTax {
	
	private String totalDemand;

	private String firstInstallmentCollection;

	private String secondInstallmentDemand;

	private String cityName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String propertyAddress;

	private String cityCode;

	private String arrearInterestCollection;

	private String revenueBlock;

	private String currentInterestCollection;

	private String ptAssesmentNo;

	private String locationName;

	private String totalCollection;

	private String arrearBalance;

	private String propertyUsage;

	private String arvAmount;

	private String currentInterestDemand;

	private String consumerName;

	private String propertyCategory;

	private String annualDemand;

	private String adjustment;

	private String adminWardName;

	private String billCollector;

	private String rebate;

	private String duePeriod;

	private String isExempted;

	private String cityGrade;

	private String consumerType;

	private String arrearDemand;

	private String arrearInterestDemand;

	private String totalBalance;

	private String annualCollection;

	private String regionName;

	private String annualBalance;

	private String revZoneName;

	private String boundaryGeo_lat;

	private String boundaryGeo_lon;

	private String firstInstallmentDemand;

	private String isActive;

	private String districtName;

	private String aadhaarNumber;
    @Id
	private String consumerCode;

	private String secondInstallmentCollection;

	private String propertyGeo_lat;

	private String propertyGeo_lon;

	private String builtupArea;

	private String sitalArea;

	private String revenueWard;

	private String isUnderCourtcase;

	private String mobileNumber;

	private String advance;

	private String arrearCollection;

	public String getTotalDemand() {
		return totalDemand;
	}

	public void setTotalDemand(String totalDemand) {
		this.totalDemand = totalDemand;
	}

	public String getFirstInstallmentCollection() {
		return firstInstallmentCollection;
	}

	public void setFirstInstallmentCollection(String firstInstallmentCollection) {
		this.firstInstallmentCollection = firstInstallmentCollection;
	}

	public String getSecondInstallmentDemand() {
		return secondInstallmentDemand;
	}

	public void setSecondInstallmentDemand(String secondInstallmentDemand) {
		this.secondInstallmentDemand = secondInstallmentDemand;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getArrearInterestCollection() {
		return arrearInterestCollection;
	}

	public void setArrearInterestCollection(String arrearInterestCollection) {
		this.arrearInterestCollection = arrearInterestCollection;
	}

	public String getRevenueBlock() {
		return revenueBlock;
	}

	public void setRevenueBlock(String revenueBlock) {
		this.revenueBlock = revenueBlock;
	}

	public String getCurrentInterestCollection() {
		return currentInterestCollection;
	}

	public void setCurrentInterestCollection(String currentInterestCollection) {
		this.currentInterestCollection = currentInterestCollection;
	}

	public String getPtAssesmentNo() {
		return ptAssesmentNo;
	}

	public void setPtAssesmentNo(String ptAssesmentNo) {
		this.ptAssesmentNo = ptAssesmentNo;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(String totalCollection) {
		this.totalCollection = totalCollection;
	}

	public String getArrearBalance() {
		return arrearBalance;
	}

	public void setArrearBalance(String arrearBalance) {
		this.arrearBalance = arrearBalance;
	}

	public String getPropertyUsage() {
		return propertyUsage;
	}

	public void setPropertyUsage(String propertyUsage) {
		this.propertyUsage = propertyUsage;
	}

	public String getArvAmount() {
		return arvAmount;
	}

	public void setArvAmount(String arvAmount) {
		this.arvAmount = arvAmount;
	}

	public String getCurrentInterestDemand() {
		return currentInterestDemand;
	}

	public void setCurrentInterestDemand(String currentInterestDemand) {
		this.currentInterestDemand = currentInterestDemand;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getPropertyCategory() {
		return propertyCategory;
	}

	public void setPropertyCategory(String propertyCategory) {
		this.propertyCategory = propertyCategory;
	}

	public String getAnnualDemand() {
		return annualDemand;
	}

	public void setAnnualDemand(String annualDemand) {
		this.annualDemand = annualDemand;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	public String getAdminWardName() {
		return adminWardName;
	}

	public void setAdminWardName(String adminWardName) {
		this.adminWardName = adminWardName;
	}

	public String getBillCollector() {
		return billCollector;
	}

	public void setBillCollector(String billCollector) {
		this.billCollector = billCollector;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getDuePeriod() {
		return duePeriod;
	}

	public void setDuePeriod(String duePeriod) {
		this.duePeriod = duePeriod;
	}

	public String getIsExempted() {
		return isExempted;
	}

	public void setIsExempted(String isExempted) {
		this.isExempted = isExempted;
	}

	public String getCityGrade() {
		return cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	public String getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}

	public String getArrearDemand() {
		return arrearDemand;
	}

	public void setArrearDemand(String arrearDemand) {
		this.arrearDemand = arrearDemand;
	}

	public String getArrearInterestDemand() {
		return arrearInterestDemand;
	}

	public void setArrearInterestDemand(String arrearInterestDemand) {
		this.arrearInterestDemand = arrearInterestDemand;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getAnnualCollection() {
		return annualCollection;
	}

	public void setAnnualCollection(String annualCollection) {
		this.annualCollection = annualCollection;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAnnualBalance() {
		return annualBalance;
	}

	public void setAnnualBalance(String annualBalance) {
		this.annualBalance = annualBalance;
	}

	public String getRevZoneName() {
		return revZoneName;
	}

	public void setRevZoneName(String revZoneName) {
		this.revZoneName = revZoneName;
	}

	public String getBoundaryGeo_lat() {
		return boundaryGeo_lat;
	}

	public void setBoundaryGeo_lat(String boundaryGeo_lat) {
		this.boundaryGeo_lat = boundaryGeo_lat;
	}

	public String getBoundaryGeo_lon() {
		return boundaryGeo_lon;
	}

	public void setBoundaryGeo_lon(String boundaryGeo_lon) {
		this.boundaryGeo_lon = boundaryGeo_lon;
	}

	public String getFirstInstallmentDemand() {
		return firstInstallmentDemand;
	}

	public void setFirstInstallmentDemand(String firstInstallmentDemand) {
		this.firstInstallmentDemand = firstInstallmentDemand;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getConsumerCode() {
		return consumerCode;
	}

	public void setConsumerCode(String consumerCode) {
		this.consumerCode = consumerCode;
	}

	public String getSecondInstallmentCollection() {
		return secondInstallmentCollection;
	}

	public void setSecondInstallmentCollection(String secondInstallmentCollection) {
		this.secondInstallmentCollection = secondInstallmentCollection;
	}

	public String getPropertyGeo_lat() {
		return propertyGeo_lat;
	}

	public void setPropertyGeo_lat(String propertyGeo_lat) {
		this.propertyGeo_lat = propertyGeo_lat;
	}

	public String getPropertyGeo_lon() {
		return propertyGeo_lon;
	}

	public void setPropertyGeo_lon(String propertyGeo_lon) {
		this.propertyGeo_lon = propertyGeo_lon;
	}

	public String getBuiltupArea() {
		return builtupArea;
	}

	public void setBuiltupArea(String builtupArea) {
		this.builtupArea = builtupArea;
	}

	public String getSitalArea() {
		return sitalArea;
	}

	public void setSitalArea(String sitalArea) {
		this.sitalArea = sitalArea;
	}

	public String getRevenueWard() {
		return revenueWard;
	}

	public void setRevenueWard(String revenueWard) {
		this.revenueWard = revenueWard;
	}

	public String getIsUnderCourtcase() {
		return isUnderCourtcase;
	}

	public void setIsUnderCourtcase(String isUnderCourtcase) {
		this.isUnderCourtcase = isUnderCourtcase;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public String getArrearCollection() {
		return arrearCollection;
	}

	public void setArrearCollection(String arrearCollection) {
		this.arrearCollection = arrearCollection;
	}

	public PropertyTax() {
	}
}
