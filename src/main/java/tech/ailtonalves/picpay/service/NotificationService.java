package tech.ailtonalves.picpay.service;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tech.ailtonalves.picpay.client.notification.NotificationClientInterface;
import tech.ailtonalves.picpay.dto.TransferDTO;

@ApplicationScoped
public class NotificationService implements NotificationServiceInterface {
	
	@Inject
	NotificationClientInterface notificationClientInterface;
	
	private static final Logger LOG = Logger.getLogger(NotificationService.class);
	
    public void sendNotification(TransferDTO dto) {
    	
        try {
            for (int i = 1; i <= 10; i++) {
            	
                try {
                    LOG.info("Tentativa " + i +" de enviar notificacao...");
                    notificationClientInterface.sendNotification(dto);
                    LOG.info("Notificacao enviada com sucesso na tentativa " + i);
                    break;  
                } catch (Exception e) {
                    LOG.error("Falha na tentativa " + i + ". Erro: " + e.getLocalizedMessage());
                    if (i == 10) {
                        throw e; 
                    }
                    Thread.sleep(100); 
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
