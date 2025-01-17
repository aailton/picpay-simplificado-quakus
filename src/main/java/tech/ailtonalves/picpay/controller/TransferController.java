package tech.ailtonalves.picpay.controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/transfer")
public class TransferController {
	
	@POST
	public String transfer() {
		return null;
	}

}
