package cn.sea.controller;

import cn.sea.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hello")
@Api(tags = "HelloController 控制器")
public class HelloController {

    @ApiOperation("测试方法")
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello swagger";
    }

    @ApiOperation("根据用户名查询")
    @GetMapping("/test")
    @ResponseBody
    public String test(String username) {
        return "hello swagger username = " + username;
    }

    // 只要我们的接口中的返回值中存在实体类，他就会被扫描到Swagger中
    @ApiOperation(("查询所有"))
    @GetMapping("/findAll")
    public List<User> findAll() {
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findById")
    public User findById(String id) {
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        return user;
    }

    @ApiOperation("分页查询")
    @GetMapping("/findByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "row", value = "页面大小", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页")
    })
    public List<User> findByPage(int row, int currentPage) {
        System.out.println("row = " + row + " , currentPage = " + currentPage);
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        User user1 = new User();
        user1.setUsername("xiaochen");
        user1.setPassword("789123");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        return users;
    }

    @ApiOperation("保存操作")
    @PostMapping("/save")
    public void save(@ApiParam("User对象实体") User user) {
        System.out.println("save . . ." + user);
    }

    @GetMapping("/delete/{id}")
    public String delete(@ApiParam("用户id")@PathVariable("id") String id) {
        System.out.println("delete id = " + id);
        return "delete id = " + id;
    }


}
