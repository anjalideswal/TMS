package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TicketRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private TicketRepository trepo;
    
    @Test
    public void testCreateTicket() {
        Tickets ticket = new Tickets();
        ticket.setCustomer_id(001);
        ticket.setFirstName("Jane");
        ticket.setLastName("Sloan");
        ticket.setCategory("Query");
        ticket.setDescription("When will the service resume?");
        /*ticket.setDateOfLogging('2014-01-18');
        ticket.setDateOfResolution('2014-01-21');*/
        ticket.setStatus("Resolved");
        
        
        Tickets savedTicket = trepo.save(ticket);
        
        Tickets existTicket = entityManager.find(Tickets.class, savedTicket.getTicket_id());
        
        assertThat(ticket.getCustomer_id()).isEqualTo(existTicket.getCustomer_id());
    }
    
    /*@Test
    public void testFindUserByEmail() {
    	String email = "anjali@gmail.com";
    	
    	User user  = repo.findByEmail(email);
    	
    	assertThat(user).isNotNull();
    	
    }*/
}
