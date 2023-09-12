package com.example.practice;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeApplicationTests {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	void testJpa() {
		Client client = new Client();

		client.setName("test");
		client.setEmail("test@gmail.com");
		client.setCreatedAt(LocalDateTime.now());

		this.clientRepository.save(client);
	}
}
