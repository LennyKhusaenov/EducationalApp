package com.educationportal.repository;

import com.educationportal.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;


/**
 * Created by leniz on 18.05.2016.
 */

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {


}

