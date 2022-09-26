package br.com.fabrica.venda.services;

import br.com.fabrica.venda.models.ClienteModel;
import br.com.fabrica.venda.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

   @Autowired
    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
  @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public boolean existsByname(String name) {
        return clienteRepository.existsByname(name);
    }

    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    public Page<ClienteModel> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Optional<ClienteModel> findByid(UUID id) {
        return clienteRepository.findById(id);
    }
  @Transactional
    public void delete(ClienteModel clienteModel) {
         clienteRepository.delete(clienteModel);
    }

    public boolean existsByenderecoAndCidadeAndEstado(String endereco, String cidade, String estado) {
        return clienteRepository.existsByenderecoAndCidadeAndEstado(endereco, cidade, estado);
    }

    public Optional<ClienteModel> findById(UUID id) {
        return clienteRepository.findById(id);
    }
}
