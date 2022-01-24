package com.example.demo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppController implements ErrorController{
	private final static String PATH = "/error";
	@Override
	@RequestMapping(PATH)
	@ResponseBody
	public String getErrorPath() {
		return "No Mapping Found";
	}

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TicketRepository ticketrepository;
	
	@Autowired
	private TicketService service;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String displayDashboard(Model model) {
		Long resolved = ticketrepository.getResolvedCount();
		Long pending = ticketrepository.getPendingCount();
		Long query = ticketrepository.getQueryCount();
		Long service = ticketrepository.getServiceCount();
		Long complaint = ticketrepository.getComplaintCount();
		Long total = ticketrepository.getTotalCount();
		model.addAttribute("resolved", resolved);
		model.addAttribute("pending", pending);
		model.addAttribute("query", query);
		model.addAttribute("service", service);
		model.addAttribute("complaint", complaint);
		model.addAttribute("total", total);
		return "dashboard";
	}
	
	@RequestMapping("/redirect")
	public String defaultAfterDashboard(@AuthenticationPrincipal CustomUserDetails user) {
		if(user.getUserType().equals("H.O. User")) {
			return "redirect:/tickets";
		}
		return "redirect:/customercare";
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
	
	/*@GetMapping("/tickets")
	public String displayTickets(Model model, Tickets ticket) {
		//List<Tickets> displayTickets = ticketrepository.findAll();
		List<Tickets> displayTickets = ticketrepository.findByStatus(ticket.getStatus());
		model.addAttribute("displayTickets", displayTickets);
		
		return "display_tickets";
	}*/
	
	
	
	
	@GetMapping("/tickets")
	public String displayTickets(Model model, Tickets ticket, @Param("keyword") String keyword) {
		//List<Tickets> displayTickets = ticketrepository.findAll();
		//List<Tickets> displayTickets = ticketrepository.findByStatus(ticket.getStatus());
		List<Tickets> displayTickets = service.listAll(ticket, keyword);
		model.addAttribute("displayTickets", displayTickets);
		model.addAttribute("keyword", keyword);
		
		return "display_tickets";
	}
	
	@RequestMapping("/customercare")
    public String filterTickets(@AuthenticationPrincipal CustomUserDetails user, Model model, Tickets ticket, @Param("keyword") String keyword) {
        List<Tickets> displayUserTickets = service.listByUser(user.getCurrentUserId(), keyword);
        //List<Tickets> displayUserTickets = service.listAll(keyword);
        model.addAttribute("displayUserTickets", displayUserTickets);
        model.addAttribute("keyword", keyword);
         
        return "customercareDisplay";
    }
	
	
	@GetMapping("/raiseticketform")
	public String showRaiseTicketForm(Model model) {
		model.addAttribute("ticket", new Tickets());
		
		return "raiseTicket";
	}
	
	@PostMapping("/raiseTicket")
	public String raiseTicket(@AuthenticationPrincipal CustomUserDetails user, Tickets ticket, 
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		if(ticket.getCategory().equals("Query")) {
			ticket.setStatus("Resolved");
		}
		else {
			ticket.setStatus("Open");
		}
		Long userid = user.getCurrentUserId();
		String ccex = user.getFullName();
		ticket.setId(userid);
		ticket.setCustomerCareExecutive(ccex);
		//ticketrepository.save(ticket);
		
		ticket.setDate(LocalDate.now());
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        ticket.setPhotos(fileName);
        ticketrepository.save(ticket);
         
        //User savedUser = repo.save(user);
 
        String uploadDir = "ticket-photos/" + ticket.getId();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		
		
		
		return "ticket_raise_success";
	}
	
	@PostMapping("/update_tickets")
	public String getTicket(@RequestParam("ticket_id") long ticket_id, 
							@RequestParam("status") String status,
							@RequestParam("remarks") String remarks) {
		Tickets ticket = ticketrepository.findByTicketId(ticket_id);
		ticket.setStatus(status);
		ticket.setRemarks(remarks);
		
		ticketrepository.save(ticket);
		
		return "status_update_success";
	}
	
}