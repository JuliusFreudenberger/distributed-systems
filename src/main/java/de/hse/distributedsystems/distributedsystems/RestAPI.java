package de.hse.distributedsystems.distributedsystems;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class RestAPI {

    @GetMapping("/hi")
    public String sayHello() {
        return "Hello World!";
    }
}
