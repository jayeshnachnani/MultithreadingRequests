package com.webapp.example.webappdemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    /*@RequestMapping (method = RequestMethod.POST, consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity uploadFile(
            @RequestParam (value = "files") MultipartFile[] files) {
        try {
            LOGGER.info("Saving  cars records");
            try{
            for(final MultipartFile file: files) {
                LOGGER.info("Saving  cars records in for loop");
                LOGGER.info("In for loop");
                carService.saveCars(file);
            }} catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody CompletableFuture<ResponseEntity> getAllEmployees() throws InterruptedException {
        return employeeService.getAllEmployees().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetEmployeeFailure);
    }
    private static Function<Throwable, ResponseEntity<? extends List<Employee>>> handleGetEmployeeFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}