package com.phonebook_management.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.phonebook_management.dao.PhonebookRepository;
import com.phonebook_management.exceptions.NameAlreadyExistsException;
import com.phonebook_management.exceptions.NoRecordsFoundException;
import com.phonebook_management.exceptions.NumberAlreadyExistsException;
import com.phonebook_management.model.Phonebook;

@Service
public class PhonebookServiceImpl implements PhonebookService {

	@Autowired
	PhonebookRepository phonebookRepository;

	@Override
	public Phonebook add(String name, Long number) {
		Phonebook phonebook = new Phonebook(name, number);
		try {
			phonebookRepository.save(phonebook);
		} catch (DataIntegrityViolationException e) {
			if (null != phonebookRepository.getByName(name))
				throw new NameAlreadyExistsException("Name " + name + " already exists in phonebook.");
			else if (null != phonebookRepository.getByNumber(number))
				throw new NumberAlreadyExistsException("Number " + number + " already exists in phonebook.");
		}
		return phonebook;
	}

	@Override
	public Phonebook update(String name, Long number) {
		Phonebook phonebookByName = phonebookRepository.getByName(name);
		if (null != phonebookRepository.getByNumber(number))
			throw new NumberAlreadyExistsException("Number " + number + " already exists in phonebook with another user.");
		phonebookByName.setNumber(number);
		return phonebookRepository.save(phonebookByName);
	}

	@Override
	public void delete(Long id) {
		if (null == phonebookRepository.getById(id))
			throw new NoRecordsFoundException("No record found with given id: " + id);
		phonebookRepository.deleteById(id);
		return;
	}

	@Override
	public List<Phonebook> getAllRecords() {
		List<Phonebook> phonebooks = new ArrayList<Phonebook>();
		phonebooks = phonebookRepository.findAll();
		if (0 == phonebooks.size())
			throw new NoRecordsFoundException("No records found in phonebook.");
		return phonebooks;
	}

	@Override
	public Phonebook getById(Long id) {
		Phonebook phonebook = phonebookRepository.getById(id);
		if (null == phonebook)
			throw new NoRecordsFoundException("No record found with given id: " + id);
		return phonebook;
	}

	@Override
	public Phonebook getByName(String name) {
		Phonebook phonebook = phonebookRepository.getByName(name);
		if (null == phonebook)
			throw new NoRecordsFoundException("No record found with given name: " + name);
		return phonebook;
	}

	@Override
	public Phonebook getByNumber(Long number) {
		Phonebook phonebook = phonebookRepository.getByNumber(number);
		if (null == phonebook)
			throw new NoRecordsFoundException("No record found with given number: " + number);
		return phonebook;
	}
}
