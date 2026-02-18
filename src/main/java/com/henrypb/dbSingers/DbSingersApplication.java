package com.henrypb.dbSingers;

import com.henrypb.dbSingers.principal.Principal;
import com.henrypb.dbSingers.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbSingersApplication implements CommandLineRunner {

    @Autowired
    private CantanteRepository cantanteRepository;
	public static void main(String[] args) {
		SpringApplication.run(DbSingersApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(cantanteRepository);
        principal.mostrarMenu();
    }
}
