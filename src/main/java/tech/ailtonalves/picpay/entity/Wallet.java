package tech.ailtonalves.picpay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_wallets")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name")
	@NotNull(message = "Informe o nome completo")
	private String fullName;
	
	@Column(name = "document")
	@NotNull(message = "Informe o CPF ou CNPJ")
	private String document;
	
	@Column(name = "email")
	@NotNull(message = "Informe o email")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Formato de email inválido" )
	private String email;
	
	@Column(name = "telephone")
	@NotNull(message = "Informe o número de telefone")
	private String telephone;
	
	@Column(name = "password")
	@NotNull(message = "Insira sua senha")
	private String password;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Informe o tipo de carteira: COSTUMER ou MERCHANT")
	public WalletType walletType;
	
	
	public void debit(BigDecimal value) {
		this.balance = this.balance.subtract(value);
	}
	
	
	public void credit(BigDecimal value) {
		this.balance = this.balance.add(value);
	}

	public Wallet(String fullName, String document, String email, String telephone, String password,
			BigDecimal balance, WalletType walletType) {
		super();
		this.fullName = fullName;
		this.document = document;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.balance = balance;
		this.walletType = walletType;
	}
	
	public Wallet() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setInitialBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}
	
}
