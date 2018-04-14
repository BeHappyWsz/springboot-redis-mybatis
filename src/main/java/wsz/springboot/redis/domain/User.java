package wsz.springboot.redis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by wsz
 * date 2018/4/14
 */
public class User implements Serializable{

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private Integer age;

    public String toString(){
        return "User: id-"+id;
    }
}
