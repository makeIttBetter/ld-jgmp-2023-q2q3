package org.example2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = FlywayDemoApplication.class)
@TestPropertySource(locations="classpath:test-app-flyway.properties")
class FlywayDemoApplicationTest {

	@Test
	void contextLoads() {
	}

}
