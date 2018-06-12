package com.otsi.estodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class GetMapping {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Enter index name");
		Scanner sc = new Scanner(System.in);
		String index = sc.nextLine();
		System.out.println("Enter doc type");
		String docType = sc.nextLine();
		String url = "http://search-api.egovernments.org/" + index + "/_mapping";
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=MAUD;user=root;password=root";
		URL urlObj = new URL(url);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List fields = new ArrayList();
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
		JSONObject compObj = (JSONObject) jsonObj.get(index);
		// System.out.println(compObj.get("mappings"));
		JSONObject mappingsObj = compObj.getJSONObject("mappings");
		JSONObject compsObj = mappingsObj.getJSONObject(docType);
		JSONObject propsObj = compsObj.getJSONObject("properties");
		System.out.println(propsObj);
		String[] str = propsObj.getNames(propsObj);
		ArrayList geoFields = new ArrayList();
		ArrayList nonGeoFields = new ArrayList();
		for (String string : str) {
			if (string.contains("Geo")) {
				geoFields.add(string + "_lat");
				geoFields.add(string + "_lon");
			} else {
				nonGeoFields.add(string);
			}
		}
		List mappingFields = new ArrayList();
		mappingFields.addAll(nonGeoFields);
		mappingFields.addAll(geoFields);
		for (Object object : mappingFields) {
			System.out.print(object+"\t");
		}
	}

}
