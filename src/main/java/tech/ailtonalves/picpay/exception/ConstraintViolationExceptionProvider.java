package tech.ailtonalves.picpay.exception;

import java.util.Iterator;
import java.util.Set;

import org.jboss.logging.Logger;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionProvider implements ExceptionMapper<ConstraintViolationException> {
	
	private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionProvider.class);
	
	@Override
	public Response toResponse(ConstraintViolationException e) {
		
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            LOG.error(violation.getPropertyPath() + " was reported as null: " + violation);
        }
        
		ProblemDetail problem = new ProblemDetail(e);
		
		e.getConstraintViolations().forEach( (c) -> {
			problem.messages
					.add(new ProblemObject(lastFieldName(c.getPropertyPath(). iterator()), c.getMessage()));
		});
		
		return Response.status(Status.BAD_REQUEST).entity(problem).type(MediaType.APPLICATION_JSON).build();
	}
	
	String lastFieldName(Iterator<Path.Node> nodes){
        Path.Node last = null;
        while(nodes.hasNext()){
            last = nodes.next();
        }

        return last.getName();
    }

}
