package com.example.demo;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketrepository;
	
	public List<Tickets> listByUser(Long id, String keyword) {
		
        if (keyword != null) {
            return ticketrepository.search(keyword, id);
        }
        return ticketrepository.findByUserId(id);
    }

	public List<Tickets> listAll(Tickets ticket, String keyword) {
		
		if(keyword != null) {
			return ticketrepository.searchAll(keyword);
		}
		return ticketrepository.findByStatus(ticket.getStatus());
	}
	
}
