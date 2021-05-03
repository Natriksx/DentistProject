package com.cgi.dentistapp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.mapper.DentistVisitMapper;
import com.cgi.dentistapp.repository.DentistVisitRepository;

@Service
@Transactional
public class DentistVisitService {

	@Autowired
	private DentistVisitRepository repository;

	@Autowired
	private DentistVisitMapper mapper;

	public DentistVisitDTO findById(Long id) {
		return mapper.mapToDTO(repository.findOne(id));
	}

	//------------------------------------------------------------------CREATE
	public void addVisit(DentistVisitDTO dentistVisitDTO) {
		repository.save(mapper.mapToEntity(dentistVisitDTO));
	}

	//------------------------------------------------------------------DELETE
	public void deleteVisit(Long id) {
		repository.delete(id);
	}

	//------------------------------------------------------------------RETRIEVE
	public List<DentistVisitDTO> listAppointments() {
		Iterable<DentistVisitEntity> iterable = repository.findAll();
		Stream<DentistVisitEntity> stream = getIterableToStream(iterable);
		return stream
					   .map(object -> mapper
											  .mapToDTO(object))
					   .collect(Collectors.toList());
	}

	public static <T> Stream<T> getIterableToStream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	//------------------------------------------------------------------UPDATE

	public void updateTime(DentistVisitDTO dentistVisitDTO) {
		final DentistVisitEntity entity = repository.findOne(dentistVisitDTO.getId());
		entity.setVisitTime(dentistVisitDTO.getVisitTime());
		entity.setVisitDate(dentistVisitDTO.getVisitDate());
		repository.save(entity);
	}
}
