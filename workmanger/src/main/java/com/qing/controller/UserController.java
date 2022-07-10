package com.qing.controller;


import com.qing.Dao.UserMapper;
import com.qing.pojo.TransactionRecord;
import com.qing.pojo.User;
import com.qing.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller

public class UserController {


    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    public UserController(UserService UserService) {
        this.userService=UserService;
    }

   @GetMapping("/list")

   @ResponseBody
    public User queryUserList(){

       User user= userService.queryUserByName("root");

        return user;
    }
    @GetMapping("/home")
    public String index(Model mode) {


        return "index";
    }
    @GetMapping("/trans")
    public String trans(Model mode) {


        mode.addAttribute("me", Arrays.asList("666666","2021-12-30","1000","1000"));
        mode.addAttribute("me1", Arrays.asList("666666","2021-12-30","-50","950"));
        return "index";
    }
    @GetMapping("/cun")
    public String cun(Model mode) {
        mode.addAttribute("me", Arrays.asList("666666","2021-12-30","1000","1000"));
        return "cu";
    }
    @GetMapping("/To")
    public String qu(Model mode) {
           User user=userMapper.queryUserById(111);
        List<TransactionRecord> trans = user.getTrans();
        System.out.println(trans);
        mode.addAttribute("me",trans);
        mode.addAttribute("me1", Arrays.asList("666666","2021-12-30","-50","950"));
        return "cun";
    }
    @GetMapping("/toLogin")
    public String toLogin(Model mode) {
        mode.addAttribute("me","themleaf");
        return "login";
    }
    @GetMapping("/login")
    public String login(String username ,String password,Model model) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken Token = new UsernamePasswordToken(username,password);
        try {
            subject.login(Token);
            return "index";
        } catch (UnknownAccountException e) {       //用户名错误
            e.printStackTrace();
            model.addAttribute("message","用户名错误！");
            return "login";
        }catch (IncorrectCredentialsException e){   //密码错误
            e.printStackTrace();
        }
        model.addAttribute("message","密码错误！");
        return "login";
    }

    @GetMapping("/unathor")
    @ResponseBody
    public String unathor(){
        return "没有权限";
    }

    @GetMapping("/user/add")
    public String add(Model mode) {

        return "in";
    }
    @GetMapping("user/update")
    public String update(Model mode) {
        mode.addAttribute("update","uuuuuuu");
        return "user/update";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/toLogin";
    }
}
