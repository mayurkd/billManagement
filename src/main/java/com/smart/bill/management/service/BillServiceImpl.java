package com.smart.bill.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.bill.management.dto.Bill;
import com.smart.bill.management.repo.BillRepository;

@Service
public class BillServiceImpl {
	
	@Autowired
	private BillRepository billRepository;
	
	public Bill createBill(Bill bill) {
		return billRepository.save(bill);
	}
	
	public List<Bill> getAllBill() {
		return billRepository.findAll();
	}

}
