package com.otsi.estodb;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="FinancialVoucher")
@Entity
public class FinancialVoucher {
	
	private String generalledgerid;

	private String vouchermissubschemeid;

	private String ulbgrade;

	private String distname;

	private String gldetailtypename;

	private String vouchermisfundsourcename;

	private Date voucherlastmodifieddate;

	private String regname;

	private String vouchermisschemename;

	private Date voucherdate;

	private Date vouchereffectivedate;

	private String vouchername;

	private String glcreditamount;//

	private String vouchermisfunctionid;

	private String vouchermissubschemename;

	private String vouchermisfunctionaryid;

	private String vouchermoduleid;

	private String budgetaryappnumber;

	private String vouchermissubschemecode;//

	private String voucherstateid;

	private String voucherfundcode;

	private String vouchernumber;

	private String vouchermisid;

	private String vouchermisdivisionid;//

	private String voucheroriginalvcid;

	private String gldescription;

	private String voucherlineid;

	private String vouchercgvn;

	private String ulbname;

	private String voucherfiscalperiodname;

	private String glcodeDescription;

	private String ulbcode;

	private String generalledgerdetailamount;

	private String vouchermodulename;//

	private String vouchermisfunctioncode;

	private String vouchermisdepartmentname;

	private String vouchermisschemecode;

	private String voucherlastmodifiedby;

	private String glcode;

	private String vouchermisschemeid;

	private String gldebitamount;
	@Id
	private String id;

	private String budgetcheckreq;

	private String minorCodeDescription;

	private String glcodeid;

	private String vouchermisfunctionname;

	private Date vouchercreateddate;//

	private String gldetailkeyid;

	private String voucherstatusid;

	private String vouchermissourcepath;

	private String voucherrefvhid;

	private String majorCodeDescription;//

	private String vouchercreatedby;

	private String generalledgerdetailid;

	private String voucherstatusvalue;

	private String gldetailtypeid;

	private String voucherheaderid;//

	private String vouchermisdepartmentcode;

	private Date lastupdated;

	private String voucherfiscalperiodid;

	private String minorcode;

	private String voucherdescription;

	private String vouchermisdepartmentid;

	private String vouchermisfundsourceid;

	private String vouchermisfunctionaryname;

	private String financialyear;

	private String voucherfundname;

	private String majorcode;

	private String voucherfundid;

	private String vouchertype;

	public String getGeneralledgerid() {
		return generalledgerid;
	}

	public void setGeneralledgerid(String generalledgerid) {
		this.generalledgerid = generalledgerid;
	}

	public String getVouchermissubschemeid() {
		return vouchermissubschemeid;
	}

	public void setVouchermissubschemeid(String vouchermissubschemeid) {
		this.vouchermissubschemeid = vouchermissubschemeid;
	}

	public String getUlbgrade() {
		return ulbgrade;
	}

	public void setUlbgrade(String ulbgrade) {
		this.ulbgrade = ulbgrade;
	}

	public String getDistname() {
		return distname;
	}

	public void setDistname(String distname) {
		this.distname = distname;
	}

	public String getGldetailtypename() {
		return gldetailtypename;
	}

	public void setGldetailtypename(String gldetailtypename) {
		this.gldetailtypename = gldetailtypename;
	}

	public String getVouchermisfundsourcename() {
		return vouchermisfundsourcename;
	}

	public void setVouchermisfundsourcename(String vouchermisfundsourcename) {
		this.vouchermisfundsourcename = vouchermisfundsourcename;
	}

	public Date getVoucherlastmodifieddate() {
		return voucherlastmodifieddate;
	}

	public void setVoucherlastmodifieddate(Date voucherlastmodifieddate) {
		this.voucherlastmodifieddate = voucherlastmodifieddate;
	}

	public String getRegname() {
		return regname;
	}

	public void setRegname(String regname) {
		this.regname = regname;
	}

	public String getVouchermisschemename() {
		return vouchermisschemename;
	}

