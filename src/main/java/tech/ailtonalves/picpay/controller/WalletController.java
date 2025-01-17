package tech.ailtonalves.picpay.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/wallets")
public class WalletController {
	
	@GET
	@Produces
	public String getWallet() {
		return "Buscou por uma carteira com sucesso!";
	}
	
	@POST
	@Path("/create")
	public String createWallet() {
		return null;
	}
	
	@POST
	@Path("/deposit")
	public String depositIntoWallet() {
		return null;
	}
	
	@POST
	@Path("/withdraw")
	public String withdrawFromWallet() {
		return null;
	}
	
}
