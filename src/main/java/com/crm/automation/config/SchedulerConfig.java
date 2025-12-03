package com.crm.automation.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@EnableScheduling
public class SchedulerConfig {
	 // This class enables Spring's scheduler system.
    // Any method annotated with @Scheduled in the project will now automatically run based on its configuration.

}
