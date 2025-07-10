package com.smart.bill.management.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reminder {
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate remindOn;
	private boolean sent;
	@OneToOne
	private Bill bill;

}
