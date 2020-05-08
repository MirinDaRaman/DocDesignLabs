package com.doc.lab2;

import com.doc.lab2.db_controller.CSVController;
import com.doc.lab2.db_controller.DBController;
import com.doc.lab2.domain.Worker;
import com.doc.lab2.domain.CommisionPlan;
import com.doc.lab2.domain.Salary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        CSVController csvController = new CSVController();
        DBController dbController = new DBController();
        try {
            for (Salary salary: csvController.getSalariesFromCSV()) {
                dbController.saveSalary(salary);
            }

            for (Worker worker: csvController.getWorkersFromCSV()) {
                dbController.saveWorker(worker);
            }

            for (CommisionPlan commisionPlan: csvController.getCommisionPlansFromCSV()) {
                dbController.saveCommisionPlan(commisionPlan);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}