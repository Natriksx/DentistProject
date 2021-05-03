package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistVisitRepository extends CrudRepository<DentistVisitEntity, Long> {
}
