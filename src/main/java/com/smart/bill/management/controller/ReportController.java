package com.smart.bill.management.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.bill.management.dto.MonthlySummaryResponse;
import com.smart.bill.management.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/monthly-summary")
	public ResponseEntity<MonthlySummaryResponse> getMonthlySummary() {
		MonthlySummaryResponse result = reportService.getMonthlySummaryWithMonth();
		return ResponseEntity.ok(result);
	}

}
