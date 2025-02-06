package tech.ailtonalves.picpay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import tech.ailtonalves.picpay.entity.Transfer;
import tech.ailtonalves.picpay.entity.Wallet;

public record TransferDTO(@DecimalMin("0.01")
						  @NotNull BigDecimal value,
						  @NotNull Long payer,
						  @NotNull Long payee) {
	
	public Transfer toTransfer(String authorizationStatus, Wallet payee, Wallet payer) {
		return new Transfer(payer, payee, authorizationStatus, value);
	}
}
