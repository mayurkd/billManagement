package com.smart.bill.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.bill.management.dto.Bill;
import com.smart.bill.management.dto.MonthlySummaryResponse;
import com.smart.bill.management.repo.BillRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;

@Service
public class ReportService {

	@Autowired
    private BillRepository billRepository;

    public MonthlySummaryResponse getMonthlySummaryWithMonth() {
        YearMonth currentMonth = YearMonth.now();
        String monthName = currentMonth.getMonth().name().substring(0, 1) +
                           currentMonth.getMonth().name().substring(1).toLowerCase() +
                           " " + currentMonth.getYear();

        Map<String, BigDecimal> summary = new HashMap<>();
        List<Bill> bills = billRepository.findAll();

        for (Bill bill : bills) {
            if (bill.getDueDate() != null &&
                YearMonth.from(bill.getDueDate()).equals(currentMonth) && !bill.isPaid()) {
                summary.merge(bill.getFullName(), bill.getAmount(), BigDecimal::add);
            }
        }

        return new MonthlySummaryResponse(monthName, summary);
    }
}
