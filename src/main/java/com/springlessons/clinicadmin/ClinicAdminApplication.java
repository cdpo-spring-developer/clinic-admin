package com.springlessons.clinicadmin;

import com.springlessons.clinicadmin.examples.profiles.SettingsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SettingsService.class)
public class ClinicAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicAdminApplication.class, args);
	}

}
