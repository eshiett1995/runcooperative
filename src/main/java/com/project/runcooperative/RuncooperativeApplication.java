package com.project.runcooperative;

import com.project.runcooperative.web.entities.Priviledge;
import com.project.runcooperative.web.services.PriviledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class RuncooperativeApplication {

	@Autowired
	static PriviledgeService priviledgeService;

	public static void main(String[] args) {
		SpringApplication.run(RuncooperativeApplication.class, args);



	}

	@Bean
	@Primary
	@ConfigurationProperties("main.datasource")
	public DataSourceProperties mainDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("main.datasource")
	public DataSource mainDataSource() {
		return mainDataSourceProperties().initializeDataSourceBuilder().build();
	}




	@Bean
	@ConfigurationProperties("session.datasource")
	public EmbeddedDatabase sessionDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("org/springframework/session/jdbc/schema-h2.sql").build();
	}


	@Bean(name = "springSessionJdbcOperations")
	public JdbcTemplate springSessionJdbcOperations(@Qualifier("sessionDataSource") DataSource secondaryDataSource) {

		return new JdbcTemplate(secondaryDataSource);
	}

}