	public void setVouchermisschemename(String vouchermisschemename) {
		this.vouchermisschemename = vouchermisschemename;
	}

	public Date getVoucherdate() {
		return voucherdate;
	}

	public void setVoucherdate(Date voucherdate) {
		this.voucherdate = voucherdate;
	}

	public Date getVouchereffectivedate() {
		return vouchereffectivedate;
	}

	public void setVouchereffectivedate(Date vouchereffectivedate) {
		this.vouchereffectivedate = vouchereffectivedate;
	}

	public String getVouchername() {
		return vouchername;
	}

	public void setVouchername(String vouchername) {
		this.vouchername = vouchername;
	}

	public String getGlcreditamount() {
		return glcreditamount;
	}

	public void setGlcreditamount(String glcreditamount) {
		this.glcreditamount = glcreditamount;
	}

	public String getVouchermisfunctionid() {
		return vouchermisfunctionid;
	}

	public void setVouchermisfunctionid(String vouchermisfunctionid) {
		this.vouchermisfunctionid = vouchermisfunctionid;
	}

	public String getVouchermissubschemename() {
		return vouchermissubschemename;
	}

	public void setVouchermissubschemename(String vouchermissubschemename) {
		this.vouchermissubschemename = vouchermissubschemename;
	}

	public String getVouchermisfunctionaryid() {
		return vouchermisfunctionaryid;
	}

	public void setVouchermisfunctionaryid(String vouchermisfunctionaryid) {
		this.vouchermisfunctionaryid = vouchermisfunctionaryid;
	}

	public String getVouchermoduleid() {
		return vouchermoduleid;
	}

	public void setVouchermoduleid(String vouchermoduleid) {
		this.vouchermoduleid = vouchermoduleid;
	}

	public String getBudgetaryappnumber() {
		return budgetaryappnumber;
	}

	public void setBudgetaryappnumber(String budgetaryappnumber) {
		this.budgetaryappnumber = budgetaryappnumber;
	}

	public String getVouchermissubschemecode() {
		return vouchermissubschemecode;
	}

	public void setVouchermissubschemecode(String vouchermissubschemecode) {
		this.vouchermissubschemecode = vouchermissubschemecode;
	}

	public String getVoucherstateid() {
		return voucherstateid;
	}

	public void setVoucherstateid(String voucherstateid) {
		this.voucherstateid = voucherstateid;
	}

	public String getVoucherfundcode() {
		return voucherfundcode;
	}

	public void setVoucherfundcode(String voucherfundcode) {
		this.voucherfundcode = voucherfundcode;
	}

	public String getVouchernumber() {
		return vouchernumber;
	}

	public void setVouchernumber(String vouchernumber) {
		this.vouchernumber = vouchernumber;
	}

	public String getVouchermisid() {
		return vouchermisid;
	}

	public void setVouchermisid(String vouchermisid) {
		this.vouchermisid = vouchermisid;
	}

	public String getVouchermisdivisionid() {
		return vouchermisdivisionid;
	}

	public void setVouchermisdivisionid(String vouchermisdivisionid) {
		this.vouchermisdivisionid = vouchermisdivisionid;
	}

	public String getVoucheroriginalvcid() {
		return voucheroriginalvcid;
	}

	public void setVoucheroriginalvcid(String voucheroriginalvcid) {
		this.voucheroriginalvcid = voucheroriginalvcid;
	}

	public String getGldescription() {
		return gldescription;
	}

	public void setGldescription(String gldescription) {
		this.gldescription = gldescription;
	}

	public String getVoucherlineid() {
		return voucherlineid;
	}

	public void setVoucherlineid(String voucherlineid) {
		this.voucherlineid = voucherlineid;
	}

	public String getVouchercgvn() {
		return vouchercgvn;
	}

	public void setVouchercgvn(String vouchercgvn) {
		this.vouchercgvn = vouchercgvn;
	}

	public String getUlbname() {
		return ulbname;
	}

	public void setUlbname(String ulbname) {
		this.ulbname = ulbname;
	}

