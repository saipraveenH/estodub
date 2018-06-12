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

public class WaterChargesImporter {
	private static Session session = null;

	public static void main(String[] args) throws IOException, JSONException, ParseException {
		int totalCount = checkTotalCount();
		System.out.println(totalCount);
		bulkInsert(totalCount);
		System.out.println("Import Finished");
	}

	public static int checkTotalCount() throws IOException {

		String url = "http://search-api.egovernments.org/watercharges/_search?&q=*&pretty&sort=common.createdDate:asc";

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
			String url = "http://search-api.egovernments.org/watercharges/_search?&q=*&pretty&sort=common.createdDate:asc&size=10000&from="
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

		// 2015-12-07T05:16+0000
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
			WaterCharges wc = new WaterCharges();
			// Complaint complaint = new Complaint();
			// System.out.println("inside for " +i);
			JSONObject str = res.getJSONObject(i);
			// System.out.println(str.get("_id"));
			JSONObject obj = (JSONObject) str.get("_source");
			JSONObject common = (JSONObject) obj.get("common");
			JSONObject clauses = (JSONObject) obj.get("clauses");
			JSONObject searchable = (JSONObject) obj.get("searchable");
			if (!common.isNull("createdDate")) {
				try {
					wc.setCreatedDate(formatter.parse(((String) common.get("createdDate")).replaceAll("Z$", "+0000")));
					// System.out.println(common.get("createdDate"));
				} catch (Exception e) {
					wc.setCreatedDate(null);
				}
			}
			if (common.has("wardlocation") && !common.isNull("wardlocation")) {
				JSONObject ward = (JSONObject) common.get("wardlocation");
				if (!org.json.JSONObject.NULL.equals(ward.get("lat"))) {
					try {
						wc.setWardlocation_lat((Double) ward.get("lat") + "");
					} catch (Exception e) {
						wc.setWardlocation_lat(null);
					}

				}
				if (!org.json.JSONObject.NULL.equals(ward.get("lon"))) {
					try {
						wc.setWardlocation_lon((Double) ward.get("lon") + "");
					} catch (Exception e) {
						wc.setWardlocation_lon(null);
					}

				}

			}
			if (common.has("propertylocation") && !common.isNull("propertylocation")) {
				JSONObject loc = (JSONObject) common.get("propertylocation");
				if (!org.json.JSONObject.NULL.equals(loc.get("lat"))) {
					wc.setPropertylocation_lat((Double) loc.get("lat") + "");
				}
				if (!org.json.JSONObject.NULL.equals(loc.get("lon"))) {
					wc.setPropertylocation_lon((Double) loc.get("lon") + "");
				}

			}
			// System.out.println(searchable.get("arrearsDemand"));
			if (!str.isNull("_id")) {
				wc.setId((String) str.get("_id"));
				//System.out.println(str.get("_id"));
			}
			if (!searchable.isNull("waterTaxDue")) {
				wc.setWaterTaxDue((Number) searchable.get("waterTaxDue") + "");
			}
			if (!searchable.isNull("arrearsDemand")) {
				wc.setArrearsDemand((Number) searchable.get("arrearsDemand") + "");
			}
			if (!searchable.isNull("currentDemand")) {
				wc.setCurrentDemand((Number) searchable.getDouble("currentDemand") + "");
			}
			if (!searchable.isNull("arrearsDue")) {
				wc.setArrearsDue((Number) searchable.get("arrearsDue") + "");
			}
			if (!searchable.isNull("currentDue")) {
				wc.setCurrentDue((Number) searchable.getDouble("currentDue") + "");
			}
			if (!searchable.isNull("monthlyRate")) {
				wc.setMonthlyRate((Number) searchable.getDouble("monthlyRate") + "");
			}
			if (!searchable.isNull("locality")) {
				try {
					wc.setLocality(searchable.getString("locality"));
				} catch (Exception e) {
					wc.setLocality("");
				}

			}
			if (!searchable.isNull("consumername")) {
				try {
					wc.setConsumername(searchable.getString("consumername"));
				} catch (Exception e) {
					wc.setConsumername("XXXXXX");
				}

			}
			if (!searchable.isNull("closureType")) {
				wc.setClosureType(searchable.getString("closureType"));
			}
			if (!searchable.isNull("aadhaarnumber")) {
				try {
					wc.setAadhaarnumber(searchable.getString("aadhaarnumber"));
				} catch (Exception e) {

					wc.setAadhaarnumber("");
				}

			}
			if (!clauses.isNull("applicationcode")) {
				wc.setApplicationcode(clauses.getString("applicationcode"));
			}
			if (!clauses.isNull("mobilenumber")) {
				try {
					wc.setMobilenumber(clauses.getString("mobilenumber"));
				} catch (Exception e) {
					wc.setMobilenumber("");
				}

			}
			if (!clauses.isNull("zone")) {
				try {
					wc.setZone(clauses.getString("zone"));
				} catch (Exception e) {
					wc.setZone("");
				}

			}
			if (!clauses.isNull("totaldue")) {
				wc.setTotaldue((Double) clauses.getDouble("totaldue") + "");
			}
			if (!clauses.isNull("usage")) {
				try {
					wc.setUsage(clauses.getString("usage"));
				} catch (Exception e) {
					wc.setUsage("");
				}

			}
			if (!clauses.isNull("ulbname")) {
				wc.setUlbname(clauses.getString("ulbname"));
			}
			if (!clauses.isNull("ward")) {
				try {
					wc.setWard(clauses.getString("ward"));
				} catch (Exception e) {
					wc.setWard("");
				}

			}
			if (!clauses.isNull("consumercode")) {
				wc.setConsumercode(clauses.getString("consumercode"));
			}
			if (!clauses.isNull("connectiontype")) {
				wc.setConnectiontype(clauses.getString("connectiontype"));
			}
			if (!clauses.isNull("propertyid")) {
				try {
					wc.setPropertyid(clauses.getString("propertyid"));
				} catch (Exception e) {
					wc.setPropertyid("");
				}
			}
			if (!clauses.isNull("status")) {
				try {
					wc.setStatus(clauses.getString("status"));
				} catch (Exception e) {
					wc.setStatus("");
				}

			}
			if (!clauses.isNull("doorno")) {
				try {
					wc.setDoorno(clauses.get("doorno") + "");
				} catch (Exception e) {
					wc.setDoorno("");
				}

			}
			if (!clauses.isNull("adminward")) {
				try {
					wc.setAdminward(clauses.getString("adminward"));
				} catch (Exception e) {
					wc.setAdminward("");
				}

			}
			if (!clauses.isNull("islegacy")) {
				wc.setIslegacy((Boolean) clauses.get("islegacy") + "");
			}
			if (!clauses.isNull("districtname")) {
				wc.setDistrictname(clauses.getString("districtname"));
			}
			if (!clauses.isNull("regionname")) {
				wc.setRegionname(clauses.getString("regionname"));
			}
			if (!clauses.isNull("grade")) {
				wc.setGrade(clauses.getString("grade"));
			}
			if (!clauses.isNull("watersource")) {
				wc.setWatersource(clauses.getString("watersource"));
			} // category
			if (!clauses.isNull("propertytype")) {
				wc.setPropertytype(clauses.getString("propertytype"));
			}
			if (!clauses.isNull("category")) {
				try {
					wc.setCategory(clauses.getString("category"));
				} catch (Exception e) {
					wc.setCategory("");

				}

			}
			if (!clauses.isNull("sumpcapacity")) {

				wc.setSumpcapacity((Number) clauses.get("sumpcapacity") + "");
			}
			if (!clauses.isNull("pipesize")) {
				try {
					wc.setPipesize(clauses.getString("pipesize"));
				} catch (Exception e) {
					wc.setPipesize("");
				}
			}
			if (!clauses.isNull("numberofperson")) {
				wc.setNumberofperson((Integer) clauses.get("numberofperson") + "");
			}
			if (!clauses.isNull("bpaid")) {
				wc.setBpaid(clauses.getString("bpaid"));
			}
			try {
				loadToDatabase(wc);
			} catch (Exception e) {
				e.printStackTrace();
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

	public static void loadToDatabase(WaterCharges wc) {

		try {
			session.save(wc);
		} catch (Exception e) {
			session.saveOrUpdate(wc);
		}

	}
}
