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
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApplicationsImport {
	private static Session session = null;

	public static void main(String[] args) throws IOException, JSONException, ParseException {
		int totalCount = checkTotalCount();
		SendHTTPRequest.createSchema();
		bulkInsert(totalCount);
		System.out.println("Import Finished");
	}

	public static int checkTotalCount() throws IOException {

		String url = "http://search-api.egovernments.org/applications/_search?&pretty&q=*";

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
			String url = "http://search-api.egovernments.org/applications/_search?sort=createdDate:asc&pretty&q=*&size=10000&from="
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
		// org.hibernate.Transaction tr = session.beginTransaction();
		// List<Complaint> complaints = new ArrayList<Complaint>();
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
		// System.out.println(res.length());
		for (int i = 0; i < res.length(); i++) {
			Applications apps = new Applications();
			// Complaint complaint = new Complaint();
			// System.out.println("inside for " +i);
			JSONObject str = res.getJSONObject(i);
			// System.out.println(str.get("_id"));
			JSONObject obj = (JSONObject) str.get("_source");

			if (!obj.isNull("id")) {
				apps.setId((String) obj.get("id"));
			}
			if (!obj.isNull("moduleName")) {
				apps.setModuleName(obj.getString("moduleName"));
			}
			if (!obj.isNull("applicationNumber")) {
				apps.setApplicationNumber(obj.getString("applicationNumber"));
			}
			if (!obj.isNull("applicationDate")) {
				apps.setApplicationDate(
						formatter.parse(((String) obj.get("applicationDate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("applicationType")) {
				apps.setApplicationType(obj.getString("applicationType"));
			}
			if (!obj.isNull("applicantName")) {
				apps.setApplicantName(obj.getString("applicantName"));
			}
			if (!obj.isNull("applicantAddress")) {
				apps.setApplicantAddress(obj.getString("applicantAddress"));
			}
			if (!obj.isNull("disposalDate")) {
				apps.setDisposalDate(formatter.parse(((String) obj.get("disposalDate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("status")) {
				apps.setStatus(obj.getString("status"));
			}
			if (!obj.isNull("url")) {
				apps.setUrl(obj.getString("url"));

			}
			if (!obj.isNull("consumerCode")) {
				apps.setConsumerCode(obj.getString("consumerCode"));
			}
			if (!obj.isNull("mobileNumber")) {
				apps.setMobileNumber(obj.getString("mobileNumber"));
			}
			if (!obj.isNull("ownerName")) {
				apps.setOwnerName(obj.getString("ownerName"));
			}
			if (!obj.isNull("aadharNumber")) {
				apps.setAadharNumber(obj.getString("aadharNumber"));
			}
			if (!obj.isNull("elapsedDays")) {
				apps.setElapsedDays(obj.getInt("elapsedDays"));
			}
			if (!obj.isNull("closed")) {
				apps.setClosed(obj.getString("closed"));
			}
			if (!obj.isNull("approved")) {
				apps.setApproved(obj.getString("approved"));
			}
			if (!obj.isNull("channel")) {
				apps.setChannel(obj.getString("channel"));
			}
			if (!obj.isNull("cityCode")) {
				apps.setCityCode(obj.getString("cityCode"));
			}
			if (!obj.isNull("cityName")) {
				apps.setCityName(obj.getString("cityName"));
			}
			if (!obj.isNull("cityGrade")) {
				apps.setCityGrade(obj.getString("cityGrade"));
			}
			if (!obj.isNull("districtName")) {
				apps.setDistrictName(obj.getString("districtName"));
			} // regionName
			if (!obj.isNull("regionName")) {
				apps.setRegionName(obj.getString("regionName"));
			}
			if (!obj.isNull("isClosed")) {
				apps.setIsClosed(obj.getInt("isClosed"));
			}
			if (!obj.isNull("sla")) {
				apps.setSla(obj.getInt("sla"));
			}
			if (!obj.isNull("slaGap")) {
				apps.setSlaGap(obj.getInt("slaGap"));
			}
			if (!obj.isNull("createdDate")) {
				apps.setCreatedDate(formatter.parse(((String) obj.get("createdDate")).replaceAll("Z$", "+0000")));
			}

			try {
				loadToDatabase(apps);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // for each matched search query convert it to complaint and add to arrayList

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

	public static void loadToDatabase(Applications apps) {

		try {
			session.save(apps);
		} catch (Exception e) {
			session.saveOrUpdate(apps);
		}

	}
}
