package com.qjx.sample.controller;

import com.qjx.sample.metrics.PerformanceMetrics;
import com.qjx.sample.service.UserService;
import com.qjx.sample.test.ValueTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
@RestController
public class UserController {

    private UserService userService;

    private ValueTest valueTest;

    public UserController(UserService userService, ValueTest valueTest) {
        this.userService = userService;
        this.valueTest = valueTest;
    }

    @GetMapping("/query")
    public String query(Integer id) {
        return userService.query(id);
    }


    @GetMapping("/query2")
    public String query2(Integer id) {
        return valueTest.printValue();
    }
}
