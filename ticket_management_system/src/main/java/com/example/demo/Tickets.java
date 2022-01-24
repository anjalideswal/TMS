package com.example.demo;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


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
    
    @Column(name = "date", nullable = false, length = 20) 
    private LocalDate date;
     
    @Column(name = "category", nullable = false, length = 20)
    private String category;
    
    @Column(name = "description", nullable = false, length = 45)
    private String description;
    
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    
    @Column(name = "id", nullable = false, length = 45)
    private Long id;
    
    @Column(name = "customerCareExecutive", nullable = false, length = 45)
    private String customerCareExecutive;
    
    @Column(name = "remarks", columnDefinition = "varchar(255) default 'NA'")
    private String remarks;
    
    @Column(nullable = true, length = 64)
    private String photos;
    
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
	
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	
	public LocalDate getDate(LocalDate date) {
		return date;
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
	
	public void setCustomerCareExecutive(String customerCareExecutive) {
		this.customerCareExecutive = customerCareExecutive;
	}
	
	public String getCustomerCareExecutive() {
		return customerCareExecutive;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhotos() {
		return photos;
	}
	
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
         
        return "/ticket-photos/" + id + "/" + photos;
    }
}
