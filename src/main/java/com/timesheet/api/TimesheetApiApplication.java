package com.timesheet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.timesheet.api.config.property.TimesheetApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(TimesheetApiProperty.class)
public class TimesheetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApiApplication.class, args);
	}

}
