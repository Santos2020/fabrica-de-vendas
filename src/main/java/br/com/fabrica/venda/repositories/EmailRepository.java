package br.com.fabrica.venda.repositories;

import br.com.fabrica.venda.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
