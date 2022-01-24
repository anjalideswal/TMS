package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
	@Query("SELECT u FROM Tickets u WHERE u.ticket_id = ?1")
	public Tickets findByTicketId(Long ticket_id);
	
	@Query("SELECT u FROM Tickets u WHERE u.id = ?1")
	public List<Tickets> findByUserId(Long id);

	@Query("SELECT u FROM Tickets u WHERE u.status NOT LIKE 'Resolved'")
	public List<Tickets> findByStatus(String status);
		
	@Query("SELECT u FROM Tickets u WHERE u.id = ?2 AND CONCAT(u.status,' ', u.date,' ', u.ticket_id) LIKE %?1%")
	public List<Tickets> search(String keyword, Long id);

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.status = 'Resolved'")
	public Long getResolvedCount();

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.status = 'Pending'")
	public Long getPendingCount();

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.category = 'Query' AND u.date >= current_date - 16")
	public Long getQueryCount();

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.category = 'Service' AND u.date >= current_date - 16")
	public Long getServiceCount();

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.category = 'Complaint' AND u.date >= current_date - 16")
	public Long getComplaintCount();

	@Query("SELECT COUNT(*) FROM Tickets u WHERE u.date >= current_date - 16")
	public Long getTotalCount();
	
	/*@Query("SELECT COUNT(*) FROM Tickets u WHERE u.date BETWEEN CURDATE() AND (CURDATE() - 10)")
	public Long getTotalCount();*/

	@Query("SELECT u FROM Tickets u WHERE CONCAT(u.status,' ', u.date,' ', u.ticket_id) LIKE %?1%")
	public List<Tickets> searchAll(String keyword);

}  


