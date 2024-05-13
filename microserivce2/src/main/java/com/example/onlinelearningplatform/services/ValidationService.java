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
        // Make HTTP request to external API to validate instructor ID
        String url = "http://localhost:8080/Online-Learning-Platform-1.0-SNAPSHOT/api/admin/validate?id=" + adminId;
        Boolean isValid = restTemplate.getForObject(url, Boolean.class);
        return isValid == null || !isValid; // Assuming the API returns a boolean indicating validity
    }

    public boolean validateInstructor(Long instructorId) {
        // Make HTTP request to external API to validate instructor ID
        String url = "http://localhost:8080/Online-Learning-Platform-1.0-SNAPSHOT/api/instructor/" + instructorId;
        try {
            // Make a GET request to the API endpoint
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

            // Check the HTTP status code of the response
            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            } else {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            // Handle 404 Not Found error
            return false;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return false;
        }
    }
    public boolean validateStudent(Long studentId) {
        String apiUrl = "http://localhost:8080/Online-Learning-Platform-1.0-SNAPSHOT/api/student/" + studentId;

        try {
            // Make a GET request to the API endpoint
            ResponseEntity<Void> response = restTemplate.getForEntity(apiUrl, Void.class);

            // Check the HTTP status code of the response
            if (response.getStatusCode() == HttpStatus.OK) {
                // Student exists, return true
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Student does not exist, return false
                return false;
            } else {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            // Handle 404 Not Found error
            return false;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return false;
        }
    }

}
