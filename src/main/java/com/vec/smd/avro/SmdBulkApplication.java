package com.vec.smd.avro;

import com.vec.smd.avro.model.Address;
import com.vec.smd.avro.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ThreadInfo;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@SpringBootApplication
public class SmdBulkApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(SmdBulkApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SmdBulkApplication.class, args);
		//testStreamTask();
	}


	//learning java 8 streams
	public static void testStreamTask(){

		List<Customer> aCustomers = new ArrayList<>();

		IntStream.range(1,11)
			.forEach(n -> {
				aCustomers.add(new Customer(n,n * 3));
			});

		aCustomers.forEach(cust -> {

			IntStream.range(1,5).forEach(n -> {
				Address newAddress = new Address("USA","TX",     cust.getCustId() +" addressLine" + n);
				cust.addAddress(newAddress);
			});

		});


		aCustomers.parallelStream().filter(customer -> {

			LOGGER.info("inside filter " + customer.toString() + " " + Thread.currentThread().getName());
			return customer.getAge() % 2 == 0;

		}).forEach(customer -> {
			LOGGER.info("inside for each " + customer.toString() + " " + Thread.currentThread().getName());
			LOGGER.info("on each " + customer.toString() + " age " + customer.getAge());
		});

		LOGGER.info("consuming flapMap");
		aCustomers.stream().flatMap(cust -> cust.getAddresses().stream())
				.forEach(address -> LOGGER.info(address.toString()));


		IntStream.range(1,21)
				.mapToObj(n-> {

					LOGGER.info("inside mapToObj " + n);

					String firstCodeLetter = "TX";
					if(n % 2 == 0)
						firstCodeLetter = "CA";

					Customer cust = new Customer(n, n * 2);
					cust.setCustId(firstCodeLetter + n);

					return cust;
				})
				.peek(customer -> {
					LOGGER.info("inside peek " + customer.getCustId());
					IntStream.range(1,5)
							.mapToObj(n -> new Address("USA","TX",     customer.getCustId() +" addressLine" + n))
					.forEach(address -> customer.addAddress(address));

				})
				.collect(Collectors.groupingBy(customer -> customer.getCustId().substring(0,2)))
				.forEach((s, customers) -> {
					LOGGER.info("inside for each grouping " + s  + " count " + customers.size());

				});
				//.flatMap(customer -> customer.getAddresses().stream())
				//.forEach(address -> LOGGER.info(address.toString()));


	}





}
