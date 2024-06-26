package com.example.onlinelearningplatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationService {
    @Autowired
    private RestTemplate restTemplate;

    public boolean validateAdmin(Long adminId) {

       String url = "http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/admin/validate?id=" + adminId;
        try {

            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            } else {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            //404 Not Found error
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateInstructor(Long instructorId) {

        String url = "http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/instructor/" + instructorId;
        try {

            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            } else {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
         //404 Not Found error
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean validateStudent(Long studentId) {
        String apiUrl = "http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/student/" + studentId;

        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(apiUrl, Void.class);
            // Check the HTTP status code of the response
            if (response.getStatusCode() == HttpStatus.OK) {
                // Student exists
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Student does not exist
                return false;
            } else {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            //404 Not Found error
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
