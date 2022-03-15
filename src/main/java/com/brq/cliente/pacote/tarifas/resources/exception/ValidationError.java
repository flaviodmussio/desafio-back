package com.brq.cliente.pacote.tarifas.resources.exception;

import java.util.ArrayList;
import java.util.List;

//vai ter todos os dados que tem no StandardError mais a lista de mensagens FieldMessage
public class ValidationError extends StandardError {
	

	public ValidationError(Long timestamp, Integer status, String msg) {
		super(timestamp, status, msg);
	}

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
