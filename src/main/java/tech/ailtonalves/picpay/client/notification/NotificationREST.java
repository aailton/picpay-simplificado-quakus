package tech.ailtonalves.picpay.client.notification;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import tech.ailtonalves.picpay.dto.TransferDTO;

@RegisterRestClient(configKey = "notification")
public interface NotificationREST {
	
	@POST
	@Transactional
	public void sendNotification(TransferDTO dto);

}
