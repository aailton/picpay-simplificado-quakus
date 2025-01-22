package tech.ailtonalves.picpay.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tech.ailtonalves.picpay.entity.Transfer;

@ApplicationScoped
public class TransferRepository implements PanacheRepository<Transfer>{

}
