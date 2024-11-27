package br.com.hexburger.cliente.framework.repository;

import br.com.hexburger.cliente.framework.entidade.ECliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ECliente, String> {
}
