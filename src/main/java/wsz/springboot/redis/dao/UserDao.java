package wsz.springboot.redis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsz.springboot.redis.domain.User;

import java.util.List;

/**
 * Created by wsz
 * date 2018/4/14
 */
@Mapper
@Repository
public interface UserDao {

    List<User> findAll();

    User findById(@Param("id") Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);
}
