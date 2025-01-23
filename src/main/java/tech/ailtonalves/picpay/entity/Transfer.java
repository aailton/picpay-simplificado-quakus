package tech.ailtonalves.picpay.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_transactions")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payer_id")
	private Wallet payerId;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payee_id")
	private Wallet payeeId;
	
	@Column(name = "authorization_status")
	private String authorizationStatus;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@CreationTimestamp
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	public Transfer(@NotNull Wallet payer, @NotNull Wallet payee, String authorizationStatus,
			@DecimalMin("0.01") @NotNull BigDecimal amount) {
		this.payerId = payer;
		this.payeeId = payee;
		this.authorizationStatus = authorizationStatus;
		this.amount = amount;
	}
	
	public Transfer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getPayerId() {
		return payerId;
	}

	public void setPayerId(Wallet payerId) {
		this.payerId = payerId;
	}

	public Wallet getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Wallet payeeId) {
		this.payeeId = payeeId;
	}

	public String getAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(String authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
