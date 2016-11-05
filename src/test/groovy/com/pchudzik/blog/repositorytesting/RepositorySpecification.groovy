package com.pchudzik.blog.repositorytesting

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

import javax.transaction.Transactional

@Transactional
@SpringApplicationConfiguration([RepositoryTestingApplication.class, TestConfiguration.class])
@TestPropertySource(properties = ["spring.profiles.active=dev,test"])
abstract class RepositorySpecification extends Specification {
	@Configuration
	static class TestConfiguration {
		@Bean
		@Profile("test")
		public FlywayMigrationStrategy migrationStrategy() {
			return { flyway ->
				flyway.clean();
				flyway.migrate();
			}
		}
	}
}
