package tech.ailtonalves.picpay.dto;

import java.math.BigDecimal;

import tech.ailtonalves.picpay.entity.Wallet;
import tech.ailtonalves.picpay.entity.WalletType;

public record WalletDTO(String fullName,
						String document, 
						String email,
						String telephone,
						String password,
						BigDecimal balance,
						WalletType walletType) {
	
	public Wallet toWallet() {
		return new Wallet(fullName, document, email, telephone, password, balance, walletType);
	}
}
