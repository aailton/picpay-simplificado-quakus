package tech.ailtonalves.picpay.exception;

import org.jboss.logging.Logger;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionProvider implements ExceptionMapper<BusinessException> {
	
	private static final Logger LOG = Logger.getLogger(BusinessExceptionProvider.class);
	
	@Override
	public Response toResponse(BusinessException e) {
		ProblemDetail problem = new ProblemDetail(e);
		LOG.error(e);
		return Response.status(422).entity(problem)
				.type(MediaType.APPLICATION_JSON).build();
	}

}
