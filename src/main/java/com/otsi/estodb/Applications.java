package com.otsi.estodb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Applications")
public class Applications {
	@Id
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String moduleName;

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	private String applicationNumber;

	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@Column(columnDefinition = "datetime2")
	private Date applicationDate;

	public Date getApplicationDate() {
		return this.applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	private String applicationType;

	public String getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	private String applicantName;

	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String applicantAddress;

	public String getApplicantAddress() {
		return this.applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	@Column(columnDefinition = "datetime2")
	private Date disposalDate;

	public Date getDisposalDate() {
		return this.disposalDate;
	}

	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}

	private String status;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String url;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String consumerCode;

	public String getConsumerCode() {
		return this.consumerCode;
	}

	public void setConsumerCode(String consumerCode) {
		this.consumerCode = consumerCode;
	}

	private String mobileNumber;

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	private String ownerName;

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	private String aadharNumber;

	public String getAadharNumber() {
		return this.aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	private int elapsedDays;

	public int getElapsedDays() {
		return this.elapsedDays;
	}

	public void setElapsedDays(int elapsedDays) {
		this.elapsedDays = elapsedDays;
	}

	private String closed;

	public String getClosed() {
		return this.closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	private String approved;

	public String getApproved() {
		return this.approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	private String channel;

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	private String cityCode;

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	private String cityName;

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	private String cityGrade;

	public String getCityGrade() {
		return this.cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	private String districtName;

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	private String regionName;

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	private int isClosed;

	public int getIsClosed() {
		return this.isClosed;
	}

	public void setIsClosed(int isClosed) {
		this.isClosed = isClosed;
	}

	private int sla;

	public int getSla() {
		return this.sla;
	}

	public void setSla(int sla) {
		this.sla = sla;
	}

	private int slaGap;

	public int getSlaGap() {
		return this.slaGap;
	}

	public void setSlaGap(int slaGap) {
		this.slaGap = slaGap;
	}

	@Column(columnDefinition = "datetime2")
	private Date createdDate;

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
