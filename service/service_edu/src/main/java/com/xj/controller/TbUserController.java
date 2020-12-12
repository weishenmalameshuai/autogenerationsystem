package com.xj.controller;


import com.xj.commonutils.ReponseCode;
import com.xj.entity.TbUser;
import com.xj.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xj
 * @since 2020-12-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/baseLogin/user")
//解决跨域
@CrossOrigin(allowCredentials = "true")
public class TbUserController {

    @Resource
    private TbUserService tbUserService;

    //查询用户列表数据
    //rest风格
    @ApiOperation(value = "用户列表数据")
    @GetMapping("search")
    public ReponseCode doSearch() {

        List<TbUser> users = tbUserService.list(null);

        return ReponseCode.ok().data("users", users);
    }

    //查询用户数据
    //rest风格
    @ApiOperation(value = "查询指定用户数据")
    @GetMapping("select/{id}")
    public ReponseCode doSelect(@ApiParam(name = "id", value = "用户id", required = true)
                                @PathVariable Long id) {

        TbUser user = tbUserService.getById(id);

        return ReponseCode.ok().data("user", user);
    }

    //增加用户数据
    //rest风格
    @ApiOperation(value = "增加用户数据")
    @PostMapping("insert")
    public ReponseCode doInsert(@ApiParam(name = "用户数据", value = "用户数据", required = true)
                                @RequestBody TbUser tbUser) {

        if (tbUserService.save(tbUser)) {
            return ReponseCode.ok().data("user", tbUser);
        } else {
            return ReponseCode.error().data("user", tbUser);
        }
    }

    //删除用户数据
    //rest风格
    @ApiOperation(value = "删除用户数据")
    @DeleteMapping("delete/{id}")
    public ReponseCode doDelete(@ApiParam(name = "id", value = "用户id", required = true)
                                @PathVariable Long id) {

        if (tbUserService.removeById(id)) {
            return ReponseCode.ok();
        }
        else {
            return ReponseCode.error();
        }

    }

    //修改用户数据
    //rest风格
    @ApiOperation(value = "修改用户数据")
    @PostMapping("update")
    public ReponseCode doDelete(@ApiParam(name = "用户数据", value = "用户数据", required = true)
                                @RequestBody TbUser tbUser) {

        if (tbUserService.updateById(tbUser)) {
            return ReponseCode.ok().data("user", tbUser);
        } else {
            return ReponseCode.error().data("user", tbUser);
        }
    }

    /** 登录login **/
    @PostMapping("login")
    public ReponseCode login() {
        return ReponseCode.ok().data("token", "admin");
    }

    /** 返回登录信息info **/
    @GetMapping("info")
    public ReponseCode info() {

        Map<String, Object> data = new HashMap<>();
        data.put("roles", "[admin]");
        data.put("name", "admin");

        return ReponseCode.ok().data(data);
    }

}

