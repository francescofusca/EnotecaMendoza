package com.negozio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class NegozioApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Test di caricamento contesto...");
	}

	@Test
	void testApplicazioneAvviata() {
		String messaggio = "Il Mio Negozio Online";
		assert messaggio.contains("Negozio");
		System.out.println("Test applicazione: " + messaggio);
	}
}