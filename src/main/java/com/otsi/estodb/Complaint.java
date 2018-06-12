package com.otsi.estodb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Complaint")
public class Complaint {
	private int rating;

	private Date completionDate;

	private int feedbackRating;

	private int noOfFeedbackTaken;

	private int noOfFeedbackReviews;

	private String feedbackReason;

	private String complaintGeo_lon;

	private String complaintGeo_lat;

	private String wardGeo_lon;

	private String wardGeo_lat;

	private String localityGeo_lon;

	private String localityGeo_lat;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String initialFunctionaryMobileNumber;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String localityName;

	private int ifSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String cityName;

	private int cityCode;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation1FunctionaryIsSLA;

	private Date complaintReOpenedDate;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complaintTypeCode;

	private int escalation2FunctionaryIfSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String cityDomainUrl;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String cityDistrictName;

	private int escalation3FunctionaryIfSLA;

	private int addressed;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String categoryName;

	private int escalation1FunctionaryIfSLA;

	private int assigneeId;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation1FunctionaryName;
	private int rejected;

	private int escalation1FunctionarySLADays;

	private Date createdDate;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complaintStatusName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complaintIsClosed;

	private int currentFunctionaryAgeingFromDue;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String currentFunctionaryMobileNumber;

	private int complaintAgeingFromDue;

	private int escalation3FunctionarySLADays;

	private Date currentFunctionaryAssigneddate;

	private int complaintAgeingdaysFromDue;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String currentFunctionaryName;

	private int categoryId;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String reasonForRejection;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String url;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complaintTypeName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation3FunctionaryIsSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String initialFunctionaryName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complainantName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String durationRange;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String isSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String initialFunctionaryIsSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation2FunctionaryIsSLA;

	private int escalation3FunctionaryAgeingFromDue;

	private int cityDistrictCode;

	private int complaintDuration;

	private Date escalation3FunctionaryAssigneddate;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String wardName;

	private int complaintSLADays;

	private int reOpened;

	private int escalationLevel;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String closedByFunctionaryName;

	private int escalation2FunctionaryAgeingFromDue;

	@Id
	private String id;

	private int wardNo;

	private Date initialFunctionaryAssigneddate;

	private int localityNo;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String details;

	private Date escalation1FunctionaryAssigneddate;

	private int complaintPeriod;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String crn;

	private int inProcess;

	private String complainantMobile;

	private boolean closed;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String complainantEmail;

	private int initialFunctionaryIfSLA;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation2FunctionaryName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String cityGrade;

	private int satisfactionIndex;

	private int ifClosed;

	private Date escalationDate;

	private int currentFunctionaryIfSLA;

	private int initialFunctionarySLADays;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String cityRegionName;

	private Date escalation2FunctionaryAssigneddate;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String landmarkDetails;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String assigneeName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String escalation3FunctionaryName;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String departmentName;

	private int currentFunctionarySLADays;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String source;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String receivingMode;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String currentFunctionaryIsSLA;

	private int initialFunctionaryAgeingFromDue;

	private int registered;

	private int escalation1FunctionaryAgeingFromDue;

	private int escalation2FunctionarySLADays;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String departmentCode;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getFeedbackRating() {
		return feedbackRating;
	}

	public void setFeedbackRating(int feedbackRating) {
		this.feedbackRating = feedbackRating;
	}

	public int getNoOfFeedbackTaken() {
		return noOfFeedbackTaken;
	}

	public void setNoOfFeedbackTaken(int noOfFeedbackTaken) {
		this.noOfFeedbackTaken = noOfFeedbackTaken;
	}

	public int getNoOfFeedbackReviews() {
		return noOfFeedbackReviews;
	}

	public void setNoOfFeedbackReviews(int noOfFeedbackReviews) {
		this.noOfFeedbackReviews = noOfFeedbackReviews;
	}

	public String getFeedbackReason() {
		return feedbackReason;
	}

	public void setFeedbackReason(String feedbackReason) {
		this.feedbackReason = feedbackReason;
	}

	public String getComplaintGeo_lon() {
		return complaintGeo_lon;
	}

