package tech.ailtonalves.picpay.client.authorization;

import java.math.BigDecimal;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import tech.ailtonalves.picpay.entity.Wallet;

public interface AuthClientInterface {
	
	public Response authorizeTransaction(Wallet payer, BigDecimal value) throws WebApplicationException ;

}
