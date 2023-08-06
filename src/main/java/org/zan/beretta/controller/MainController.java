package org.zan.beretta.controller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController
@Slf4j
@AllArgsConstructor
public class MainController {
    private final Jedis jedis;
    @GetMapping("/api1")
    public String sayHelloApi1(HttpServletRequest request) {
        String key = "user:" + request.getHeader("X-Forwarded-For");
        Optional<String> value = Optional.ofNullable(jedis.get(key));
        int count = value.map(Integer::parseInt).orElse(0);
        if (count < 5) {
            count++;
            jedis.set(key, String.valueOf(count));
            jedis.pexpire(key, 10000);
            log.info("Request count for {} : {}", key, count);
            return "done";
        } else {
            log.error("Max Thread or Rate Limit Exceeded for {}", key);
            return "Rate limit exceeded. Try again later.";
        }
    }
}
