package com.smart.bill.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.smart.bill.management.dto.Bill;

@EnableJpaRepositories
public interface BillRepository extends JpaRepository<Bill, Long> {

}
