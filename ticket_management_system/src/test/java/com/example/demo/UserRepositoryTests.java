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
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
    
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setPassword("abc");
        user.setFirstName("Abc");
        user.setLastName("Def");
        user.setUserType("Customer Care Executive");
  
        
        User savedUser = repo.save(user);
        
        User existUser = entityManager.find(User.class, savedUser.getId());
        
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
    
    @Test
    public void testFindUserByEmail() {
    	String email = "anjali@gmail.com";
    	
    	User user  = repo.findByEmail(email);
    	
    	assertThat(user).isNotNull();
    	
    }
}