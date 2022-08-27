 package br.com.dh.ClinicaOdontologica;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	/**
	 * Sets whether a property should be skipped or not when the property value is null
	 * @return ModelMapper set to skip Null Enable
	 */
	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
