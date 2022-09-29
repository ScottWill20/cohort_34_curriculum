package learn.concepts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                   // 1
public class ConceptsController {

    @GetMapping("/")              // 2
    public String helloWorld() {
        return "Hello world.";    // 3
    }

    @PostMapping("/urlencoded")
    public void readFromBody(String name, int age, boolean likesCookies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Likes Cookies?: " + likesCookies);
    }
}
