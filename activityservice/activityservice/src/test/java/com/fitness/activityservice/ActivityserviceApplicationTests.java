package com.fitness.activityservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.cloud.config.enabled=false",
		"spring.kafka.topic.name=test-topic",
		"spring.kafka.bootstrap-servers=localhost:9092"
})
class ActivityserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
