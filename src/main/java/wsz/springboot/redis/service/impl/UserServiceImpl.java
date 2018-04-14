package wsz.springboot.redis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import wsz.springboot.redis.dao.UserDao;
import wsz.springboot.redis.domain.User;
import wsz.springboot.redis.service.UserService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wsz
 * date 2018/4/14
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 也可用此方法解决key乱码问题，推荐使用配置类方法
     * @return
     */
//    @Autowired(required = false)
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        RedisSerializer stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        this.redisTemplate = redisTemplate;
//    }
    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(Long id){
        String key = "user_"+id;

        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            User user = operations.get(key);
            LOGGER.info("UserServiceImpl.findById() : 从缓存中获取了用户 >> " + user);
            return user;
        }
        User user = userDao.findById(id);
        operations.set(key, user, 60, TimeUnit.SECONDS);//60秒
        System.out.println(key);
        return user;
    }

    public Long saveUser(User user){
        return userDao.saveUser(user);
    }

    public Long updateUser(User user){
        Long ret = userDao.updateUser(user);

        String key = "user_"+user.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            redisTemplate.delete(key);
            LOGGER.info("UserServiceImpl.updateUser() : 从缓存中删除了用户 >> " + user.toString());
        }
        return ret;
    }

    public Long deleteUser(Long id){
        Long ret = userDao.deleteUser(id);
        //缓存存在，删除
        String key = "user_" + id;
        boolean hashKey = redisTemplate.hasKey(key);
        if(hashKey){
            redisTemplate.delete(key);
            LOGGER.info("UserServiceImpl.deleteUser() : 从缓存中删除了用户 >> " + id);
        }
        return ret;
    }
}
