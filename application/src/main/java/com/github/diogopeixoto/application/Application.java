package com.github.diogopeixoto.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.NativeHints;
import org.springframework.nativex.hint.TypeHint;

import com.github.diogopeixoto.controller.exception.ExceptionResponse;
import com.github.diogopeixoto.core.empresas.Empresas;
import com.github.diogopeixoto.core.empresas.EmpresasService;
import com.github.diogopeixoto.core.empresas.EmpresasServiceImpl;

@SpringBootApplication(scanBasePackages = { "com.github.diogopeixoto" })
@EnableJpaRepositories(basePackages = "com.github.diogopeixoto.repository")
@EntityScan(basePackages = "com.github.diogopeixoto.repository")
@NativeHints(value = { @NativeHint(types = @TypeHint(types = ExceptionResponse.class)) })
public class Application {

	private Empresas empresas;

	public Application(Empresas empresas) {
		super();
		this.empresas = empresas;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public EmpresasService empresasService() {
		return new EmpresasServiceImpl(empresas);
	}
}
