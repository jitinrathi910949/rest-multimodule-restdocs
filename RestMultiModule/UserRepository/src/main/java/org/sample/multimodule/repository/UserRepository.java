package org.sample.multimodule.repository;

import javax.transaction.Transactional;

import org.sample.multimodule.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Hello world!
 *
 */
@Repository

public interface UserRepository  extends CrudRepository<User, Long>
{
   
}
