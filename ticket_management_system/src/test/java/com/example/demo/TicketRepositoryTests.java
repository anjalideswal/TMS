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
    private TicketRepository ticketrepository;
    
    @Test
    public void testCreateTicket() {
        Tickets ticket = new Tickets();
        ticket.setCustomer_id(002);
        ticket.setFirstName("Helen");
        ticket.setLastName("Sloan");
        ticket.setCategory("Query");
        ticket.setDescription("When will the service resume?");
        ticket.setStatus("Resolved");
        ticket.setCustomerCareExecutive("Anjali Deswal");
        ticket.setTicket_id((long) 1);
        ticket.setRemarks("fixed");
        ticket.setId((long) 1);
        
        
        
        Tickets savedTicket = ticketrepository.save(ticket);
        
        Tickets existTicket = entityManager.find(Tickets.class, savedTicket.getTicket_id());
        
        assertThat(ticket.getCustomer_id()).isEqualTo(existTicket.getCustomer_id());
    }
}