	public String getVoucherfiscalperiodname() {
		return voucherfiscalperiodname;
	}

	public void setVoucherfiscalperiodname(String voucherfiscalperiodname) {
		this.voucherfiscalperiodname = voucherfiscalperiodname;
	}

	public String getGlcodeDescription() {
		return glcodeDescription;
	}

	public void setGlcodeDescription(String glcodeDescription) {
		this.glcodeDescription = glcodeDescription;
	}

	public String getUlbcode() {
		return ulbcode;
	}

	public void setUlbcode(String ulbcode) {
		this.ulbcode = ulbcode;
	}

	public String getGeneralledgerdetailamount() {
		return generalledgerdetailamount;
	}

	public void setGeneralledgerdetailamount(String generalledgerdetailamount) {
		this.generalledgerdetailamount = generalledgerdetailamount;
	}

	public String getVouchermodulename() {
		return vouchermodulename;
	}

	public void setVouchermodulename(String vouchermodulename) {
		this.vouchermodulename = vouchermodulename;
	}

	public String getVouchermisfunctioncode() {
		return vouchermisfunctioncode;
	}

	public void setVouchermisfunctioncode(String vouchermisfunctioncode) {
		this.vouchermisfunctioncode = vouchermisfunctioncode;
	}

	public String getVouchermisdepartmentname() {
		return vouchermisdepartmentname;
	}

	public void setVouchermisdepartmentname(String vouchermisdepartmentname) {
		this.vouchermisdepartmentname = vouchermisdepartmentname;
	}

	public String getVouchermisschemecode() {
		return vouchermisschemecode;
	}

	public void setVouchermisschemecode(String vouchermisschemecode) {
		this.vouchermisschemecode = vouchermisschemecode;
	}

	public String getVoucherlastmodifiedby() {
		return voucherlastmodifiedby;
	}

	public void setVoucherlastmodifiedby(String voucherlastmodifiedby) {
		this.voucherlastmodifiedby = voucherlastmodifiedby;
	}

	public String getGlcode() {
		return glcode;
	}

	public void setGlcode(String glcode) {
		this.glcode = glcode;
	}

	public String getVouchermisschemeid() {
		return vouchermisschemeid;
	}

	public void setVouchermisschemeid(String vouchermisschemeid) {
		this.vouchermisschemeid = vouchermisschemeid;
	}

	public String getGldebitamount() {
		return gldebitamount;
	}

