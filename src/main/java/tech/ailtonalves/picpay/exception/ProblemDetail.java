package tech.ailtonalves.picpay.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.ConstraintViolationException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetail {
	
	/**
	 * https://medium.com/@alissonmn/quarkus-padronize-o-retorno-de-erros-15c235f10cb2
	 */
	
	public int status;
	public OffsetDateTime timestamp;
	public String title;
	public String detail;
	public List<ProblemObject> messages;
	
	public ProblemDetail() {
		
	}
	
	public ProblemDetail(BusinessException e) {
		this.status = 422;
		this.timestamp = OffsetDateTime.now();
		this.title = "Business";
		this.detail = e.getLocalizedMessage();
	}
	
	public ProblemDetail(ConstraintViolationException e) {
		this.status = 400;
		this.timestamp = OffsetDateTime.now();
		this.title = "Invalid data";
		this.detail = "Dados inválidos";
		this.messages = new ArrayList<>();
	}

}
