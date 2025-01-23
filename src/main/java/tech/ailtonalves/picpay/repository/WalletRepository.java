package tech.ailtonalves.picpay.repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tech.ailtonalves.picpay.entity.Wallet;

@ApplicationScoped
public class WalletRepository implements PanacheRepository<Wallet> {
	
	@Inject
	PanacheRepositoryBase<Wallet, Long> panacheRepositoryBase; 
	
	public Optional<Wallet> findByDocumentOrEmail(String document, String email) {
		return panacheRepositoryBase
				.find("document = ?1 or email = ?2", document, email)
				.firstResultOptional();
	}

}
