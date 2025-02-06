package tech.ailtonalves.picpay.service;

import java.util.List;

import tech.ailtonalves.picpay.dto.WalletDTO;
import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.exception.BusinessException;

public interface WalletServiceInterface {
	
	public Wallet createWallet(WalletDTO walletDTO) throws BusinessException;
	public List<Wallet> getAllWallts();

}
