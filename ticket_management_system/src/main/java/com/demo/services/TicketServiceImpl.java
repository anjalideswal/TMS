package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.TicketRepository;
import com.example.demo.Tickets;

@Transactional
@Service("ticketService")
public class TicketServiceImpl implements TicketService {
		
		@Autowired
		private TicketRepository ticketRepository;
		
		@Override
		public Tickets update(Tickets ticket) {
			return ticketRepository.save(ticket);
		}
}
