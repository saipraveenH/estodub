package com.otsi.estodb;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WaterTax")
public class WaterCharges {
	@Column(columnDefinition = "nvarchar(max)")
	private String aadhaarnumber;
	@Column(columnDefinition = "nvarchar(max)")
	private String monthlyRate;
	@Column(columnDefinition = "nvarchar(max)")
	private String closureType;
	@Column(columnDefinition = "nvarchar(max)")
	private String consumername;
	@Column(columnDefinition = "nvarchar(max)")

	private String arrearsDemand;
	@Id
	private String id;
	@Column(columnDefinition = "nvarchar(max)")

	private String locality;
	@Column(columnDefinition = "nvarchar(max)")

	private String arrearsDue;
	@Column(columnDefinition = "nvarchar(max)")

	private String currentDue;
	@Column(columnDefinition = "nvarchar(max)")

	private String currentDemand;
	@Column(columnDefinition = "nvarchar(max)")

	private String waterTaxDue;
	@Column(columnDefinition = "nvarchar(max)")

	private String islegacy;
	@Column(columnDefinition = "nvarchar(max)")
	private String applicationcode;
	@Column(columnDefinition = "nvarchar(max)")
	private String districtname;
	@Column(columnDefinition = "nvarchar(max)")
	private String status;
	@Column(columnDefinition = "nvarchar(max)")
	private String sumpcapacity;
	@Column(columnDefinition = "nvarchar(max)")
	private String doorno;
	@Column(columnDefinition = "nvarchar(max)")
	private String bpaid;
	@Column(columnDefinition = "nvarchar(max)")
	private String pipesize;
	@Column(columnDefinition = "nvarchar(max)")

	private String watersource;
	@Column(columnDefinition = "nvarchar(max)")
	private String propertyid;
	@Column(columnDefinition = "nvarchar(max)")
	private String propertytype;
	@Column(columnDefinition = "nvarchar(max)")
	private String category;
	@Column(columnDefinition = "nvarchar(max)")

	private String regionname;
	@Column(columnDefinition = "nvarchar(max)")

	private String connectiontype;
	@Column(columnDefinition = "nvarchar(max)")

	private String adminward;
	@Column(columnDefinition = "nvarchar(max)")
	private String ward;
	@Column(columnDefinition = "nvarchar(max)")
	private String mobilenumber;

	@Column(columnDefinition = "nvarchar(max)")
	private String grade;
	@Column(columnDefinition = "nvarchar(max)")

	private String usage;

	private String ulbname;
	@Column(columnDefinition = "nvarchar(max)")

	private String consumercode;
	@Column(columnDefinition = "nvarchar(max)")

	private String numberofperson;
	@Column(columnDefinition = "nvarchar(max)")

	private String totaldue;
	@Column(columnDefinition = "nvarchar(max)")

	private String zone;

	private String wardlocation_lat;

	private String wardlocation_lon;

	private String propertylocation_lat;

	private String propertylocation_lon;

	@Column(columnDefinition = "datetime2")
	private Date createdDate;

	public String getAadhaarnumber() {
		return aadhaarnumber;
	}

	public void setAadhaarnumber(String aadhaarnumber) {
		this.aadhaarnumber = aadhaarnumber;
	}

	public String getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(String monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public String getClosureType() {
		return closureType;
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType;
	}

	public String getConsumername() {
		return consumername;
	}

	public void setConsumername(String consumername) {
		this.consumername = consumername;
	}

	public String getArrearsDemand() {
		return arrearsDemand;
	}

	public void setArrearsDemand(String arrearsDemand) {
		this.arrearsDemand = arrearsDemand;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getArrearsDue() {
		return arrearsDue;
	}

	public void setArrearsDue(String arrearsDue) {
		this.arrearsDue = arrearsDue;
	}

	public String getCurrentDue() {
		return currentDue;
	}

	public void setCurrentDue(String currentDue) {
		this.currentDue = currentDue;
	}

	public String getCurrentDemand() {
		return currentDemand;
	}

	public void setCurrentDemand(String currentDemand) {
		this.currentDemand = currentDemand;
	}

	public String getWaterTaxDue() {
		return waterTaxDue;
	}

	public void setWaterTaxDue(String waterTaxDue) {
		this.waterTaxDue = waterTaxDue;
	}

	public String getIslegacy() {
		return islegacy;
	}

	public void setIslegacy(String islegacy) {
		this.islegacy = islegacy;
	}

	public String getApplicationcode() {
		return applicationcode;
	}

	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSumpcapacity() {
		return sumpcapacity;
	}

	public void setSumpcapacity(String sumpcapacity) {
		this.sumpcapacity = sumpcapacity;
	}

	public String getDoorno() {
		return doorno;
	}

	public void setDoorno(String doorno) {
		this.doorno = doorno;
	}

	public String getBpaid() {
		return bpaid;
	}

	public void setBpaid(String bpaid) {
		this.bpaid = bpaid;
	}

	public String getPipesize() {
		return pipesize;
	}

	public void setPipesize(String pipesize) {
		this.pipesize = pipesize;
	}

	public String getWatersource() {
		return watersource;
	}

	public void setWatersource(String watersource) {
		this.watersource = watersource;
	}

	public String getPropertyid() {
		return propertyid;
	}

	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}

	public String getPropertytype() {
		return propertytype;
	}

	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(String connectiontype) {
		this.connectiontype = connectiontype;
	}

	public String getAdminward() {
		return adminward;
	}

	public void setAdminward(String adminward) {
		this.adminward = adminward;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getUlbname() {
		return ulbname;
	}

	public void setUlbname(String ulbname) {
		this.ulbname = ulbname;
	}

	public String getConsumercode() {
		return consumercode;
	}

	public void setConsumercode(String consumercode) {
		this.consumercode = consumercode;
	}

	public String getNumberofperson() {
		return numberofperson;
	}

	public void setNumberofperson(String numberofperson) {
		this.numberofperson = numberofperson;
	}

	public String getTotaldue() {
		return totaldue;
	}

	public void setTotaldue(String totaldue) {
		this.totaldue = totaldue;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getWardlocation_lat() {
		return wardlocation_lat;
	}

	public void setWardlocation_lat(String wardlocation_lat) {
		this.wardlocation_lat = wardlocation_lat;
	}

	public String getWardlocation_lon() {
		return wardlocation_lon;
	}

	public void setWardlocation_lon(String wardlocation_lon) {
		this.wardlocation_lon = wardlocation_lon;
	}

	public String getPropertylocation_lat() {
		return propertylocation_lat;
	}

	public void setPropertylocation_lat(String propertylocation_lat) {
		this.propertylocation_lat = propertylocation_lat;
	}

	public String getPropertylocation_lon() {
		return propertylocation_lon;
	}

	public void setPropertylocation_lon(String propertylocation_lon) {
		this.propertylocation_lon = propertylocation_lon;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