	public void setComplaintGeo_lon(String complaintGeo_lon) {
		this.complaintGeo_lon = complaintGeo_lon;
	}

	public String getComplaintGeo_lat() {
		return complaintGeo_lat;
	}

	public void setComplaintGeo_lat(String complaintGeo_lat) {
		this.complaintGeo_lat = complaintGeo_lat;
	}

	public String getWardGeo_lon() {
		return wardGeo_lon;
	}

	public void setWardGeo_lon(String wardGeo_lon) {
		this.wardGeo_lon = wardGeo_lon;
	}

	public String getWardGeo_lat() {
		return wardGeo_lat;
	}

	public void setWardGeo_lat(String wardGeo_lat) {
		this.wardGeo_lat = wardGeo_lat;
	}

	public String getLocalityGeo_lon() {
		return localityGeo_lon;
	}

	public void setLocalityGeo_lon(String localityGeo_lon) {
		this.localityGeo_lon = localityGeo_lon;
	}

	public String getLocalityGeo_lat() {
		return localityGeo_lat;
	}

	public void setLocalityGeo_lat(String localityGeo_lat) {
		this.localityGeo_lat = localityGeo_lat;
	}

	public String getInitialFunctionaryMobileNumber() {
		return initialFunctionaryMobileNumber;
	}

