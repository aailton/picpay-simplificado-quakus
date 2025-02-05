package tech.ailtonalves.picpay.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;

@RegisterRestClient(configKey = "authorization")
public interface AuthREST {
	
	@GET
	public Response authorizeTransaction();

}
