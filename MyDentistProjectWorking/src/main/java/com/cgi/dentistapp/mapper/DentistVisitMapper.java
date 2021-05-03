package com.cgi.dentistapp.mapper;

import org.springframework.stereotype.Component;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;

@Component
public class DentistVisitMapper implements DTOMapper<DentistVisitEntity, DentistVisitDTO> {

	@Override
	public DentistVisitDTO mapToDTO(DentistVisitEntity entity) {
		DentistVisitDTO dto = new DentistVisitDTO();
		dto.setDentistName(entity.getDentistName());
		dto.setVisitDate(entity.getVisitDate());
		dto.setVisitTime(entity.getVisitTime());
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public DentistVisitEntity mapToEntity(DentistVisitDTO dto) {
		DentistVisitEntity entity = new DentistVisitEntity();
		entity.setDentistName(dto.getDentistName());
		entity.setVisitDate(dto.getVisitDate());
		entity.setVisitTime(dto.getVisitTime());
		entity.setId(dto.getId());
		return entity;
	}
}
