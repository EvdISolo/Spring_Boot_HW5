package com.example.SpringBoot_HW5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DemoApplicationTests {

@Container
private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
        .withExposedPorts(8080);



@Container
private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
        .withExposedPorts(8081);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testDevApp() {

        Integer mappedPort = devApp.getMappedPort(8080);
        String url = "http://localhost:" + mappedPort;

        System.out.println("Testing dev app on URL: " + url);
        System.out.println("Dev container ID: " + devApp.getContainerId());
        System.out.println("Dev container is running: " + devApp.isRunning());

        // Делаем запрос к приложению
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("Dev response status: " + response.getStatusCode());
        System.out.println("Dev response body: " + response.getBody());


        assertEquals(HttpStatus.OK, response.getStatusCode());


        String body = response.getBody();
        assertTrue(body != null && !body.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testProdApp() {

        Integer mappedPort = prodApp.getMappedPort(8081);
        String url = "http://localhost:" + mappedPort;

        System.out.println("Testing prod app on URL: " + url);
        System.out.println("Prod container ID: " + prodApp.getContainerId());
        System.out.println("Prod container is running: " + prodApp.isRunning());


        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("Prod response status: " + response.getStatusCode());
        System.out.println("Prod response body: " + response.getBody());


        assertEquals(HttpStatus.OK, response.getStatusCode());


        String body = response.getBody();
        assertTrue(body != null && !body.isEmpty(), "Response body should not be empty");
    }
}