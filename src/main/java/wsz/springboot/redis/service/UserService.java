package wsz.springboot.redis.service;

import org.springframework.data.repository.query.Param;
import wsz.springboot.redis.domain.User;

import java.util.List;

/**
 * Created by wsz
 * date 2018/4/14
 */
public interface UserService {

    List<User> findAll();

    User findById(Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);
}
