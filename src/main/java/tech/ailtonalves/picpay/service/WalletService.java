package tech.ailtonalves.picpay.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import tech.ailtonalves.picpay.dto.WalletDTO;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.exception.BusinessException;
import tech.ailtonalves.picpay.repository.WalletRepository;

@ApplicationScoped
public class WalletService implements WalletServiceInterface {
	
	@Inject
	WalletRepository walletRepository;
	
	private static final Logger LOG = Logger.getLogger(WalletService.class);
	
	@Override
	@Transactional
	public Wallet createWallet(WalletDTO walletDTO) throws BusinessException  {
		LOG.info("Solicitacao para criacao da carteira : " + walletDTO);
		
		Optional<Wallet> validation = walletRepository.findByDocumentOrEmail(walletDTO.document(), walletDTO.email());
		
		if (validation.isPresent()) {
            throw new BusinessException ("ERROR: document and/or email already registered!");
        }
		
		walletRepository.persist(walletDTO.toWallet());
		LOG.info("Carteira criada com sucesso!");
		return walletDTO.toWallet();
	
	}
	
	@Override
	public List<Wallet> getAllWallts() {
		return walletRepository.listAll();
	}
	
}
