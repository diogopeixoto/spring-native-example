package com.github.diogopeixoto.controller.empresas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.diogopeixoto.controller.exception.IllegalArgumentResponseEntityExceptionHandler;
import com.github.diogopeixoto.core.empresas.Empresa;
import com.github.diogopeixoto.core.empresas.EmpresasService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmpresasController.class)
@ContextConfiguration(classes = { EmpresasController.class, IllegalArgumentResponseEntityExceptionHandler.class })
class EmpresasControllerTest {

	private final String CNPJ_VALID = "04.470.781/0001-39";
	private final String ENDPOINT = "/empresas";

	@MockBean
	private EmpresasService empresas;

	@MockBean
	private EmpresaMapper mapper;

	@Autowired
	private MockMvc mvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void add_ShouldReturnCreateCode() throws Exception {
		Empresa empresa = buildEmpresa(0, CNPJ_VALID);
		EmpresaDTO empresaDTO = buildEmpresaDTO(0, CNPJ_VALID);

		String json = objectMapper.writeValueAsString(empresaDTO);

		when(mapper.fromDTO(any(EmpresaDTO.class))).thenReturn(empresa);
		when(empresas.add(empresa)).thenReturn(empresa);
		when(mapper.toDTO(empresa)).thenReturn(empresaDTO);
		mvc.perform(MockMvcRequestBuilders.post(ENDPOINT).content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn()
				.equals(json);

		verify(mapper, times(1)).fromDTO(any(EmpresaDTO.class));
		verify(empresas, only()).add(empresa);
		verify(mapper, times(1)).toDTO(empresa);
	}

	@Test
	void add_ShouldReturnBadRequestCode_CnpjInvalid() throws Exception {
		String json = objectMapper.writeValueAsString(buildEmpresaDTO(0, CNPJ_VALID));

		doThrow(IllegalArgumentException.class).when(mapper).fromDTO(any(EmpresaDTO.class));
		mvc.perform(MockMvcRequestBuilders.post(ENDPOINT).content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

		verify(mapper, only()).fromDTO(any(EmpresaDTO.class));
		verify(empresas, never()).add(any());
	}

	@Test
	void deleteById_ShouldReturnNoContentCode() throws Exception {
		long id = 1;

		doNothing().when(empresas).deleteById(id);

		mvc.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());

		verify(empresas, only()).deleteById(id);
	}

	@Test
	void findByCnpj_ShouldReturnNotFoundCode() throws Exception {
		String cnpj = "0000000";

		when(empresas.findByCnpj(cnpj)).thenReturn(null);

		mvc.perform(MockMvcRequestBuilders.get(ENDPOINT + "/cnpj/" + cnpj).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(empresas, only()).findByCnpj(cnpj);
	}

	@Test
	void findByCnpj_ShouldReturnOkCodeAndEmpresa() throws Exception {
		String cnpj = "0000000";
		Empresa empresa = buildEmpresa(0, CNPJ_VALID);
		EmpresaDTO empresaDTO = buildEmpresaDTO(0, CNPJ_VALID);

		when(empresas.findByCnpj(cnpj)).thenReturn(empresa);
		when(mapper.toDTO(empresa)).thenReturn(empresaDTO);

		String json = objectMapper.writeValueAsString(empresaDTO);
		mvc.perform(MockMvcRequestBuilders.get(ENDPOINT + "/cnpj/" + cnpj).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().equals(json);

		verify(empresas, only()).findByCnpj(cnpj);
		verify(mapper, only()).toDTO(empresa);
	}

	@Test
	void findById_ShouldReturnNotFoundCode() throws Exception {
		long id = 1;

		when(empresas.findById(id)).thenReturn(null);

		mvc.perform(MockMvcRequestBuilders.get(ENDPOINT + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(empresas, only()).findById(id);
	}

	@Test
	void findById_ShouldReturnOkCodeAndEmpresa() throws Exception {
		long id = 1;

		Empresa empresa = buildEmpresa(id, CNPJ_VALID);
		EmpresaDTO empresaDTO = buildEmpresaDTO(id, CNPJ_VALID);

		when(empresas.findById(id)).thenReturn(empresa);
		when(mapper.toDTO(empresa)).thenReturn(empresaDTO);

		String json = objectMapper.writeValueAsString(empresaDTO);
		mvc.perform(MockMvcRequestBuilders.get(ENDPOINT + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().equals(json);

		verify(empresas, only()).findById(id);
		verify(mapper, only()).toDTO(empresa);
	}

	@Test
	void update_ShouldReturnNoContentCode() throws Exception {
		long id = 1;

		Empresa empresa = buildEmpresa(id, CNPJ_VALID);

		EmpresaDTO empresaDTO = buildEmpresaDTO(id, CNPJ_VALID);

		String json = objectMapper.writeValueAsString(empresaDTO);

		when(mapper.fromDTO(any(EmpresaDTO.class))).thenReturn(empresa);
		when(empresas.update(empresa)).thenReturn(empresa);
		mvc.perform(MockMvcRequestBuilders.put(ENDPOINT + "/" + id).content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		verify(mapper, times(1)).fromDTO(any(EmpresaDTO.class));
		verify(empresas, only()).update(empresa);
	}

	@Test
	void update_ShouldReturnBadRequestCode_CnpjInvalid() throws Exception {
		long id = 1;
		String json = objectMapper.writeValueAsString(buildEmpresaDTO(id, CNPJ_VALID));

		doThrow(IllegalArgumentException.class).when(mapper).fromDTO(any(EmpresaDTO.class));
		mvc.perform(MockMvcRequestBuilders.put(ENDPOINT + "/" + id).content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		verify(mapper, only()).fromDTO(any(EmpresaDTO.class));
		verify(empresas, never()).update(any());
	}

	private EmpresaDTO buildEmpresaDTO(long id, String cnpj) {
		EmpresaDTO dto = new EmpresaDTO(id, cnpj, "Nome Fantasia", "Razão Social");

		return dto;
	}

	private Empresa buildEmpresa(long id, String cnpj) {
		return new Empresa(id, cnpj, "Nome Fantasia", "Razão Social");
	}
}
