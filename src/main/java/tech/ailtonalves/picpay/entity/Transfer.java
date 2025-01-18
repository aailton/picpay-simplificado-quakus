package tech.ailtonalves.picpay.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payer_id")
	private Wallet payer;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payee_id")
	private Wallet payee;
	
	@Column(name = "authorization_status")
	private String authorizationStatus;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;

}
