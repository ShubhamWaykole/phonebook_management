package com.phonebook_management.services;

import java.util.List;

import com.phonebook_management.model.Phonebook;

public interface PhonebookService {

	public Phonebook add(String name, Long number);

	public Phonebook update(String name, Long number);

	public void delete(Long id);

	public List<Phonebook> getAllRecords();
	
	public Phonebook getById(Long id);
	
	public Phonebook getByName(String name);
	
	public Phonebook getByNumber(Long number);
}
