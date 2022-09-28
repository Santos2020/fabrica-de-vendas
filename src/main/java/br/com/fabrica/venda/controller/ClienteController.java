package br.com.fabrica.venda.controller;

import br.com.fabrica.venda.dtos.ClienteDto;
import br.com.fabrica.venda.models.ClienteModel;
import br.com.fabrica.venda.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vendas")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto){
       if(clienteService.existsByname(clienteDto.getName())){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Client not found!");
       }
       if(clienteService.existsByCpf(clienteDto.getCpf())){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Client not found!");
       }
       if(clienteService.existsByenderecoAndCidadeAndEstado(clienteDto.getEndereco(), clienteDto.getCidade(),clienteDto.getEstado())){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Client not found!");
       }

       ClienteModel clienteModel = new ClienteModel();
       BeanUtils.copyProperties(clienteDto, clienteModel);
       clienteModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
       return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
   }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
   @GetMapping
    public ResponseEntity<Page<ClienteModel>> getAllVendas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                              Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(pageable));
   }
   @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVendas(@PathVariable(value = "id") UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findByid(id);
        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
   }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVendas(@PathVariable(value = "id") UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findByid(id);
        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");

        }
       clienteService.delete(clienteModelOptional.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente deletado com Successufully");

       }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVendas(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if (!clienteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente alterado com Successufully.");
        }
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setId(clienteModelOptional.get().getId());
        clienteModel.setRegistrationDate(clienteModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
    }

}
