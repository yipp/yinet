package org.yinet.dao.utils;
import java.util.List;

import org.yinet.dao.clazz.User;
public interface UserDao {
	public User selectUser(Integer id);
	public List<User> selectAll();
}
