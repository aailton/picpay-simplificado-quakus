package tech.ailtonalves.picpay.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tech.ailtonalves.picpay.dto.WalletDTO;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.exception.BusinessException;
import tech.ailtonalves.picpay.repository.WalletRepository;

@ApplicationScoped
public class WalletService implements WalletServiceInterface {
	
	private static final Logger LOG = Logger.getLogger(WalletService.class);
	
	@Inject
	WalletRepository walletRepository;
	
	@Override
	public Wallet createWallet(WalletDTO walletDTO) throws BusinessException  {
		LOG.info("Solicitacao para criacao da carteira : " + walletDTO);
		
		Optional<Wallet> validation = walletRepository.findByDocumentOrEmail(walletDTO.document(), walletDTO.email());
		
		if (validation.isPresent()) {
            throw new BusinessException ("ERRO: documento e/ou email ja cadastrado(s)!");
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
