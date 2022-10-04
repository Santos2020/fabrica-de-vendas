package br.com.fabrica.venda.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteDto {

   @NotBlank
   @Size(max = 55)
    private String name;
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 255)
    private String endereco;
    @NotBlank
    @Size(max = 55)
    private String  cidade;
    @NotBlank
    @Size(max = 255)
    private String  estado;
    @NotBlank
     @Size(max = 55)
    private String  email;

    @NotBlank
    @Size(max = 11)
     private String  telefone;
    @NotBlank
    @Size(max = 11)
    private String  sexo;

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getCpf() {
  return cpf;
 }

 public void setCpf(String cpf) {
  this.cpf = cpf;
 }

 public String getEndereco() {
  return endereco;
 }

 public void setEndereco(String endereco) {
  this.endereco = endereco;
 }

 public String getCidade() {
  return cidade;
 }

 public void setCidade(String cidade) {
  this.cidade = cidade;
 }

 public String getEstado() {
  return estado;
 }

 public void setEstado(String estado) {
  this.estado = estado;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getTelefone() {
  return telefone;
 }

 public void setTelefone(String telefone) {
  this.telefone = telefone;
 }

 public String getSexo() {
  return sexo;
 }

 public void setSexo(String sexo) {
  this.sexo = sexo;
 }
}
