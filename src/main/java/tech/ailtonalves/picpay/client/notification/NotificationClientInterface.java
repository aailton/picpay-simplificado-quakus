package tech.ailtonalves.picpay.client.notification;

import tech.ailtonalves.picpay.dto.TransferDTO;

public interface NotificationClientInterface {
	
	public void sendNotification(TransferDTO dto);

}
