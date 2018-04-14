package wsz.springboot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 解决set key乱码问题
 * 调整序列化方式
 * Created by wsz
 * date 2018/4/14
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate redisTemplateInit(){
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
