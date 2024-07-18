package com.backend.webservice.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.webservice.model.Receipt;
import com.backend.webservice.services.ReceiptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
	
	private final ReceiptService receiptService;
	
    private final ObjectMapper objectMapper;
	
	public ReceiptController(ReceiptService receiptService){
		this.receiptService = receiptService;
		this.objectMapper = new ObjectMapper();
	}

	@PostMapping("/process")
	public ResponseEntity<ObjectNode> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

	
	@GetMapping("/{id}/points")
    public ResponseEntity<ObjectNode> getPoints(@PathVariable String id) {
        Optional<Integer> points = receiptService.getPoints(id);
        return points.map(p -> {
            ObjectNode response = objectMapper.createObjectNode();
            response.put("points", p);
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
      
	    


}
