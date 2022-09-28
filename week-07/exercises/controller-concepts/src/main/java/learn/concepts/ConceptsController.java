package learn.concepts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                   // 1
public class ConceptsController {

    @GetMapping("/")              // 2
    public String helloWorld() {
        return "Hello world.";    // 3
    }
}
