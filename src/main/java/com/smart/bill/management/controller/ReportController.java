package com.smart.bill.management.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.bill.management.dto.MonthlySummaryResponse;
import com.smart.bill.management.service.ReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/pending-monthly-summary")
	public ResponseEntity<?> getpendingMonthlySummary() {
		MonthlySummaryResponse result = reportService.getPendingMonthlySummaryWithMonth();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/paid-monthly-summary")
	public ResponseEntity<?> getPaidMonthlySummary() {
		MonthlySummaryResponse result = reportService.getPaidMonthlySummaryWithMonth();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/monthly-summary")
	public ResponseEntity<?> getMonthlySummary() {
		MonthlySummaryResponse result = reportService.getMonthlySummaryWithMonth();
		return ResponseEntity.ok(result);
	}

}
