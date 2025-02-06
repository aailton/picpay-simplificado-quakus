package tech.ailtonalves.picpay.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import tech.ailtonalves.picpay.dto.TransferDTO;
import tech.ailtonalves.picpay.dto.TransferResponse;
import tech.ailtonalves.picpay.entity.Transfer;
import tech.ailtonalves.picpay.service.TransferService;

@Path("/transfer")
@RequestScoped
public class TransferController {
	
	@Inject
	TransferService transferService;
	
	@POST
	public Response createTransfer(@Valid TransferDTO dto) throws Exception {
		
		transferService.createTransfer(dto);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Produces
	public List<TransferResponse> getTransfer() {
		
        List<Transfer> transferencias = transferService.getAllTransfer();

        return transferencias.stream()
            .map(transferencia -> new TransferResponse(
                    transferencia.getId(),
                    transferencia.getPayerId().getId(),
                    transferencia.getPayeeId().getId(),
                    transferencia.getAuthorizationStatus(),
                    transferencia.getAmount(),
                    transferencia.getTimestamp().toString()))
            .collect(Collectors.toList());
	}

}
