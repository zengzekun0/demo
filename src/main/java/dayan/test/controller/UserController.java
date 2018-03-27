package dayan.test.controller;

import dayan.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xsg
 * @date 2018-03-20 9:34
 */
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String index2() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/registered")
    public String registered() {
        return "registered";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        if (!userMapper.isUsernameValid(username)) {
            model.addAttribute("errorMsg", "用户名不存在");
            return "index";
        } else if (!userMapper.isPasswordValid(username, password)) {
            model.addAttribute("errorMsg", "密码错误");
            return "index";
        }

        model.addAttribute("username", username);
        return "home";
    }

    @RequestMapping("/register")
    public String register(String username, String password, Model model) {
            if (userMapper.isUsernameValid(username)) {
                model.addAttribute("errorMsg", "用户名已存在");
                return "registered";
        }else if(password.length()==0){
            model.addAttribute("errorMsg", "密码不能为空");
            return "registered";
        }

            userMapper.saveMsg(username,password);
            model.addAttribute("errorMsg", "注册成功,请登录");
            return "index";
    }
}