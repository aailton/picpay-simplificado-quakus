package tech.ailtonalves.picpay.service;

import java.util.List;

import tech.ailtonalves.picpay.dto.TransferDTO;
import tech.ailtonalves.picpay.entity.Transfer;

public interface TransferServiceInterface {
	
	public Transfer createTransfer(TransferDTO transferDTO);
	public List<Transfer> getAllTransfer();
}
