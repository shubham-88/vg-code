package org.vg.pv.pv_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PvAppApplication {

	public static void main(String[] args) {
        System.out.println("JVM TimeZone: " + java.util.TimeZone.getDefault().getID());
		SpringApplication.run(PvAppApplication.class, args);
	}

}
