package com.doc.lab2.db_controller;

import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.CommisionPlan;
import com.doc.lab2.domain.Salary;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVController {
    public List<Salary> getSalariesFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<Salary> salariesArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean toFind = true;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(line.equals("##")) {toFind = false;}

                if (toFind){
                    String[] salaries = line.split(cvsSplitBy);

                    salariesArray.add(new Salary(Long.parseLong(salaries[0]), salaries[1], salaries[2],
                            Integer.parseInt(salaries[3])));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return salariesArray;
    }

    public List<Worker> getWorkersFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<Worker> workersArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            boolean toFind = false;
            while ((line = br.readLine()) != null) {
                if(line.equals("###")) {toFind = false;}

                if(toFind){
                    String[] salaries = line.split(cvsSplitBy);

                    Worker newWorker = new Worker(Long.parseLong(salaries[0]), salaries[1], salaries[2],
                            salaries[3], Long.parseLong(salaries[4]));

                    workersArray.add(newWorker);
                }

                if(line.equals("##")) {toFind = true;}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return workersArray;
    }

    public List<CommisionPlan> getCommisionPlansFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<CommisionPlan> commisionPlans = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            boolean toFind = false;
            while ((line = br.readLine()) != null) {
                if(toFind){
                    String[] salaries = line.split(cvsSplitBy);

                    CommisionPlan newCommisionPlan = new CommisionPlan(Long.parseLong(salaries[0]), salaries[1],
                            Long.parseLong(salaries[2]));

                    commisionPlans.add(newCommisionPlan);
                }

                if(line.equals("###")) {toFind = true;}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return commisionPlans;
    }
}
