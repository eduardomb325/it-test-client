package IT.MS.Client.Repositories.Interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import IT.MS.Client.Domains.Models.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByDocument(String document);
}
