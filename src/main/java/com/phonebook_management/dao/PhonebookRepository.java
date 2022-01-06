package com.phonebook_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phonebook_management.model.Phonebook;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {

	public Phonebook getByName(String name);

	public Phonebook getByNumber(Long number);
}
