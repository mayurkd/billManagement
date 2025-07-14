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

	public Bill updateBillByid(long id, Bill request) {
		Bill bill = billRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bill not found with id : " + id));
		bill.setAmount(request.getAmount());
		bill.setDueDate(request.getDueDate());
		bill.setFullName(request.getFullName());
		bill.setTitle(request.getTitle());
		bill.setPaid(request.isPaid());
		return billRepository.save(bill);

	}

	public String deleteBillByid(long id) {
		Bill bill = billRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bill not found with id : " + id));
		if (bill.getId() != null) {
			billRepository.deleteById(bill.getId());
			return "Bill deleted Succesfully";
		} else {
			return "Bill id not present";
		}
	}

}
