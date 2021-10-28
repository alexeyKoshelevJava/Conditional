package ru.netology.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    private final String HOST = "http://localhost:";

    public static GenericContainer<?> dev_app = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    public static GenericContainer<?> prod_app = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        dev_app.start();
        prod_app.start();
    }

    @Test
    void contextLoads() {
        Integer dev_appMappedPort = dev_app.getMappedPort(8080);
        System.out.println(dev_appMappedPort);
        ResponseEntity<String> responseEntityForDev = restTemplate.getForEntity(HOST + dev_appMappedPort + "/profile", String.class);

        Integer prod_appMappedPort = prod_app.getMappedPort(8081);
        System.out.println(prod_appMappedPort);
        ResponseEntity<String> responseEntityForProd = restTemplate.getForEntity(HOST + prod_appMappedPort + "/profile", String.class);

        String expectedForDev = "Current profile is dev";
        String expectedForProd = "Current profile is production";

        String actualForDev = responseEntityForDev.getBody();
        String actualForProd = responseEntityForProd.getBody();
        System.out.println(actualForDev);
        System.out.println(actualForProd);

        Assertions.assertEquals(expectedForDev,actualForDev);
        Assertions.assertEquals(expectedForProd,actualForProd);

    }

}
