package org.example2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = LiquibaseDemoApplication.class)
@TestPropertySource(locations="classpath:test-app-liquibase.properties")
class LiquibaseDemoApplicationTest {

	@Test
	void contextLoads() {
	}

}
