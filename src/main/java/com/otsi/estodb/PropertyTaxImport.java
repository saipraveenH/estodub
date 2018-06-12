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

public class PropertyTaxImport {
	private static Session session = null;

	public static void main(String[] args) throws IOException, JSONException, ParseException {
		int totalCount = checkTotalCount();
		SendHTTPRequest.createSchema();
		System.out.println(totalCount);
		bulkInsert(totalCount);
		System.out.println("Import Finished");
	}

	public static int checkTotalCount() throws IOException {

		String url = "http://search-api.egovernments.org/propertytax/_search?q=*&pretty&sort=consumerCode:asc";

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
		for (int i = 0; i <= iteratorCount; i++) {
			String from = String.valueOf(i * 10000);
			String url = "http://search-api.egovernments.org/propertytax/_search?q=*&pretty&sort=consumerCode:asc&size=10000&from="
					+ from;
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

		for (int i = 0; i < 10000; i++) {
			PropertyTax pt = new PropertyTax();
			// System.out.println("inside for " + i);
			JSONObject str = res.getJSONObject(i);
			// System.out.println(str);
			JSONObject obj = (JSONObject) str.get("_source");
			if (obj.has("boundaryGeo") && !obj.isNull("boundaryGeo")) {
				JSONObject boundary = (JSONObject) obj.get("boundaryGeo");
				if (!boundary.isNull("lat")) {
					pt.setBoundaryGeo_lat((Double) boundary.get("lat") + "");
				}
				if (!boundary.isNull("lon")) {
					pt.setBoundaryGeo_lon((Double) boundary.get("lon") + "");
				}
			}
			if (obj.has("propertyGeo") && !obj.isNull("propertyGeo")) {
				JSONObject property = (JSONObject) obj.get("propertyGeo");
				if (!property.isNull("lat")) {
					pt.setPropertyGeo_lat((Double) property.get("lat") + "");
				}
				if (!property.isNull("lon")) {
					pt.setPropertyGeo_lon((Double) property.get("lon") + "");

				}
			}
			if (!obj.isNull("totalDemand")) {
				pt.setTotalDemand((Double) obj.get("totalDemand") + "");
			}
			if (!obj.isNull("firstInstallmentCollection")) {
				pt.setFirstInstallmentCollection((Double) obj.get("firstInstallmentCollection") + "");
			}
			if (!obj.isNull("secondInstallmentDemand")) {
				pt.setSecondInstallmentDemand((Double) obj.get("secondInstallmentDemand") + "");
			}
			if (!obj.isNull("cityName")) {
				pt.setCityName(obj.getString("cityName"));
			}
			if (!obj.isNull("propertyAddress")) {
				pt.setPropertyAddress(obj.getString("propertyAddress"));
			}
			if (!obj.isNull("cityCode")) {
				pt.setCityCode(obj.getString("cityCode"));
			}

			if (!obj.isNull("arrearInterestCollection")) {
				pt.setArrearInterestCollection((Double) obj.get("arrearInterestCollection") + "");
			}

			if (!obj.isNull("revenueBlock")) {
				pt.setRevenueBlock(obj.getString("revenueBlock"));
			}
			if (!obj.isNull("currentInterestCollection")) {
				pt.setCurrentInterestCollection((Double) obj.get("currentInterestCollection") + "");
			}

			if (!obj.isNull("ptAssesmentNo")) {
				pt.setPtAssesmentNo(obj.getString("ptAssesmentNo"));
			}

			if (!obj.isNull("locationName")) {
				pt.setLocationName(obj.getString("locationName"));
			}

			if (!obj.isNull("totalCollection")) {
				pt.setTotalCollection((Double) obj.get("totalCollection") + "");
			}
			if (!obj.isNull("arrearBalance")) {
				pt.setArrearBalance((Double) obj.get("arrearBalance") + "");
			}

			if (!obj.isNull("propertyUsage")) {
				pt.setPropertyUsage(obj.getString("propertyUsage"));
			}
			if (!obj.isNull("arvAmount")) {
				pt.setArvAmount((Double) obj.get("arvAmount") + "");
			}
			if (!obj.isNull("currentInterestDemand")) {
				pt.setCurrentInterestDemand((Double) obj.get("currentInterestDemand") + "");
			}
			if (!obj.isNull("consumerName")) {
				pt.setConsumerName(obj.getString("consumerName"));
			}
			if (!obj.isNull("propertyCategory")) {
				pt.setPropertyCategory(obj.getString("propertyCategory"));
			}
			if (!obj.isNull("annualDemand")) {
				pt.setAnnualDemand((Double) obj.get("annualDemand") + "");
			}
			if (!obj.isNull("adjustment")) {
				pt.setAdjustment((Double) obj.get("adjustment") + "");
			}
			if (!obj.isNull("adminWardName")) {
				pt.setAdminWardName(obj.getString("adminWardName"));
			}
			if (!obj.isNull("billCollector")) {
				pt.setBillCollector(obj.getString("billCollector"));
			}
			if (!obj.isNull("rebate")) {
				pt.setRebate((Double) obj.get("rebate") + "");
			}
			if (!obj.isNull("duePeriod")) {
				pt.setDuePeriod(obj.getString("duePeriod"));
			}
			if (!obj.isNull("isExempted")) {
				pt.setIsExempted((Boolean) obj.get("isExempted") + "");
			}
			if (!obj.isNull("cityGrade")) {
				pt.setCityGrade(obj.getString("cityGrade"));
			}
			if (!obj.isNull("consumerType")) {
				pt.setConsumerType(obj.getString("consumerType"));
			}
			if (!obj.isNull("arrearDemand")) {
				pt.setArrearDemand((Double) obj.get("arrearDemand") + "");
			}
			if (!obj.isNull("totalBalance")) {
				pt.setTotalBalance((Double) obj.get("totalBalance") + "");
			}
			if (!obj.isNull("annualCollection")) {
				pt.setAnnualCollection((Double) obj.get("annualCollection") + "");
			}
			if (!obj.isNull("regionName")) {
				pt.setRegionName(obj.getString("regionName"));
			}
			if (!obj.isNull("annualBalance")) {
				pt.setAnnualBalance((Double) obj.get("annualBalance") + "");
			}
			if (!obj.isNull("revZoneName")) {
				pt.setRevZoneName(obj.getString("revZoneName"));
			}
			if (!obj.isNull("firstInstallmentDemand")) {
				pt.setFirstInstallmentDemand((Double) obj.get("firstInstallmentDemand") + "");
			}
			if (!obj.isNull("isActive")) {
				pt.setIsActive((Boolean) obj.get("isActive") + "");
			}
			if (!obj.isNull("districtName")) {
				pt.setDistrictName(obj.getString("districtName"));
			}
			if (!obj.isNull("aadhaarNumber")) {
				pt.setAadhaarNumber(obj.getString("aadhaarNumber"));
			}
			if (!obj.isNull("consumerCode")) {
				pt.setConsumerCode(obj.getString("consumerCode"));
			}
			if (!obj.isNull("secondInstallmentCollection")) {
				pt.setSecondInstallmentCollection((Double) obj.get("secondInstallmentCollection") + "");
			}
			if (!obj.isNull("builtupArea")) {
				pt.setBuiltupArea((Double) obj.get("builtupArea") + "");
			}
			if (!obj.isNull("sitalArea")) {
				pt.setBuiltupArea((Double) obj.get("sitalArea") + "");
			}
			if (!obj.isNull("revenueWard")) {
				pt.setRevenueWard(obj.getString("revenueWard"));
			}
			if (!obj.isNull("isUnderCourtcase")) {
				pt.setIsUnderCourtcase((Boolean) obj.get("isUnderCourtcase") + "");
			}
			if (!obj.isNull("mobileNumber")) {
				pt.setMobileNumber(obj.getString("mobileNumber"));
			}
			if (!obj.isNull("advance")) {
				pt.setAdvance((Double) obj.get("advance") + "");
			}
			if (!obj.isNull("arrearCollection")) {
				pt.setArrearCollection((Double) obj.get("arrearCollection") + "");
			}
			// System.out.println(i);
			session.saveOrUpdate(pt);
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		} // for loop
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

	public static void loadToDatabase(FinancialVoucher pt) {

		try {
			session.save(pt);
		} catch (Exception e) {
			session.saveOrUpdate(pt);
		}

	}
}
