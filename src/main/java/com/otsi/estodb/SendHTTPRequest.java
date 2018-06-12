package com.otsi.estodb;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

import javax.persistence.FlushModeType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class SendHTTPRequest {
	private static Session session = null;

	public static void main(String[] args) throws Exception {
		System.out.println("Starting  import service");
		
			String maxDt=null;
			try {
			session = getSession();
			String sql = "select max(createdDate) FROM Complaint";
			Query query = session.createQuery(sql, Date.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			Object dd=query.getSingleResult();
			if(dd!=null) {
			Date dt = (Date) dd;
			
			maxDt = sdf.format(dt);
			}
			session.close();
			session=null;
			}catch(Exception e) {
				e.printStackTrace();
				maxDt=null;
				createSchema();
			}
			
			
			
			int totalCount = checkTotalCount(maxDt);
			bulkInsert(totalCount, maxDt);
			System.out.println("Import completed!!!");
	}

	private static SchemaExport getSchemaExport() {

		SchemaExport export = new SchemaExport();
		// Script file.

		// No Stop if Error
		export.setHaltOnError(true);
		//
		return export;
	}

	public static void createDataBase(SchemaExport export, Metadata metadata) {

		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.STDOUT);

		SchemaExport.Action action = SchemaExport.Action.CREATE;
		//
		export.execute(targetTypes, action, metadata);
		System.out.println("Table created");

	}

	public static void createSchema() {
		String configFileName = "hibernate-sqlserver.cfg.xml";
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		SchemaExport export = getSchemaExport();
		createDataBase(export, metadata);

	}

	public static int checkTotalCount(String maxDt) throws IOException {
		String from = "*";
		if (maxDt != null) {
			from = maxDt;
		}
		String url = "http://search-api.egovernments.org/complaint/_search?sort=createdDate:asc&pretty&q=createdDate:[%22"
				+ from + "%22+TO+%22now-1d/d%22]";

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

	public static void bulkInsert(int toatlCount, String maxDt) throws IOException, JSONException, ParseException {
		String frm = "*";
		if (maxDt != null) {
			frm = maxDt;
		}
		int iteratorCount = toatlCount / 10000;
		for (int i = 0; i <= iteratorCount; i++) {
			String from = String.valueOf(i * 10000);
			String url = "http://search-api.egovernments.org/complaint/_search?sort=createdDate:asc&pretty&q=createdDate:[%22"
					+ frm + "%22+TO+%22now-1d/d%22]&size=10000&from=" + from;
			System.out.println(url);
			insertBatch(url, from);
			
		}
		session.close();
		session = null;
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

		if (session == null) {
			session = getSession();
		}
		org.hibernate.Transaction tr =  session.beginTransaction();
		//List<Complaint> complaints = new ArrayList<Complaint>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
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
		JSONArray res = (JSONArray) hitsObj.get("hits");
        System.out.println(from);
		// System.out.println(res.length());
		for (int i = 0; i < res.length(); i++) {
			Complaint complaint = new Complaint();
			// System.out.println("inside for " +i);
			JSONObject str = res.getJSONObject(i);
			//System.out.println(str.get("_id"));
			JSONObject obj = (JSONObject) str.get("_source");
			if (obj.has("wardGeo") && !obj.isNull("wardGeo")) {
				JSONObject ward = (JSONObject) obj.get("wardGeo");
				complaint.setWardGeo_lat((Double) ward.get("lat") + "");
				complaint.setWardGeo_lon((Double) ward.get("lon") + "");
			}
			if (obj.has("complaintGeo") && !obj.isNull("complaintGeo")) {
				JSONObject comp = (JSONObject) obj.get("complaintGeo");
				complaint.setComplaintGeo_lat((Double) comp.get("lat") + "");
				complaint.setComplaintGeo_lon((Double) comp.get("lon") + "");

			}
			if (obj.has("localityGeo") && !obj.isNull("localityGeo")) {
				JSONObject loca = (JSONObject) obj.get("localityGeo");
				complaint.setLocalityGeo_lat((Double) loca.get("lat") + "");
				complaint.setLocalityGeo_lon((Double) loca.get("lon") + "");
			}

			if (!obj.isNull("escalation3FunctionaryAgeingFromDue")) {
				complaint.setEscalation3FunctionaryAgeingFromDue(
						(int) (obj.getDouble("escalation3FunctionaryAgeingFromDue")));
			}

			if (!obj.isNull("escalation1FunctionaryAssigneddate")) {
				complaint.setEscalation1FunctionaryAssigneddate(formatter
						.parse(((String) obj.get("escalation1FunctionaryAssigneddate")).replaceAll("Z$", "+0000")));
			}
			// same for all 90 + fields :(
            
			
			if (!obj.isNull("complaintStatusName")) {
				complaint.setComplaintStatusName((String) obj.get("complaintStatusName"));
			}
			if (!obj.isNull("escalation1FunctionaryIfSLA")) {
				complaint.setEscalation1FunctionaryIfSLA((Integer) obj.get("escalation1FunctionaryIfSLA"));
			}
			if (!obj.isNull("departmentCode")) {
				complaint.setDepartmentCode(obj.getString("departmentCode"));
			}
			if (!obj.isNull("escalation3FunctionaryIsSLA")) {
				complaint.setEscalation3FunctionaryIsSLA((String) obj.get("escalation3FunctionaryIsSLA"));
			}
			if (!obj.isNull("registered")) {
				complaint.setRegistered((Integer) obj.get("registered"));
			}
			if (!obj.isNull("receivingMode")) {
				complaint.setReceivingMode(obj.getString("receivingMode"));
			}
			if (!obj.isNull("source")) {
				complaint.setSource(obj.getString("source"));
			}
			if (!obj.isNull("complaintPeriod")) {
				complaint.setComplaintPeriod((int) obj.getDouble("complaintPeriod"));
			}
			if (!obj.isNull("currentFunctionaryIsSLA")) {
				complaint.setCurrentFunctionaryIsSLA(obj.getString("currentFunctionaryIsSLA"));
			}
			if (!obj.isNull("currentFunctionaryIsSLA")) {
				complaint.setCityGrade(obj.getString("cityGrade"));
			}
			if (!obj.isNull("inProcess")) {
				complaint.setInProcess((Integer) obj.get("inProcess"));
			}

			if (!obj.isNull("localityName")) {
				complaint.setLocalityName(obj.getString("localityName"));
			}

			if (!obj.isNull("initialFunctionaryAssigneddate")) {
				complaint.setInitialFunctionaryAssigneddate(formatter
						.parse(((String) obj.get("initialFunctionaryAssigneddate")).replaceAll("Z$", "+0000")));

			}
			if (!obj.isNull("escalationDate")) {
				complaint.setEscalationDate(formatter
						.parse(((String) obj.get("escalationDate")).replaceAll("Z$", "+0000")));

			}
			if (!obj.isNull("initialFunctionaryMobileNumber")) {
				complaint.setInitialFunctionaryMobileNumber((String) obj.get("initialFunctionaryMobileNumber"));
			}
			if (!obj.isNull("complaintAgeingFromDue")) {
				complaint.setComplaintAgeingFromDue(obj.getInt("complaintAgeingFromDue"));
			}
			if (!obj.isNull("initialFunctionaryIfSLA")) {
				complaint.setInitialFunctionaryIfSLA(obj.getInt("initialFunctionaryIfSLA"));
			}
			if (!obj.isNull("details")) {
				complaint.setDetails(obj.getString("details"));
			}
			if (str.get("_id") != null) {
				complaint.setId((String) str.get("_id"));
			}
			if (!obj.isNull("departmentName")) {
				complaint.setDepartmentName(obj.getString("departmentName"));
			}
			if (!obj.isNull("currentFunctionaryIfSLA")) {
				complaint.setCurrentFunctionaryIfSLA(obj.getInt("currentFunctionaryIfSLA"));
			}
			if (!obj.isNull("currentFunctionaryAssigneddate")) {
				complaint.setCurrentFunctionaryAssigneddate(formatter
						.parse(((String) obj.get("currentFunctionaryAssigneddate")).replaceAll("Z$", "+0000")));
				// System.out.println(complaint.getEscalation1FunctionaryAssigneddate());
			}
			if (!obj.isNull("escalation1FunctionaryAgeingFromDue")) {
				complaint.setEscalation1FunctionaryAgeingFromDue(obj.getInt("escalation1FunctionaryAgeingFromDue"));
			}
			if (!obj.isNull("complainantName")) {
				complaint.setComplainantName(obj.getString("complainantName"));
			}
			if (!obj.isNull("complainantMobile")) {
				complaint.setComplainantMobile((String) obj.get("complainantMobile"));
			}
			if (!obj.isNull("complaintDuration")) {
				complaint.setComplaintDuration(obj.getInt("complaintDuration"));
			}
			if (!obj.isNull("durationRange")) {
				complaint.setDurationRange(obj.getString("durationRange"));
			}
			if (!obj.isNull("escalationLevel")) {
				complaint.setEscalationLevel(obj.getInt("escalationLevel"));
			}
			if (!obj.isNull("cityRegionName")) {
				complaint.setCityRegionName(obj.getString("cityRegionName"));
			}
			if (!obj.isNull("wardName")) {
				complaint.setWardName(obj.getString("wardName"));
			}
			if (!obj.isNull("currentFunctionarySLADays")) {
				complaint.setCurrentFunctionarySLADays(obj.getInt("currentFunctionarySLADays"));
			}
			if (!obj.isNull("addressed")) {
				complaint.setAddressed(obj.getInt("addressed"));
			}
			if (!obj.isNull("currentFunctionaryMobileNumber")) {
				complaint.setCurrentFunctionaryMobileNumber((String) obj.get("currentFunctionaryMobileNumber"));
			}
			if (!obj.isNull("reOpened")) {
				complaint.setReOpened(obj.getInt("reOpened"));
			}
			if (!obj.isNull("cityDistrictName")) {
				complaint.setCityDistrictName(obj.getString("cityDistrictName"));
			}
			if (!obj.isNull("complaintAgeingdaysFromDue")) {
				complaint.setComplaintAgeingdaysFromDue(obj.getInt("complaintAgeingdaysFromDue"));
			}
			if (!obj.isNull("ifSLA")) {
				complaint.setIfSLA(obj.getInt("ifSLA"));
			}
			if (!obj.isNull("escalation3FunctionaryIfSLA")) {
				complaint.setEscalation3FunctionaryIfSLA(obj.getInt("escalation3FunctionaryIfSLA"));
			}
			if (!obj.isNull("escalation1FunctionaryIsSLA")) {
				complaint.setEscalation1FunctionaryIsSLA(obj.getString("escalation1FunctionaryIsSLA"));
			}
			if (!obj.isNull("initialFunctionaryIsSLA")) {
				complaint.setInitialFunctionaryIsSLA(obj.getString("initialFunctionaryIsSLA"));
			}
			if (!obj.isNull("complainantEmail")) {
				complaint.setComplainantEmail(obj.getString("complainantEmail"));
			}
			if (!obj.isNull("wardNo")) {
				complaint.setWardNo(obj.getInt("wardNo"));
			}
			if (!obj.isNull("landmarkDetails")) {
				complaint.setLandmarkDetails(obj.getString("landmarkDetails"));
			}
			if (!obj.isNull("rejected")) {
				complaint.setRejected(obj.getInt("rejected"));
			}
			if (!obj.isNull("localityNo")) {
				complaint.setLocalityNo(obj.getInt("localityNo"));
			}
			if (!obj.isNull("cityCode")) {
				complaint.setCityCode(obj.getInt("cityCode"));
			}
			if (!obj.isNull("escalation3FunctionaryName")) {
				complaint.setEscalation3FunctionaryName(obj.getString("escalation3FunctionaryName"));
			}
			if (!obj.isNull("categoryName")) {
				complaint.setCategoryName(obj.getString("categoryName"));
			}
			if (!obj.isNull("complaintTypeCode")) {
				complaint.setComplaintTypeCode(obj.getString("complaintTypeCode"));
			}
			if (!obj.isNull("complaintTypeName")) {
				complaint.setComplaintTypeName(obj.getString("complaintTypeName"));
			}
			if (!obj.isNull("complaintReOpenedDate")) {
				complaint.setComplaintReOpenedDate(
						formatter.parse(((String) obj.get("complaintReOpenedDate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("currentFunctionaryName")) {
				complaint.setCurrentFunctionaryName(obj.getString("currentFunctionaryName"));
			}
			if (!obj.isNull("cityName")) {
				complaint.setCityName(obj.getString("cityName"));
			}
			if (!obj.isNull("cityName")) {
				complaint.setAssigneeName(obj.getString("assigneeName"));
			}
			if (!obj.isNull("initialFunctionaryName")) {
				complaint.setInitialFunctionaryName(obj.getString("initialFunctionaryName"));
			}
			if (!obj.isNull("cityDomainUrl")) {
				complaint.setCityDomainUrl(obj.getString("cityDomainUrl"));
			}
			if (!obj.isNull("escalation3FunctionaryAssigneddate")) {
				complaint.setEscalation3FunctionaryAssigneddate(formatter
						.parse(((String) obj.get("escalation3FunctionaryAssigneddate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("escalation3FunctionarySLADays")) {
				complaint.setEscalation3FunctionarySLADays(obj.getInt("escalation3FunctionarySLADays"));
			}
			if (!obj.isNull("crn")) {
				complaint.setCrn(obj.getString("crn"));
			}
			if (!obj.isNull("cityDistrictCode")) {
				complaint.setCityDistrictCode(obj.getInt("cityDistrictCode"));
			}
			if (!obj.isNull("isSLA")) {
				complaint.setIsSLA(obj.getString("isSLA"));
			}
			if (!obj.isNull("escalation2FunctionaryIfSLA")) {
				complaint.setEscalation2FunctionaryIfSLA(obj.getInt("escalation2FunctionaryIfSLA"));
			}
			if (!obj.isNull("initialFunctionarySLADays")) {
				complaint.setInitialFunctionarySLADays(obj.getInt("initialFunctionarySLADays"));
			}
			if (!obj.isNull("initialFunctionaryAgeingFromDue")) {
				complaint.setInitialFunctionaryAgeingFromDue(obj.getInt("initialFunctionaryAgeingFromDue"));
			}
			if (!obj.isNull("currentFunctionaryAgeingFromDue")) {
				complaint.setCurrentFunctionaryAgeingFromDue(obj.getInt("currentFunctionaryAgeingFromDue"));
			}
			if (!obj.isNull("escalation2FunctionaryIsSLA")) {
				complaint.setEscalation2FunctionaryIsSLA(obj.getString("escalation2FunctionaryIsSLA"));
			}
			if (!obj.isNull("escalation2FunctionarySLADays")) {
				complaint.setEscalation2FunctionarySLADays(obj.getInt("escalation2FunctionarySLADays"));
			}
			if (!obj.isNull("escalation1FunctionaryName")) {
				complaint.setEscalation1FunctionaryName(obj.getString("escalation1FunctionaryName"));
			}
			if (!obj.isNull("assigneeId")) {
				complaint.setAssigneeId(obj.getInt("assigneeId"));
			}
			if (!obj.isNull("satisfactionIndex")) {
				complaint.setSatisfactionIndex(obj.getInt("satisfactionIndex"));
			}
			if (!obj.isNull("url")) {
				complaint.setUrl(obj.getString("url"));
				System.out.println(obj.getString("url")+"URL");
			}
			if (!obj.isNull("escalation2FunctionaryAgeingFromDue")) {
				complaint.setEscalation2FunctionaryAgeingFromDue(obj.getInt("escalation2FunctionaryAgeingFromDue"));
			}
			if (!obj.isNull("createdDate")) {
				complaint.setCreatedDate(formatter.parse(((String) obj.get("createdDate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("complaintSLADays")) {
				complaint.setComplaintSLADays(obj.getInt("complaintSLADays"));
			}
			if (!obj.isNull("closedByFunctionaryName")) {
				complaint.setClosedByFunctionaryName(obj.getString("closedByFunctionaryName"));
			}
			if (!obj.isNull("complaintIsClosed")) {
				complaint.setComplaintIsClosed(obj.getString("complaintIsClosed"));
			}
			if (!obj.isNull("escalation1FunctionarySLADays")) {
				complaint.setEscalation1FunctionarySLADays(obj.getInt("escalation1FunctionarySLADays"));
			}
			if (!obj.isNull("reasonForRejection")) {
				complaint.setReasonForRejection(obj.getString("reasonForRejection"));
			}
			if (!obj.isNull("ifClosed")) {
				complaint.setIfClosed(obj.getInt("ifClosed"));
			}
			if (!obj.isNull("closed")) {
				complaint.setClosed(obj.getBoolean("closed"));
			}
			if (!obj.isNull("escalation2FunctionaryAssigneddate")) {
				complaint.setEscalation2FunctionaryAssigneddate(formatter
						.parse(((String) obj.get("escalation2FunctionaryAssigneddate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("completionDate")) {
				complaint.setCompletionDate(formatter
						.parse(((String) obj.get("completionDate")).replaceAll("Z$", "+0000")));
			}
			if (!obj.isNull("escalation2FunctionaryName")) {
				complaint.setEscalation2FunctionaryName(obj.getString("escalation2FunctionaryName"));
			}
			if (!obj.isNull("categoryId")) {
				complaint.setCategoryId(obj.getInt("categoryId"));
			}
			if (!obj.isNull("rating")) {
				complaint.setRating(obj.getInt("rating"));
			}
			if (!obj.isNull("feedbackRating")) {
				complaint.setFeedbackRating(obj.getInt("feedbackRating"));
			}
			if (!obj.isNull("noOfFeedbackTaken")) {
				complaint.setNoOfFeedbackTaken(obj.getInt("noOfFeedbackTaken"));
			}
			if (!obj.isNull("noOfFeedbackReviews")) {
				complaint.setNoOfFeedbackReviews(obj.getInt("noOfFeedbackReviews"));
			}
			if (!obj.isNull("feedbackReason")) {
				complaint.setFeedbackReason(obj.getString("feedbackReason"));
			}
			if( i % 50 == 0 ) { // Same as the JDBC batch size
			      //flush a batch of inserts and release memory:
			      session.flush();
			      session.clear();
			   }

			loadToDatabase(complaint);
			// complaints.add(complaint);

		} // for each matched search query convert it to complaint and add to arrayList
		tr.commit();
		
		// loadToCsv(complaints, from);

	}

	public static void loadToCsv(Complaint complaints, String fileName) throws IOException {
		FileWriter fileWriter = null;
		fileName = "C:\\Users\\HP\\Desktop\\" + fileName + ".csv";
		fileWriter = new FileWriter(fileName);
		FileOutputStream os = new FileOutputStream(fileName);
		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(os));

		// CSVWriter csvWriter = new CSVWriter(fileWriter);
		BeanToCsv bc = new BeanToCsv();
		ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
		mappingStrategy.setType(Complaint.class);
		String[] columns = new String[] { "rating", "feedbackRating", "noOfFeedbackTaken", "noOfFeedbackReviews",
				"feedbackReason", "complaintGeo_lon", "complaintGeo_lat", "wardGeo_lon", "wardGeo_lat",
				"localityGeo_lon", "localityGeo_lat", "initialFunctionaryMobileNumber", "localityName", "ifSLA",
				"cityName", "cityCode", "escalation1FunctionaryIsSLA", "complaintReOpenedDate", "complaintTypeCode",
				"escalation2FunctionaryIfSLA", "cityDomainUrl", "cityDistrictName", "escalation3FunctionaryIfSLA",
				"addressed", "categoryName", "escalation1FunctionaryIfSLA", "assigneeId", "escalation1FunctionaryName",
				"rejected", "escalation1FunctionarySLADays", "createdDate", "complaintStatusName", "complaintIsClosed",
				"currentFunctionaryAgeingFromDue", "currentFunctionaryMobileNumber", "complaintAgeingFromDue",
				"escalation3FunctionarySLADays", "currentFunctionaryAssigneddate", "complaintAgeingdaysFromDue",
				"currentFunctionaryName", "categoryId", "reasonForRejection", "url", "complaintTypeName",
				"escalation3FunctionaryIsSLA", "initialFunctionaryName", "complainantName", "durationRange", "isSLA",
				"initialFunctionaryIsSLA", "escalation2FunctionaryIsSLA", "escalation3FunctionaryAgeingFromDue",
				"cityDistrictCode", "complaintDuration", "escalation3FunctionaryAssigneddate", "wardName",
				"complaintSLADays", "reOpened", "escalationLevel", "closedByFunctionaryName",
				"escalation2FunctionaryAgeingFromDue", "id", "wardNo", "initialFunctionaryAssigneddate", "localityNo",
				"details", "escalation1FunctionaryAssigneddate", "complaintPeriod", "crn", "inProcess",
				"complainantMobile", "closed", "complainantEmail", "initialFunctionaryIfSLA",
				"escalation2FunctionaryName", "cityGrade", "satisfactionIndex", "ifClosed", "escalationDate",
				"currentFunctionaryIfSLA", "initialFunctionarySLADays", "cityRegionName",
				"escalation2FunctionaryAssigneddate", "landmarkDetails", "assigneeName", "escalation3FunctionaryName",
				"departmentName", "currentFunctionarySLADays", "source", "receivingMode", "currentFunctionaryIsSLA",
				"initialFunctionaryAgeingFromDue", "registered", "escalation1FunctionaryAgeingFromDue",
				"escalation2FunctionarySLADays", "departmentCode" };
		mappingStrategy.setColumnMapping(columns);
		// Writing empList to csv file
		// bc.write(mappingStrategy, csvWriter, complaints);

	}

	public static void loadToDatabase(Complaint complaints) throws IOException {

		session.saveOrUpdate(complaints);

	}
}
