package br.com.fabrica.venda.repositories;

import br.com.fabrica.venda.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

    boolean existsByname(String name);

    boolean existsByCpf(String cpf);

    boolean existsByenderecoAndCidadeAndEstado(String endereco, String cidade, String estado);

    Optional<ClienteModel> findById(UUID id);
}
