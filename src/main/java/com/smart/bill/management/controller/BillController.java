package com.smart.bill.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.bill.management.dto.Bill;
import com.smart.bill.management.service.BillServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	private BillServiceImpl billServiceImpl;

	@PostMapping
	ResponseEntity<?> createBills(@RequestBody Bill bill) {
		try {
			return ResponseEntity.ok(billServiceImpl.createBill(bill));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
		}
	}

	@GetMapping
	ResponseEntity<?> getBills() {
		try {
			return ResponseEntity.ok(billServiceImpl.getAllBill());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<?> updateBills(@PathVariable long id, @RequestBody Bill bill) {
		try {
			return ResponseEntity.ok(billServiceImpl.updateBillByid(id, bill));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteBills(@PathVariable long id) {
		try {
			return ResponseEntity.ok(Map.of("Message", billServiceImpl.deleteBillByid(id)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of( "Error" , e.getMessage()));
		}
	}
}
