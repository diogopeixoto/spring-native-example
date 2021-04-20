package com.github.diogopeixoto.controller.empresas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.diogopeixoto.core.empresas.Empresa;
import com.github.diogopeixoto.core.empresas.EmpresasService;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {

	private EmpresasService empresas;
	private EmpresaMapper mapper;

	public EmpresasController(EmpresasService empresas, EmpresaMapper mapper) {
		super();
		this.empresas = empresas;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<EmpresaDTO> add(@RequestBody EmpresaDTO dto) {
		Empresa empresa = mapper.fromDTO(dto);
		Empresa empresaAdded = empresas.add(empresa);

		return new ResponseEntity<>(mapper.toDTO(empresaAdded), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
		empresas.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<EmpresaDTO> findByCnpj(@PathVariable("cnpj") String cnpj) {
		Empresa empresa = empresas.findByCnpj(cnpj);

		return buildFindEmpresaByAttributeResponse(empresa);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDTO> findById(@PathVariable("id") long id) {
		Empresa empresa = empresas.findById(id);

		return buildFindEmpresaByAttributeResponse(empresa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody EmpresaDTO dto) {
		dto.setId(id);

		Empresa empresa = mapper.fromDTO(dto);
		empresas.update(empresa);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<EmpresaDTO> buildFindEmpresaByAttributeResponse(Empresa empresa) {
		if (empresa != null) {
			return new ResponseEntity<>(mapper.toDTO(empresa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