	public void setGldebitamount(String gldebitamount) {
		this.gldebitamount = gldebitamount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBudgetcheckreq() {
		return budgetcheckreq;
	}

	public void setBudgetcheckreq(String budgetcheckreq) {
		this.budgetcheckreq = budgetcheckreq;
	}

	public String getMinorCodeDescription() {
		return minorCodeDescription;
	}

	public void setMinorCodeDescription(String minorCodeDescription) {
		this.minorCodeDescription = minorCodeDescription;
	}

	public String getGlcodeid() {
		return glcodeid;
	}

	public void setGlcodeid(String glcodeid) {
		this.glcodeid = glcodeid;
	}

	public String getVouchermisfunctionname() {
		return vouchermisfunctionname;
	}

	public void setVouchermisfunctionname(String vouchermisfunctionname) {
		this.vouchermisfunctionname = vouchermisfunctionname;
	}

	public Date getVouchercreateddate() {
		return vouchercreateddate;
	}

	public void setVouchercreateddate(Date vouchercreateddate) {
		this.vouchercreateddate = vouchercreateddate;
	}

	public String getGldetailkeyid() {
		return gldetailkeyid;
	}

	public void setGldetailkeyid(String gldetailkeyid) {
		this.gldetailkeyid = gldetailkeyid;
	}

	public String getVoucherstatusid() {
		return voucherstatusid;
	}

	public void setVoucherstatusid(String voucherstatusid) {
		this.voucherstatusid = voucherstatusid;
	}

	public String getVouchermissourcepath() {
		return vouchermissourcepath;
	}

	public void setVouchermissourcepath(String vouchermissourcepath) {
		this.vouchermissourcepath = vouchermissourcepath;
	}

	public String getVoucherrefvhid() {
		return voucherrefvhid;
	}

	public void setVoucherrefvhid(String voucherrefvhid) {
		this.voucherrefvhid = voucherrefvhid;
	}

	public String getMajorCodeDescription() {
		return majorCodeDescription;
	}

	public void setMajorCodeDescription(String majorCodeDescription) {
		this.majorCodeDescription = majorCodeDescription;
	}

	public String getVouchercreatedby() {
		return vouchercreatedby;
	}

	public void setVouchercreatedby(String vouchercreatedby) {
		this.vouchercreatedby = vouchercreatedby;
	}

	public String getGeneralledgerdetailid() {
		return generalledgerdetailid;
	}

	public void setGeneralledgerdetailid(String generalledgerdetailid) {
		this.generalledgerdetailid = generalledgerdetailid;
	}

	public String getVoucherstatusvalue() {
		return voucherstatusvalue;
	}

	public void setVoucherstatusvalue(String voucherstatusvalue) {
		this.voucherstatusvalue = voucherstatusvalue;
	}

	public String getGldetailtypeid() {
		return gldetailtypeid;
	}

	public void setGldetailtypeid(String gldetailtypeid) {
		this.gldetailtypeid = gldetailtypeid;
	}

	public String getVoucherheaderid() {
		return voucherheaderid;
	}

	public void setVoucherheaderid(String voucherheaderid) {
		this.voucherheaderid = voucherheaderid;
	}

	public String getVouchermisdepartmentcode() {
		return vouchermisdepartmentcode;
	}

	public void setVouchermisdepartmentcode(String vouchermisdepartmentcode) {
		this.vouchermisdepartmentcode = vouchermisdepartmentcode;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getVoucherfiscalperiodid() {
		return voucherfiscalperiodid;
	}

	public void setVoucherfiscalperiodid(String voucherfiscalperiodid) {
		this.voucherfiscalperiodid = voucherfiscalperiodid;
	}

	public String getMinorcode() {
		return minorcode;
	}

	public void setMinorcode(String minorcode) {
		this.minorcode = minorcode;
	}

	public String getVoucherdescription() {
		return voucherdescription;
	}

	public void setVoucherdescription(String voucherdescription) {
		this.voucherdescription = voucherdescription;
	}

	public String getVouchermisdepartmentid() {
		return vouchermisdepartmentid;
	}

	public void setVouchermisdepartmentid(String vouchermisdepartmentid) {
		this.vouchermisdepartmentid = vouchermisdepartmentid;
	}

	public String getVouchermisfundsourceid() {
		return vouchermisfundsourceid;
	}

	public void setVouchermisfundsourceid(String vouchermisfundsourceid) {
		this.vouchermisfundsourceid = vouchermisfundsourceid;
	}

	public String getVouchermisfunctionaryname() {
		return vouchermisfunctionaryname;
	}

	public void setVouchermisfunctionaryname(String vouchermisfunctionaryname) {
		this.vouchermisfunctionaryname = vouchermisfunctionaryname;
	}

	public String getFinancialyear() {
		return financialyear;
	}

	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}

	public String getVoucherfundname() {
		return voucherfundname;
	}

	public void setVoucherfundname(String voucherfundname) {
		this.voucherfundname = voucherfundname;
	}

	public String getMajorcode() {
		return majorcode;
	}

	public void setMajorcode(String majorcode) {
		this.majorcode = majorcode;
	}

	public String getVoucherfundid() {
		return voucherfundid;
	}

	public void setVoucherfundid(String voucherfundid) {
		this.voucherfundid = voucherfundid;
	}

	public String getVouchertype() {
		return vouchertype;
	}

	public FinancialVoucher() {

	}

	public void setVouchertype(String vouchertype) {
		this.vouchertype = vouchertype;
	}

}
