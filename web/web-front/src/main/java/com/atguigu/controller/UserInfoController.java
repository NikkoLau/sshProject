package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.utils.MD5;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Reference
    private UserInfoService userInfoService;

    private String code = "8888";
    @RequestMapping("/sendCode/{num}")
    public Result sendCode(@PathVariable("num")String num, HttpSession httpSession){
        if (num!=null){
            UserInfo user = userInfoService.getByPhone(num);
            if (user ==null){
                httpSession.setAttribute("code",code);
                return Result.ok(code);
            }

        }
        return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
    }

    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        String nickName = registerVo.getNickName();
        String code = registerVo.getCode();
        String password = registerVo.getPassword();
        String phone = registerVo.getPhone();
        if (nickName !=null && code !=null &&password !=null &&phone !=null ){
            //获取手机号码是否注册过
            UserInfo user = userInfoService.getByPhone(phone);
            if (user !=null){
//                已注册
                return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
            }
            if (code !=this.code){
                return Result.build(null,ResultCodeEnum.CODE_ERROR);
            }

            //注册
            UserInfo userInfo = new UserInfo();
            userInfo.setNickName(nickName);
            userInfo.setPassword(MD5.encrypt(password));
            userInfo.setPhone(phone);
            userInfo.setStatus(1);
            userInfoService.insert(userInfo);
        }
        return Result.ok();
    }

    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo,HttpSession session){
        String password = loginVo.getPassword();
        String phone = loginVo.getPhone();
        if (!(password!=null && phone!=null)){
            return Result.build(null,ResultCodeEnum.DATA_ERROR);
        }
        UserInfo user = userInfoService.getByPhone(phone);
        if (user == null){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!(user.getPassword().equals(MD5.encrypt(password)))){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        if (user.getStatus() == 2){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        session.setAttribute("user",user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickName",user.getNickName());
        map.put("phone",phone);
        return Result.ok(map);

    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("user");
        return Result.ok();
    }
}
