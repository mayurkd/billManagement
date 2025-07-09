package com.smart.bill.management;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BillManagementApplication {

	@Value("${server.port}")
	private String portNumber;

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(BillManagementApplication.class, args);
	}

	@PostConstruct
	public void printAppInfo() {
		String ipAddress = getIpAddress();
		System.out.println("------------------------------------------");
		System.out.println("Application_Name: " + appName); // Customize as needed
		System.out.println("System_IP_Address: " + ipAddress);
		System.out.println("localhost: http://localhost:" + portNumber);
		System.out.println("System_IP: http://" + ipAddress + ":" + portNumber);
		System.out.println("------------------------------------------");
	}

	private String getIpAddress() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostAddress(); // Returns the IP address of the system
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Unable to determine IP address";
		}
	}

}
