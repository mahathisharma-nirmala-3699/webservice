package com.backend.webservice.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backend.webservice.model.Item;
import com.backend.webservice.model.Receipt;

@Service
public class ReceiptService {
	 private final List<Receipt> receipts = new ArrayList<>();
	 
		public String processReceipt(Receipt receipt) {
	        receipt.setId(UUID.randomUUID().toString());
			receipt.setPoints(calculatePoints(receipt));
	        receipts.add(receipt);
			return receipt.getId();
		}
		
		 public Optional<Integer> getPoints(String id) {
		        return receipts.stream().filter(receipt -> receipt.getId().equals(id)).map(Receipt::getPoints).findFirst();
		    }
		
		
		public int calculatePoints(Receipt receipt)
		{
			int points= callConditionOne(receipt.getRetailer()) + 
			callConditionTwoAndThree(receipt.getTotal()) +
			callConditionFour(receipt.getItems().size()) +
			callConditionFive(receipt.getItems()) +
			callConditionSixAndSeven(receipt.getPurchaseDate(), receipt.getPurchaseTime());
			return points;
	       
	    }	
		
		
		//1 : Retailer String condition 
		public int callConditionOne(String retailerString)
		{
			int points =0;
		  for (char c : retailerString.toCharArray()) {
	          if (Character.isLetterOrDigit(c)) {
	              points++;
	          }
	      }
		  return points;
		}
		
		//2 and 3 total without and with cents
		public int callConditionTwoAndThree(Double total)
		{
			int points =0;
			 if (total % 1 == 0) {
		            points += 50;
			 }
			 if (total % 0.25 == 0) {
		            points += 25;
		        }
	    return points;
		}
		
		//4 
		public int callConditionFour(int itemSize)
		{
			int points =0;
			points +=(itemSize/2 * 5);	
			return points;
		}
		
		//5
		public int callConditionFive(List<Item> items) {
			int points =0;
			for (Item item : items) {
		          String description = item.getShortDescription().trim();
		          if (description.length() % 3 == 0) {
		              points += Math.ceil(item.getPrice() * (0.2));
		          }
		      }
			
			return points;
		}
		
		//6 and 7
		public int callConditionSixAndSeven(LocalDate purchaseDate, LocalTime purchaseTime) {
			int points =0;
			if (purchaseDate.getDayOfMonth() % 2 != 0) {
		          points += 6;
		      }
		      LocalTime startTime = LocalTime.of(14, 0); // 2:00 PM
		      LocalTime endTime = LocalTime.of(16, 0); // 4:00 PM
		      if(purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime) ) {
		    	  points +=10;
		      }
		      return points;  
			
		}
		
		
}
