package tech.ailtonalves.picpay.client.authorization;

import java.math.BigDecimal;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import tech.ailtonalves.picpay.entity.Wallet;

@ApplicationScoped
public class AuthClient implements AuthClientInterface {

	@RestClient
	AuthREST authREST;
	
	@Override
	public Response authorizeTransaction(Wallet payer, BigDecimal value) throws WebApplicationException {

        try {
            return authREST.authorizeTransaction();
        } catch (WebApplicationException e) {
        	if (e.getResponse().getStatus() == Response.Status.FORBIDDEN.getStatusCode()) {
        		return Response.status(Response.Status.FORBIDDEN).build();
        	}
            throw new WebApplicationException (e.getMessage());
        }
    }

}