	public void setInitialFunctionaryMobileNumber(String initialFunctionaryMobileNumber) {
		this.initialFunctionaryMobileNumber = initialFunctionaryMobileNumber;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public int getIfSLA() {
		return ifSLA;
	}

	public void setIfSLA(int ifSLA) {
		this.ifSLA = ifSLA;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getEscalation1FunctionaryIsSLA() {
		return escalation1FunctionaryIsSLA;
	}

	public void setEscalation1FunctionaryIsSLA(String escalation1FunctionaryIsSLA) {
		this.escalation1FunctionaryIsSLA = escalation1FunctionaryIsSLA;
	}

	public Date getComplaintReOpenedDate() {
		return complaintReOpenedDate;
	}

	public void setComplaintReOpenedDate(Date complaintReOpenedDate) {
		this.complaintReOpenedDate = complaintReOpenedDate;
	}

	public String getComplaintTypeCode() {
		return complaintTypeCode;
	}

	public void setComplaintTypeCode(String complaintTypeCode) {
		this.complaintTypeCode = complaintTypeCode;
	}

	public int getEscalation2FunctionaryIfSLA() {
		return escalation2FunctionaryIfSLA;
	}

	public void setEscalation2FunctionaryIfSLA(int escalation2FunctionaryIfSLA) {
		this.escalation2FunctionaryIfSLA = escalation2FunctionaryIfSLA;
	}

	public String getCityDomainUrl() {
		return cityDomainUrl;
	}

	public void setCityDomainUrl(String cityDomainUrl) {
		this.cityDomainUrl = cityDomainUrl;
	}

	public String getCityDistrictName() {
		return cityDistrictName;
	}

	public void setCityDistrictName(String cityDistrictName) {
		this.cityDistrictName = cityDistrictName;
	}

	public int getEscalation3FunctionaryIfSLA() {
		return escalation3FunctionaryIfSLA;
	}

	public void setEscalation3FunctionaryIfSLA(int escalation3FunctionaryIfSLA) {
		this.escalation3FunctionaryIfSLA = escalation3FunctionaryIfSLA;
	}

	public int getAddressed() {
		return addressed;
	}

	public void setAddressed(int addressed) {
		this.addressed = addressed;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getEscalation1FunctionaryIfSLA() {
		return escalation1FunctionaryIfSLA;
	}

	public void setEscalation1FunctionaryIfSLA(int escalation1FunctionaryIfSLA) {
		this.escalation1FunctionaryIfSLA = escalation1FunctionaryIfSLA;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getEscalation1FunctionaryName() {
		return escalation1FunctionaryName;
	}

	public void setEscalation1FunctionaryName(String escalation1FunctionaryName) {
		this.escalation1FunctionaryName = escalation1FunctionaryName;
	}

	public int getRejected() {
		return rejected;
	}

	public void setRejected(int rejected) {
		this.rejected = rejected;
	}

	public int getEscalation1FunctionarySLADays() {
		return escalation1FunctionarySLADays;
	}

	public void setEscalation1FunctionarySLADays(int escalation1FunctionarySLADays) {
		this.escalation1FunctionarySLADays = escalation1FunctionarySLADays;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getComplaintStatusName() {
		return complaintStatusName;
	}

	public void setComplaintStatusName(String complaintStatusName) {
		this.complaintStatusName = complaintStatusName;
	}

	public String getComplaintIsClosed() {
		return complaintIsClosed;
	}

	public void setComplaintIsClosed(String complaintIsClosed) {
		this.complaintIsClosed = complaintIsClosed;
	}

	public int getCurrentFunctionaryAgeingFromDue() {
		return currentFunctionaryAgeingFromDue;
	}

	public void setCurrentFunctionaryAgeingFromDue(int currentFunctionaryAgeingFromDue) {
		this.currentFunctionaryAgeingFromDue = currentFunctionaryAgeingFromDue;
	}

	public String getCurrentFunctionaryMobileNumber() {
		return currentFunctionaryMobileNumber;
	}

	public void setCurrentFunctionaryMobileNumber(String currentFunctionaryMobileNumber) {
		this.currentFunctionaryMobileNumber = currentFunctionaryMobileNumber;
	}

	public int getComplaintAgeingFromDue() {
		return complaintAgeingFromDue;
	}

	public void setComplaintAgeingFromDue(int complaintAgeingFromDue) {
		this.complaintAgeingFromDue = complaintAgeingFromDue;
	}

	public int getEscalation3FunctionarySLADays() {
		return escalation3FunctionarySLADays;
	}

	public void setEscalation3FunctionarySLADays(int escalation3FunctionarySLADays) {
		this.escalation3FunctionarySLADays = escalation3FunctionarySLADays;
	}

	public Date getCurrentFunctionaryAssigneddate() {
		return currentFunctionaryAssigneddate;
	}

	public void setCurrentFunctionaryAssigneddate(Date currentFunctionaryAssigneddate) {
		this.currentFunctionaryAssigneddate = currentFunctionaryAssigneddate;
	}

	public int getComplaintAgeingdaysFromDue() {
		return complaintAgeingdaysFromDue;
	}

	public void setComplaintAgeingdaysFromDue(int complaintAgeingdaysFromDue) {
		this.complaintAgeingdaysFromDue = complaintAgeingdaysFromDue;
	}

	public String getCurrentFunctionaryName() {
		return currentFunctionaryName;
	}

	public void setCurrentFunctionaryName(String currentFunctionaryName) {
		this.currentFunctionaryName = currentFunctionaryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComplaintTypeName() {
		return complaintTypeName;
	}

	public void setComplaintTypeName(String complaintTypeName) {
		this.complaintTypeName = complaintTypeName;
	}

	public String getEscalation3FunctionaryIsSLA() {
		return escalation3FunctionaryIsSLA;
	}

	public void setEscalation3FunctionaryIsSLA(String escalation3FunctionaryIsSLA) {
		this.escalation3FunctionaryIsSLA = escalation3FunctionaryIsSLA;
	}

	public String getInitialFunctionaryName() {
		return initialFunctionaryName;
	}

	public void setInitialFunctionaryName(String initialFunctionaryName) {
		this.initialFunctionaryName = initialFunctionaryName;
	}

	public String getComplainantName() {
		return complainantName;
	}

	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}

	public String getDurationRange() {
		return durationRange;
	}

	public void setDurationRange(String durationRange) {
		this.durationRange = durationRange;
	}

	public String getIsSLA() {
		return isSLA;
	}

	public void setIsSLA(String isSLA) {
		this.isSLA = isSLA;
	}

	public String getInitialFunctionaryIsSLA() {
		return initialFunctionaryIsSLA;
	}

	public void setInitialFunctionaryIsSLA(String initialFunctionaryIsSLA) {
		this.initialFunctionaryIsSLA = initialFunctionaryIsSLA;
	}

	public String getEscalation2FunctionaryIsSLA() {
		return escalation2FunctionaryIsSLA;
	}

	public void setEscalation2FunctionaryIsSLA(String escalation2FunctionaryIsSLA) {
		this.escalation2FunctionaryIsSLA = escalation2FunctionaryIsSLA;
	}

	public int getEscalation3FunctionaryAgeingFromDue() {
		return escalation3FunctionaryAgeingFromDue;
	}

	public void setEscalation3FunctionaryAgeingFromDue(int escalation3FunctionaryAgeingFromDue) {
		this.escalation3FunctionaryAgeingFromDue = escalation3FunctionaryAgeingFromDue;
	}

	public int getCityDistrictCode() {
		return cityDistrictCode;
	}

	public void setCityDistrictCode(int cityDistrictCode) {
		this.cityDistrictCode = cityDistrictCode;
	}

	public int getComplaintDuration() {
		return complaintDuration;
	}

	public void setComplaintDuration(int complaintDuration) {
		this.complaintDuration = complaintDuration;
	}

	public Date getEscalation3FunctionaryAssigneddate() {
		return escalation3FunctionaryAssigneddate;
	}

	public void setEscalation3FunctionaryAssigneddate(Date escalation3FunctionaryAssigneddate) {
		this.escalation3FunctionaryAssigneddate = escalation3FunctionaryAssigneddate;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public int getComplaintSLADays() {
		return complaintSLADays;
	}

	public void setComplaintSLADays(int complaintSLADays) {
		this.complaintSLADays = complaintSLADays;
	}

	public int getReOpened() {
		return reOpened;
	}

	public void setReOpened(int reOpened) {
		this.reOpened = reOpened;
	}

	public int getEscalationLevel() {
		return escalationLevel;
	}

	public void setEscalationLevel(int escalationLevel) {
		this.escalationLevel = escalationLevel;
	}

	public String getClosedByFunctionaryName() {
		return closedByFunctionaryName;
	}

	public void setClosedByFunctionaryName(String closedByFunctionaryName) {
		this.closedByFunctionaryName = closedByFunctionaryName;
	}

	public int getEscalation2FunctionaryAgeingFromDue() {
		return escalation2FunctionaryAgeingFromDue;
	}

	public void setEscalation2FunctionaryAgeingFromDue(int escalation2FunctionaryAgeingFromDue) {
		this.escalation2FunctionaryAgeingFromDue = escalation2FunctionaryAgeingFromDue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWardNo() {
		return wardNo;
	}

	public void setWardNo(int wardNo) {
		this.wardNo = wardNo;
	}

	public Date getInitialFunctionaryAssigneddate() {
		return initialFunctionaryAssigneddate;
	}

	public void setInitialFunctionaryAssigneddate(Date initialFunctionaryAssigneddate) {
		this.initialFunctionaryAssigneddate = initialFunctionaryAssigneddate;
	}

	public int getLocalityNo() {
		return localityNo;
	}

	public void setLocalityNo(int localityNo) {
		this.localityNo = localityNo;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getEscalation1FunctionaryAssigneddate() {
		return escalation1FunctionaryAssigneddate;
	}

	public void setEscalation1FunctionaryAssigneddate(Date escalation1FunctionaryAssigneddate) {
		this.escalation1FunctionaryAssigneddate = escalation1FunctionaryAssigneddate;
	}

	public int getComplaintPeriod() {
		return complaintPeriod;
	}

	public void setComplaintPeriod(int complaintPeriod) {
		this.complaintPeriod = complaintPeriod;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public int getInProcess() {
		return inProcess;
	}

	public void setInProcess(int inProcess) {
		this.inProcess = inProcess;
	}

	public String getComplainantMobile() {
		return complainantMobile;
	}

	public void setComplainantMobile(String complainantMobile) {
		this.complainantMobile = complainantMobile;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getComplainantEmail() {
		return complainantEmail;
	}

	public void setComplainantEmail(String complainantEmail) {
		this.complainantEmail = complainantEmail;
	}

	public int getInitialFunctionaryIfSLA() {
		return initialFunctionaryIfSLA;
	}

	public void setInitialFunctionaryIfSLA(int initialFunctionaryIfSLA) {
		this.initialFunctionaryIfSLA = initialFunctionaryIfSLA;
	}

	public String getEscalation2FunctionaryName() {
		return escalation2FunctionaryName;
	}

	public void setEscalation2FunctionaryName(String escalation2FunctionaryName) {
		this.escalation2FunctionaryName = escalation2FunctionaryName;
	}

	public String getCityGrade() {
		return cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	public int getSatisfactionIndex() {
		return satisfactionIndex;
	}

	public void setSatisfactionIndex(int satisfactionIndex) {
		this.satisfactionIndex = satisfactionIndex;
	}

	public int getIfClosed() {
		return ifClosed;
	}

	public void setIfClosed(int ifClosed) {
		this.ifClosed = ifClosed;
	}

	public Date getEscalationDate() {
		return escalationDate;
	}

	public void setEscalationDate(Date escalationDate) {
		this.escalationDate = escalationDate;
	}

	public int getCurrentFunctionaryIfSLA() {
		return currentFunctionaryIfSLA;
	}

	public void setCurrentFunctionaryIfSLA(int currentFunctionaryIfSLA) {
		this.currentFunctionaryIfSLA = currentFunctionaryIfSLA;
	}

	public int getInitialFunctionarySLADays() {
		return initialFunctionarySLADays;
	}

	public void setInitialFunctionarySLADays(int initialFunctionarySLADays) {
		this.initialFunctionarySLADays = initialFunctionarySLADays;
	}

	public String getCityRegionName() {
		return cityRegionName;
	}

	public void setCityRegionName(String cityRegionName) {
		this.cityRegionName = cityRegionName;
	}

	public Date getEscalation2FunctionaryAssigneddate() {
		return escalation2FunctionaryAssigneddate;
	}

	public void setEscalation2FunctionaryAssigneddate(Date escalation2FunctionaryAssigneddate) {
		this.escalation2FunctionaryAssigneddate = escalation2FunctionaryAssigneddate;
	}

	public String getLandmarkDetails() {
		return landmarkDetails;
	}

	public void setLandmarkDetails(String landmarkDetails) {
		this.landmarkDetails = landmarkDetails;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getEscalation3FunctionaryName() {
		return escalation3FunctionaryName;
	}

	public void setEscalation3FunctionaryName(String escalation3FunctionaryName) {
		this.escalation3FunctionaryName = escalation3FunctionaryName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getCurrentFunctionarySLADays() {
		return currentFunctionarySLADays;
	}

	public void setCurrentFunctionarySLADays(int currentFunctionarySLADays) {
		this.currentFunctionarySLADays = currentFunctionarySLADays;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReceivingMode() {
		return receivingMode;
	}

	public void setReceivingMode(String receivingMode) {
		this.receivingMode = receivingMode;
	}

	public String getCurrentFunctionaryIsSLA() {
		return currentFunctionaryIsSLA;
	}

	public void setCurrentFunctionaryIsSLA(String currentFunctionaryIsSLA) {
		this.currentFunctionaryIsSLA = currentFunctionaryIsSLA;
	}

	public int getInitialFunctionaryAgeingFromDue() {
		return initialFunctionaryAgeingFromDue;
	}

	public void setInitialFunctionaryAgeingFromDue(int initialFunctionaryAgeingFromDue) {
		this.initialFunctionaryAgeingFromDue = initialFunctionaryAgeingFromDue;
	}

	public int getRegistered() {
		return registered;
	}

	public void setRegistered(int registered) {
		this.registered = registered;
	}

	public int getEscalation1FunctionaryAgeingFromDue() {
		return escalation1FunctionaryAgeingFromDue;
	}

	public void setEscalation1FunctionaryAgeingFromDue(int escalation1FunctionaryAgeingFromDue) {
		this.escalation1FunctionaryAgeingFromDue = escalation1FunctionaryAgeingFromDue;
	}

	public int getEscalation2FunctionarySLADays() {
		return escalation2FunctionarySLADays;
	}

	public void setEscalation2FunctionarySLADays(int escalation2FunctionarySLADays) {
		this.escalation2FunctionarySLADays = escalation2FunctionarySLADays;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public Complaint() {
		// TODO Auto-generated constructor stub
	}

}
