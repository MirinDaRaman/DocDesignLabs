package com.doc.lab2.db_controller;
import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.CommisionPlan;
import com.doc.lab2.domain.Salary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DBController {

    public void saveSalary(Salary salary){
        try {
            URL url = new URL("http://localhost:8080/api/salary/%7Bsalary_id%7D");
            addObjectToDb(salary.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWorker(Worker worker){

        try {
            URL url = new URL("http://localhost:8080/api/worker/salary/" + worker.getRelatedSalaryId());
            addObjectToDb(worker.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCommisionPlan(CommisionPlan commisionPlan){
        try {
            URL url = new URL("http://localhost:8080/api/commision_plan");
            addObjectToDb(commisionPlan.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addObjectToDb(String jsonObject, URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonObject.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
