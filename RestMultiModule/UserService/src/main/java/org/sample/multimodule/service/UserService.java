package org.sample.multimodule.service;
import java.util.List;

import org.Model.*;
import org.sample.multimodule.model.User;

/**
 * Hello world!
 *
 */
public interface UserService
{
   public List<User> allUsers();
   public User createUser(User user);
   public User getUserById(Long Id);
   public String deleteUser(Long Id);
   public User updateUser(Long Id,String name);
}
