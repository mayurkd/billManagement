package com.smart.bill.management.dto;

import java.math.BigDecimal;
import java.util.Map;

public record MonthlySummaryResponse(String month, Map<String, BigDecimal> Summary) {

}
