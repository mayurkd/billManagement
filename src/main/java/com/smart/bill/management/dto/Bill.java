package com.smart.bill.management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.smart.bill.management.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bill")
public class Bill {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private BigDecimal amount;
	private LocalDate dueDate;
	private boolean isPaid;
	private boolean isRecurring;
	private String fullName;
}
