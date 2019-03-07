package com.project.arielfaridja.Operator.model.Datasource;

import com.project.arielfaridja.Operator.model.Entities.ActDes;
import com.project.arielfaridja.Operator.model.Entities.Address;
import com.project.arielfaridja.Operator.model.Entities.Date;
import com.project.arielfaridja.Operator.model.Entities.activity;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.user;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Harel on 09/03/2017.
 */

public class ServerFunctions
        implements FunctionsInterface {
    private static final String WEB_URL = "http://faridja.vlab.jct.ac.il/project";

    int ActivitiesOriginalSize = 0;
    int BusinessOriginalSize = 0;

    @Override
    public int AddUser(user User) {
        String results = new String();
        try {
            Map<String, Object> params = new LinkedHashMap<>();


            params.put("email", User.getEmail());
            params.put("password", User.getPassword());

            results = POST(WEB_URL + "/AddUser.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.length()>=5 && results.substring(0,5).equalsIgnoreCase("error"))  {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return Integer.parseInt(results);
    }

    @Override
    public int AddBusiness(business Business) {
        String results = new String();
        try {
            Map<String, Object> params = new LinkedHashMap<>();


            params.put("name", Business.getName());
            params.put("address", Business.getAddress().toString());
            params.put("phone", Business.getPhoneNumber());
            params.put("email", Business.getEmail());
            params.put("website", Business.getWebsite());
            results = POST(WEB_URL + "/AddBusiness.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.length()>=5 && results.substring(0,5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return Integer.parseInt(results);
    }

    @Override
    public void AddActivity(activity Act) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();


            params.put("country", Act.getCountry());
            params.put("description", Act.getDescription().toString());
            params.put("cost", Act.getCost());
            params.put("startDate", Act.getStartDate().toString());
            params.put("endDate", Act.getEndDate().toString());
            params.put("LongDescription", Act.getLongDescription());
            params.put("BusinessId", Act.getBusinessId());
            String results = POST(WEB_URL + "/AddActivity.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.length()>=5 && results.substring(0,5).equalsIgnoreCase("error"))  {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public boolean IsChangedSomthing()throws Exception {

        if (ActivitiesOriginalSize == this.GetAllActivities().size() && BusinessOriginalSize == this.GetAllBusiness().size()) {
            return false;
        }
        ActivitiesOriginalSize = this.GetAllActivities().size();
        BusinessOriginalSize = this.GetAllBusiness().size();
        return true;
    }

    @Override
    public ArrayList<user> GetAllUsers() throws Exception {
        ArrayList<user> a = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "/GetAllUsers.php")).getJSONArray("Users");
        for (int i = 0; i < array.length(); i++) {
            JSONObject u = array.getJSONObject(i);
            a.add(new user(u.getString("email"),u.getString("password"),u.getInt("id")));
        }
        return a;
    }

    @Override
    public ArrayList<activity> GetAllActivities() throws Exception {
        ArrayList<activity> a = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "/GetAllActivities.php")).getJSONArray("Activities");
        for (int i = 0; i < array.length(); i++) {
            JSONObject act = array.getJSONObject(i);
            a.add(new activity(ActDes.valueOf(act.getString("description")),
                    act.getString("country"),
                    Float.valueOf(act.getString("cost")),
                    Date.parse(act.getString("startDate")),
                    Date.parse(act.getString("endDate")),
                    act.getString("LongDescription"),
                    act.getInt("BusinessId")));
        }
        return a;
    }

    @Override
    public ArrayList<business> GetAllBusiness() throws Exception {
        ArrayList<business> a = new ArrayList<>();
        JSONArray array = new JSONObject(GET(WEB_URL + "/GetAllBusiness.php")).getJSONArray("Agencies");
        for (int i = 0; i < array.length(); i++) {
            JSONObject agency = array.getJSONObject(i);
            a.add(new business(agency.getString("name"),
                    Address.parse(agency.getString("address")),
                    agency.getString("phone"),
                    agency.getString("email"),
                    agency.getString("website"),
                    agency.getInt("id")));
        }
        return a;
    }

    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return "";
        }
    }

    private static String POST(String url, Map<String, Object> params) throws IOException {

        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else return "";
    }

}
