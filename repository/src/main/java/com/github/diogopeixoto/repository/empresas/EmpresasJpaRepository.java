package com.github.diogopeixoto.repository.empresas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresasJpaRepository extends JpaRepository<EmpresaEntity, Long> {

	EmpresaEntity findByCnpj(String cnpj);
}
