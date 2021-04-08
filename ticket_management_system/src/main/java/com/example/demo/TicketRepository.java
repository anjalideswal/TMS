package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
	
	@Query("SELECT u FROM Tickets u WHERE u.ticket_id = ?1")
	public Tickets findByTicketId(Long ticket_id);
	
	
	/*@Query("FROM Tickets")
	public List<Tickets> findAll();*/
	
}


