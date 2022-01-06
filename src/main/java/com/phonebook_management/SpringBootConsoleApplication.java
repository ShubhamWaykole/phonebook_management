package com.phonebook_management;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phonebook_management.exceptions.NameAlreadyExistsException;
import com.phonebook_management.exceptions.NoRecordsFoundException;
import com.phonebook_management.exceptions.NumberAlreadyExistsException;
import com.phonebook_management.model.Phonebook;
import com.phonebook_management.services.PhonebookService;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

	@Autowired
	PhonebookService phonebookService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsoleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------Phonebook Management System-------------");
		while (true) {
			System.out.println();
			System.out.println("Select option from the menu");
			System.out.println("1. Create phonebook record");
			System.out.println("2. Update phonebook record");
			System.out.println("3. Delete phonebook record");
			System.out.println("4. Get all records");
			System.out.println("5. Get record by id");
			System.out.println("6. Get record by name");
			System.out.println("7. Get record by number");
			System.out.println("8. Exit");
			Scanner s = new Scanner(System.in);
			Long id;
			String name;
			Long number;

			switch (s.nextInt()) {
			case 1:
				System.out.println("Enter name: ");
				name = s.next();
				System.out.println("Enter number: ");
				number = s.nextLong();
				try {
					phonebookService.add(name, number);
					System.out.println("Records created successfuly");
				} catch (NameAlreadyExistsException | NumberAlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter name: ");
				name = s.next();
				System.out.println("Enter number: ");
				number = s.nextLong();
				try {
					phonebookService.update(name, number);
					System.out.println("Records updated successfuly");
				} catch (NameAlreadyExistsException | NumberAlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter phonebook id: ");
				id = s.nextLong();
				try {
					phonebookService.delete(id);
					System.out.println("Records deleted successfuly");
				} catch (NoRecordsFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("id number        name");
					for (Phonebook phonebook : phonebookService.getAllRecords()) {
						System.out.println(phonebook.toString());
					}
				} catch (NoRecordsFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter phonebook id: ");
				id = s.nextLong();
				try {
					System.out.println("id number        name");
					System.out.println(phonebookService.getById(id).toString());
				} catch (NoRecordsFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter phonebook name: ");
				name = s.next();
				try {
					System.out.println("id number        name");
					System.out.println(phonebookService.getByName(name).toString());
				} catch (NoRecordsFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.out.println("Enter phonebook number: ");
				number = s.nextLong();
				try {
					System.out.println("id number        name");
					System.out.println(phonebookService.getByNumber(number).toString());
				} catch (NoRecordsFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				System.out.println("Exit");
				return;
			}
		}
	}

}
