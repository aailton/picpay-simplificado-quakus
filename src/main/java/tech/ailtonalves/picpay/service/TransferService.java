package tech.ailtonalves.picpay.service;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tech.ailtonalves.picpay.dto.TransferDTO;
import tech.ailtonalves.picpay.entity.Transfer;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.repository.TransferRepository;
import tech.ailtonalves.picpay.repository.WalletRepository;

@ApplicationScoped
public class TransferService implements TransferServiceInterface {
	
	@Inject
	WalletRepository walletRepository;
	
	@Inject
	TransferRepository transferRepository;
	
	private static final Logger LOG = Logger.getLogger(TransferService.class);
	
	@Override
	public Transfer createTransfer(TransferDTO transferDTO) {
		LOG.info("Solicitacao de transferencia: " + transferDTO);
		
		Wallet walletPayer = walletRepository.findById(transferDTO.payer());
		Wallet walletPayee = walletRepository.findById(transferDTO.payee());
		
		walletPayer.debit(transferDTO.value());
		walletPayee.credit(transferDTO.value());
		
		walletRepository.persist(walletPayer);
		walletRepository.persist(walletPayee);
		
		transferRepository.persist(transferDTO.toTransfer(null, walletPayee, walletPayer));
		
		return transferDTO.toTransfer(null, walletPayee, walletPayer);
		
	}
	
	@Override
	public List<Transfer> getAllTransfer() {
		return transferRepository.listAll();
	}

}
