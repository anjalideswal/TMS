package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TicketRepository trepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<User> listUsers = repo.findAll();
	    model.addAttribute("listUsers", listUsers);
	    
	    return "users";
	}
	
	@GetMapping("/tickets")
	public String displayTickets(Model model) {
		List<Tickets> displayTickets = trepo.findAll();
		model.addAttribute("displayTickets", displayTickets);
		
		return "display_tickets";
	}
	
	@GetMapping("/raiseticketform")
	public String showRaiseTicketForm(Model model) {
		model.addAttribute("ticket", new Tickets());
		
		return "raiseTicket";
	}
	
	@PostMapping("/raiseTicket")
	public String raiseTicket(Tickets ticket) {
		if(ticket.getCategory().equals("Query")) {
			ticket.setStatus("Resolved");
		}
		else {
			ticket.setStatus("Open");
		}
		trepo.save(ticket);
		return "ticket_raise_success";
	}
	
	@PostMapping("/update_tickets")
	public String getTicket(@RequestParam("ticket_id") long ticket_id, 
							@RequestParam("status") String status) {
		Tickets ticket = trepo.findByTicketId(ticket_id);
		ticket.setStatus(status);
		
		trepo.save(ticket);
		
		return "status_update_success";
	}
}