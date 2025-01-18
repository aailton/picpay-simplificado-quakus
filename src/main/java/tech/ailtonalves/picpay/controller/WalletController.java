package tech.ailtonalves.picpay.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
public class WalletController {
	
	@Inject
	WalletService walletService;
	
	@GET
	@Produces
	public List<Wallet> getWallet() {
		return walletService.getAllWallts();
	}
	
	@POST
	@Transactional
	@Path("/create")
	public Response createWallet(@Valid WalletDTO dto) throws BusinessException {
		
		walletService.createWallet(dto);
		return Response.status(Response.Status.CREATED).entity(dto).build();
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
