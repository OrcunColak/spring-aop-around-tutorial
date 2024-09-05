package com.colak.springtutorial.controller;

import com.colak.springtutorial.around.redislock.RedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/resource
    @GetMapping("/resource")
    public String getResource() {
        return "Resource accessed";
    }

    // http://localhost:8080/status?card=yourCardValue&id=yourIdValue
    // The parameter 'card' was detected in the request with the value: yourCardValue
    @GetMapping(path = "status")
    @RedisLock(key = "myLockKey", timeout = 10)
    public String checkStatusCard(@RequestParam(name = "card") String card, @RequestParam String id) {
        return "status account for client id: " + id + "!";
    }
}
