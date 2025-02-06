package tech.ailtonalves.picpay.controller;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import tech.ailtonalves.picpay.dto.WalletDTO;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.exception.BusinessException;
import tech.ailtonalves.picpay.service.WalletService;

@Path("/wallets")
@RequestScoped
public class WalletController {
	
	@Inject
	WalletService walletService;
	
	@GET
	@Produces
	public List<Wallet> getWallet() {
		return walletService.getAllWallts();
	}
	
	@POST
	@Path("/create")
	public Response createWallet(@Valid WalletDTO dto) throws BusinessException {
		
		walletService.createWallet(dto);
		return Response.status(Response.Status.CREATED).entity(dto).build();
	}
	
}
