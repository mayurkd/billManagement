package com.smart.bill.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart.bill.management.controller.AuthController;
import com.smart.bill.management.dto.Bill;
import com.smart.bill.management.dto.MonthlySummaryResponse;
import com.smart.bill.management.repo.BillRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;

@Service
public class ReportService {

	private final AuthController authController;

	@Autowired
	private BillRepository billRepository;

	ReportService(AuthController authController) {
		this.authController = authController;
	}

	public MonthlySummaryResponse getPendingMonthlySummaryWithMonth() {
		return getMonthlySummaryWithMonth(true, false);
	}

	public MonthlySummaryResponse getMonthlySummaryWithMonth() {
		return getMonthlySummaryWithMonth(false, false);
	}

	public MonthlySummaryResponse getPaidMonthlySummaryWithMonth() {
		return getMonthlySummaryWithMonth(false, true);
	}

	public MonthlySummaryResponse getMonthlySummaryWithMonth(boolean pending, boolean paid) {
		YearMonth currentMonth = YearMonth.now();
		String monthName = currentMonth.getMonth().name().substring(0, 1)
				+ currentMonth.getMonth().name().substring(1).toLowerCase() + " " + currentMonth.getYear();

		Map<String, BigDecimal> summary = new HashMap<>();
		Map<String, BigDecimal> pendingValue = new HashMap<>();
		Map<String, BigDecimal> paidValue = new HashMap<>();
		List<Bill> bills = billRepository.findAll();

		for (Bill bill : bills) {

			if (bill.getDueDate() != null && YearMonth.from(bill.getDueDate()).equals(currentMonth)) {
				if (!bill.isPaid() && pending) {
					pendingValue.merge(bill.getFullName(), bill.getAmount(), BigDecimal::add);
				} else if (bill.isPaid() && paid) {
					paidValue.merge(bill.getFullName(), bill.getAmount(), BigDecimal::add);
				} else {
					summary.merge(bill.getFullName(), bill.getAmount(), BigDecimal::add);
				}
			}
		}
		if (pending) {
			return new MonthlySummaryResponse(monthName, pendingValue);
		} else if (paid) {
			return new MonthlySummaryResponse(monthName, paidValue);
		} else if (!pending && !paid) {
			return new MonthlySummaryResponse(monthName, summary);
		} else {
			return new MonthlySummaryResponse(monthName, Map.of());
		}
	}
}
