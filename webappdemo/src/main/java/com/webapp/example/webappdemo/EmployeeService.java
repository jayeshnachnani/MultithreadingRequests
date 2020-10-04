package com.webapp.example.webappdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;
    //@Async
    /*public CompletableFuture<List<Employee>> saveCars(final MultipartFile file) throws Exception {
        final long start = System.currentTimeMillis();
        List<Employee> cars = parseCSVFile(file);
        LOGGER.info("Saving a list of cars of size {} records", cars.size());
        cars = carRepository.saveAll(cars);
        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }
    private List<Employee> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Employee> cars=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(";");
                    final Employee car=new Employee();
                    car.setFirstName(data[0]);
                    car.setLastName(data[1]);
                    car.setDesignation(data[2]);
                    cars.add(car);
                }
                return cars;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }*/
    @Async
    public CompletableFuture<List<Employee>> getAllEmployees() throws InterruptedException {
        System.out.println("SupplyIds is currently running in " + Thread.currentThread().getName());
        LOGGER.info("Request to get a list of employees" + Thread.currentThread().getName());
        sleep(4000);
        long  j=0;
        for (int i =0; i<1000000000;i++)
            if (i%2==0)
                j++;
            else
                j--;
        final List<Employee> employees = employeeRepository.findAll();
        return CompletableFuture.completedFuture(employees);
    }
}
