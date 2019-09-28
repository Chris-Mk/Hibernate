package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findClientByFullName(String fullName);

    @Query("select c from app.ccb.domain.entities.Client as c order by size(c.bankAccount.cards) desc")
    List<Client> findClientWithMostCards();
}
