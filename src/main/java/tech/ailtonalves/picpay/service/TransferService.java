package tech.ailtonalves.picpay.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import tech.ailtonalves.picpay.client.authorization.AuthClientInterface;
import tech.ailtonalves.picpay.dto.TransferDTO;
import tech.ailtonalves.picpay.entity.Transfer;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.entity.WalletType;
import tech.ailtonalves.picpay.exception.BusinessException;
import tech.ailtonalves.picpay.repository.TransferRepository;
import tech.ailtonalves.picpay.repository.WalletRepository;

@ApplicationScoped
public class TransferService implements TransferServiceInterface {
	
	@Inject
	WalletRepository walletRepository;
	
	@Inject
	TransferRepository transferRepository;
	
	@Inject
	AuthClientInterface authClientInterface;
	
	@Inject
	NotificationService notificationService;
	
	private static final Logger LOG = Logger.getLogger(TransferService.class);
	
	@Override
	@Transactional
	public Transfer createTransfer(TransferDTO transferDTO) throws BusinessException {
		LOG.info("Solicitacao de transferencia: " + transferDTO);
		
		Optional<Wallet> optionalPayer = walletRepository.findByIdOptional(transferDTO.payer());
		Wallet walletPayer = optionalPayer.orElseThrow(() -> new BusinessException("Invalid payer"));
		
		Optional<Wallet> optionalPayee = walletRepository.findByIdOptional(transferDTO.payee());
		Wallet walletPayee = optionalPayee.orElseThrow(() -> new BusinessException("Invalid payee"));
		
		validate(transferDTO, walletPayer);
		
		var isAuth = authClientInterface.authorizeTransaction(walletPayer, transferDTO.value());
		
		if(isAuth.getStatus() == Response.Status.FORBIDDEN.getStatusCode()) {
			transferRepository.persist(transferDTO.toTransfer("Not Authorized", walletPayee, walletPayer));
			throw new BusinessException("Transfer Not Authorized");
		}
		
		walletPayer.debit(transferDTO.value());
		walletPayee.credit(transferDTO.value());
		
		walletRepository.persist(walletPayer);
		walletRepository.persist(walletPayee);
		
		transferRepository.persist(transferDTO.toTransfer("Authorized", walletPayee, walletPayer));
		
		CompletableFuture.runAsync(() -> notificationService.sendNotification(transferDTO));
		
		return transferDTO.toTransfer("Authorized", walletPayee, walletPayer);
		
	}
	
	@Override
	public List<Transfer> getAllTransfer() {
		return transferRepository.listAll();
	}
	
	private void validate(TransferDTO transferDTO, Wallet walletPayer) throws BusinessException {
		
		if(transferDTO.payer().equals(transferDTO.payee())) {
			throw new BusinessException("Invalid transaction");
		}
		
		if(walletPayer.getWalletType().equals(WalletType.MERCHANT)) {
			throw new BusinessException("Invalid payer wallet type");
		}
		
		if(walletPayer.getBalance().compareTo(transferDTO.value()) < 0 ) {
			throw new BusinessException("Insufficient balance");
		}
	
	}

}
