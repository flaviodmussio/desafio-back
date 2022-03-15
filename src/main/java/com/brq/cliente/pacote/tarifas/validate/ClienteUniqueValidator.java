package com.brq.cliente.pacote.tarifas.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brq.cliente.pacote.tarifas.services.ClienteService;


public class ClienteUniqueValidator implements ConstraintValidator<Unique,String> {
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
    public void initialize(Unique unique) {
        unique.message();
    }

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (clienteService != null && clienteService.existsByCpf(cpf)) {
            return false;
        }
        return true;
	}

}
