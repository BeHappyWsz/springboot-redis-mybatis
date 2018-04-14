package wsz.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wsz.springboot.redis.domain.User;
import wsz.springboot.redis.service.UserService;

import java.util.List;

/**
 * Created by wsz
 * date 2018/4/14
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * http://localhost:8080/user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * http://localhost:8080/user/3
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id")Long id){
        return userService.findById(id);
    }

    /**
     * 新增User
     * http://localhost:8080/user?username=red&password=redis&age=23
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Long saveUser(User user){
        return userService.saveUser(user);
    }

    /**
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Long updateUser(User user){
        return userService.updateUser(user);
    }

    /**
     * http://localhost:8080/user/1
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Long deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
