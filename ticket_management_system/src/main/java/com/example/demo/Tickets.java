package com.example.demo;

//import java.util.Date;

//import java.util.function.IntPredicate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

@Entity
@Table(name = "tickets")
public class Tickets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
     
    @Column(nullable = false, unique = true, length = 45)
    private Long customer_id;
    
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
     
    @Column(name = "category", nullable = false, length = 20)
    private String category;
    
    @Column(name = "description", nullable = false, length = 45)
    private String description;
     
    /*@Column(name = "date_of_logging", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_of_logging;
    
    @Column(name = "date_of_res", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_of_res;*/
    
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    
    @Column(name = "id", nullable = true, length = 45)
    private Long id;
    
    public void setTicket_id(Long ticket_id) {
		this.ticket_id = ticket_id;
	}
    
    public Long getTicket_id() {
		return ticket_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	
	public Long getCustomer_id() {
		return customer_id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	/*public void setDateOfLogging(Date date_of_logging) {
		this.date_of_logging = date_of_logging;
		
	}

	public void setDateOfResolution(Date date_of_res) {
		this.date_of_res = date_of_res;
	}*/

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
