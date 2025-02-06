package tech.ailtonalves.picpay.service;

import tech.ailtonalves.picpay.dto.TransferDTO;

public interface NotificationServiceInterface {
	
	public void sendNotification(TransferDTO dto);

}
