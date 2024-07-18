package com.backend.webservice;


import com.backend.webservice.controllers.ReceiptController;
import com.backend.webservice.model.Item;
import com.backend.webservice.model.Receipt;
import com.backend.webservice.services.ReceiptService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(ReceiptServiceTests.class)

public class ReceiptServiceTests {
	


    @MockBean
    private ReceiptService receiptService;



    private Receipt receipt;
    
	@BeforeEach
    public void setUp() {
        receipt = new Receipt();
        receipt.setId("123");
        receipt.setRetailer("Example Store");
        receipt.setPurchaseDate(LocalDate.of(2024, 7, 15));
        receipt.setPurchaseTime(LocalTime.of(14, 30));
        Item item1 = new Item("Item 1", 2.50);
        Item item2 = new Item("Item 2", 2.50);
        receipt.setItems(Arrays.asList(item1, item2));
        receipt.setTotal(5.00);
    }

	@Test
    public void testProcessReceipt() throws Exception {
        when(receiptService.processReceipt(any(Receipt.class))).thenReturn(receipt.getId());
        ReceiptController receiptController = new ReceiptController(receiptService);
        ResponseEntity<ObjectNode> response = receiptController.processReceipt(receipt);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(receipt.getId(), response.getBody().get("id").asText());
    }
	
	@Test
    public void testGetPoints() throws Exception {
        when(receiptService.getPoints(receipt.getId())).thenReturn(Optional.of(100));

        ReceiptController receiptController = new ReceiptController(receiptService);
        ResponseEntity<ObjectNode> response = receiptController.getPoints(receipt.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100, response.getBody().get("points").asInt());
    }

	}


