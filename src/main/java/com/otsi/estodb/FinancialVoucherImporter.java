package com.otsi.estodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.FlushModeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FinancialVoucherImporter {
	private static Session session = null;

	public static void main(String[] args) throws IOException, JSONException, ParseException {
		int totalCount = checkTotalCount();
		System.out.println(totalCount);
		//SendHTTPRequest.createSchema();
		bulkInsert(totalCount);
		System.out.println("Import Finished");
	}

	public static int checkTotalCount() throws IOException {

		String url = "http://search-api.egovernments.org/financialsvoucherdata/_search?q=*&pretty&sort=voucherdate:asc";

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Authorization", "Basic c2hhc2hhbmsuZzpyZTJzaGFoeG9oOWllem9S");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer builder = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			builder.append(inputLine);
		}
		in.close();
		String result = builder.toString();
		JSONObject jsonObj = new JSONObject(result);
		JSONObject hitsObj = (JSONObject) jsonObj.get("hits");
		int totalCount = (Integer) hitsObj.get("total");
		return totalCount;
	}

	public static void bulkInsert(int toatlCount) throws IOException, JSONException, ParseException {
		int iteratorCount = toatlCount / 10000;
		for (int i = 2; i <= iteratorCount; i++) {
			String from = String.valueOf(i * 10000);
			String url = "http://search-api.egovernments.org/financialsvoucherdata/_search?q=*&pretty&sort=voucherdate:asc&size=10000&from="
					+ from;
			;
			System.out.println(url);
			insertBatch(url, from);

		}
	}

	public static Session getSession() {

		String configFileName = "hibernate-sqlserver.cfg.xml";

		// Create the ServiceRegistry from hibernate-xxx.cfg.xml
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
				.configure(configFileName).build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.setFlushMode(FlushModeType.COMMIT);
		return session;
	}

	public static void insertBatch(String url, String from) throws IOException, JSONException, ParseException {
		session = null;
		if (session == null) {
			session = getSession();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
		// 2018-03-30T13:53:50.067Z
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Authorization", "Basic c2hhc2hhbmsuZzpyZTJzaGFoeG9oOWllem9S");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer builder = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			builder.append(inputLine);
		}
		in.close();
		String result = builder.toString();
		JSONObject jsonObj = new JSONObject(result);
		Transaction tr = session.beginTransaction();
		JSONObject hitsObj = (JSONObject) jsonObj.get("hits");
		JSONArray res = (JSONArray) hitsObj.get("hits");
		System.out.println(from);
		for (int i = 0; i < res.length(); i++) {
			FinancialVoucher fv = new FinancialVoucher();
			JSONObject str = res.getJSONObject(i);
			JSONObject obj = (JSONObject) str.get("_source");

			if (!str.isNull("_id")) {
				fv.setId(str.getString("_id"));
				//System.out.println(str.get("_id"));
			}
			if (!obj.isNull("voucherlastmodifieddate")) {
				fv.setVoucherlastmodifieddate(
						formatter.parse(((String) obj.get("voucherlastmodifieddate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("voucherdate")) {
				fv.setVoucherdate(formatter.parse(((String) obj.get("voucherdate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("vouchereffectivedate")) {
				fv.setVouchereffectivedate(
						formatter.parse(((String) obj.get("vouchereffectivedate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("vouchercreateddate")) {
				fv.setVouchercreateddate(
						formatter.parse(((String) obj.get("vouchercreateddate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("lastupdated")) {
				fv.setLastupdated(formatter.parse(((String) obj.get("lastupdated")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("generalledgerid")) {
				fv.setGeneralledgerid((Number) obj.get("generalledgerid") + "");
			}
			if (!obj.isNull("vouchermissubschemeid")) {
				fv.setVouchermissubschemeid(obj.getString("vouchermissubschemeid"));
			}
			if (!obj.isNull("ulbgrade")) {
				fv.setUlbgrade(obj.getString("ulbgrade"));
			}
			if (!obj.isNull("distname")) {
				fv.setDistname(obj.getString("distname"));
			}
			if (!obj.isNull("gldetailtypename")) {
				fv.setGldetailtypename(obj.getString("gldetailtypename"));
			}
			if (!obj.isNull("vouchermisfundsourcename")) {
				fv.setVouchermisfundsourcename(obj.getString("vouchermisfundsourcename"));
			}
			if (!obj.isNull("regname")) {
				fv.setRegname(obj.getString("regname"));
			}
			if (!obj.isNull("vouchermisschemename")) {
				fv.setVouchermisschemename(obj.getString("vouchermisschemename"));
			}
			if (!obj.isNull("vouchername")) {
				fv.setVouchername(obj.getString("vouchername"));
			}
			if (!obj.isNull("glcreditamount")) {
				fv.setGlcreditamount((Number) obj.get("glcreditamount") + "");
			}
			if (!obj.isNull("vouchermisfunctionid")) {
				fv.setVouchermisfunctionid((Number) obj.get("vouchermisfunctionid") + "");
			}
			if (!obj.isNull("vouchermissubschemename")) {
				fv.setVouchermissubschemename(obj.getString("vouchermissubschemename"));
			}
			if (!obj.isNull("vouchermisfunctionaryid")) {
				fv.setVouchermisfunctionaryid(obj.getString("vouchermisfunctionaryid"));
			}
			if (!obj.isNull("vouchermoduleid")) {
				fv.setVouchermoduleid((Number) obj.get("vouchermoduleid") + "");
			}
			if (!obj.isNull("budgetaryappnumber")) {
				fv.setBudgetaryappnumber(obj.getString("budgetaryappnumber"));
			}
			if (!obj.isNull("vouchermissubschemecode")) {
				fv.setVouchermissubschemecode(obj.getString("vouchermissubschemecode"));
			}
			if (!obj.isNull("voucherstateid")) {
				fv.setVoucherstateid((Number) obj.get("voucherstateid") + "");
			}
			if (!obj.isNull("voucherfundcode")) {
				fv.setVoucherfundcode(obj.getString("voucherfundcode"));
			}
			if (!obj.isNull("vouchernumber")) {
				fv.setVouchernumber(obj.getString("vouchernumber"));
			}
			if (!obj.isNull("vouchermisid")) {
				fv.setVouchermisid((Number) obj.get("vouchermisid") + "");
			}
			if (!obj.isNull("vouchermisdivisionid")) {
				fv.setVouchermisdivisionid((Number) obj.get("vouchermisdivisionid") + "");
			}
			if (!obj.isNull("voucheroriginalvcid")) {
				fv.setVoucheroriginalvcid((Number) obj.get("voucheroriginalvcid") + "");
			}
			if (!obj.isNull("gldescription")) {
				fv.setGldescription(obj.getString("gldescription"));
			}
			if (!obj.isNull("voucherlineid")) {
				fv.setVoucherlineid((Number) obj.get("voucherlineid") + "");
			} // vouchercgvn
			if (!obj.isNull("vouchercgvn")) {
				fv.setVouchercgvn(obj.getString("vouchercgvn"));
			}
			if (!obj.isNull("ulbname")) {
				fv.setUlbname(obj.getString("ulbname"));
			}
			if (!obj.isNull("voucherfiscalperiodname")) {
				fv.setVoucherfiscalperiodname(obj.getString("voucherfiscalperiodname"));
			}
			if (!obj.isNull("glcodeDescription")) {
				fv.setGlcodeDescription(obj.getString("glcodeDescription"));
			}
			if (!obj.isNull("ulbcode")) {
				fv.setUlbcode(obj.getString("ulbcode"));
			}
			if (!obj.isNull("generalledgerdetailamount")) {
				fv.setGeneralledgerdetailamount((Number) obj.get("generalledgerdetailamount") + "");
			}
			if (!obj.isNull("vouchermodulename")) {
				fv.setVouchermodulename(obj.getString("vouchermodulename"));
			} // vouchermisfunctioncode
			if (!obj.isNull("vouchermisfunctioncode")) {
				fv.setVouchermisfunctioncode(obj.getString("vouchermisfunctioncode"));
			}
			if (!obj.isNull("vouchermisdepartmentname")) {
				fv.setVouchermisdepartmentname(obj.getString("vouchermisdepartmentname"));
			}
			if (!obj.isNull("vouchermisschemecode")) {
				fv.setVouchermisschemecode(obj.getString("vouchermisschemecode"));
			}
			if (!obj.isNull("voucherlastmodifiedby")) {
				fv.setVoucherlastmodifiedby(obj.getString("voucherlastmodifiedby"));
			}
			if (!obj.isNull("glcode")) {
				fv.setGlcode(obj.getString("glcode"));
			}
			if (!obj.isNull("vouchermisschemeid")) {
				fv.setVouchermisschemeid((Number) obj.get("vouchermisschemeid") + "");
			}
			if (!obj.isNull("gldebitamount")) {
				fv.setGldebitamount((Number) obj.get("gldebitamount") + "");
			}
			if (!obj.isNull("budgetcheckreq")) {
				fv.setBudgetcheckreq((Boolean) obj.get("budgetcheckreq") + "");
			}
			if (!obj.isNull("minorCodeDescription")) {
				fv.setMinorCodeDescription(obj.getString("minorCodeDescription"));
			}
			if (!obj.isNull("glcodeid")) {
				fv.setGlcodeid((Number) obj.get("glcodeid") + "");
			}
			if (!obj.isNull("vouchermisfunctionname")) {
				fv.setVouchermisfunctionname(obj.getString("vouchermisfunctionname"));
			}
			if (!obj.isNull("gldetailkeyid")) {
				fv.setGldetailkeyid((Number) obj.get("gldetailkeyid") + "");

			}
			if (!obj.isNull("voucherstatusid")) {
				fv.setVoucherstatusid((Number) obj.get("voucherstatusid") + "");
			}
			if (!obj.isNull("vouchermissourcepath")) {
				fv.setVouchermissourcepath(obj.getString("vouchermissourcepath"));
			}
			if (!obj.isNull("voucherrefvhid")) {
				fv.setVoucherrefvhid(obj.getString("voucherrefvhid"));
			}
			if (!obj.isNull("majorCodeDescription")) {
				fv.setMajorCodeDescription(obj.getString("majorCodeDescription"));
			}
			if (!obj.isNull("vouchercreatedby")) {
				fv.setVouchercreatedby(obj.getString("vouchercreatedby"));
			}
			if (!obj.isNull("generalledgerdetailid")) {
				fv.setGeneralledgerdetailid((Number) obj.get("generalledgerdetailid") + "");
			}
			if (!obj.isNull("voucherstatusvalue")) {
				fv.setVoucherstatusvalue(obj.getString("voucherstatusvalue"));
			}
			if (!obj.isNull("gldetailtypeid")) {
				fv.setGldetailtypeid((Number) obj.get("gldetailtypeid") + "");
			}
			if (!obj.isNull("voucherheaderid")) {
				fv.setVoucherheaderid((Number) obj.get("voucherheaderid") + "");
			}
			if (!obj.isNull("vouchermisdepartmentcode")) {
				fv.setVouchermisdepartmentcode(obj.getString("vouchermisdepartmentcode"));
			}
			if (!obj.isNull("voucherfiscalperiodid")) {
				fv.setVoucherfiscalperiodid((Number) obj.get("voucherfiscalperiodid") + "");
			}
			if (!obj.isNull("minorcode")) {
				fv.setMinorcode(obj.getString("minorcode"));
			}
			if (!obj.isNull("voucherdescription")) {
				fv.setVoucherdescription(obj.getString("voucherdescription"));
			}
			if (!obj.isNull("vouchermisdepartmentid")) {
				fv.setVouchermisdepartmentid((Number) obj.get("vouchermisdepartmentid") + "");
			}
			if (!obj.isNull("vouchermisfundsourceid")) {
				fv.setVouchermisfundsourceid((Number) obj.get("vouchermisfundsourceid") + "");
			}
			if (!obj.isNull("vouchermisfunctionaryname")) {
				fv.setVouchermisfunctionaryname(obj.getString("vouchermisfunctionaryname"));
			}
			if (!obj.isNull("financialyear")) {
				fv.setFinancialyear(obj.getString("financialyear"));
			}
			if (!obj.isNull("voucherfundname")) {
				fv.setVoucherfundname(obj.getString("voucherfundname"));
			}
			if (!obj.isNull("majorcode")) {
				fv.setMajorcode(obj.getString("majorcode"));
			}
			if (!obj.isNull("voucherfundid")) {
				fv.setVoucherfundid((Number) obj.get("voucherfundid") + "");
			}
			if (!obj.isNull("vouchertype")) {
				fv.setVouchertype(obj.getString("vouchertype"));
			}
			try {
				loadToDatabase(fv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // for

		session.flush();
		session.clear();
		try {
			tr.commit();
			System.out.println("Flushed");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(from);
		}
		tr = session.beginTransaction();
		session.close();

	}

	public static void loadToDatabase(FinancialVoucher fv) {

		try {
			session.save(fv);
		} catch (Exception e) {
			session.saveOrUpdate(fv);
		}

	}

}
