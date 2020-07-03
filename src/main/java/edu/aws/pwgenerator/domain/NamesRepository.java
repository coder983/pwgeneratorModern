package edu.aws.pwgenerator.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamesRepository extends CrudRepository<Name, Long> {

}
