package tech.ailtonalves.picpay.client.notification;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import tech.ailtonalves.picpay.dto.TransferDTO;

@ApplicationScoped
public class NotificationClient implements NotificationClientInterface {
	
	@RestClient
	NotificationREST notificationREST;

	@Override
	public void sendNotification(TransferDTO dto) {
		notificationREST.sendNotification(dto);

	}

}
