package com.cgi.dentistapp.mapper;

public interface DTOMapper<E, D> {
	D mapToDTO(E entity);
	E mapToEntity(D dto);
}
