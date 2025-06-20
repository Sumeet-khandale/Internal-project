package com.PaymentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.PaymentService.dto.PaymentRequestDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testInitiatePaymentAPI() {
        PaymentRequestDTO request = new PaymentRequestDTO();
        request.setBookingId("BKG123");
        request.setUserId("USR456");
        request.setAmount(new BigDecimal("199.99"));
        request.setCurrency("INR");
        request.setPaymentMethod("UPI");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequestDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/payments/initiate", entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("SUCCESS"));
    }
}
