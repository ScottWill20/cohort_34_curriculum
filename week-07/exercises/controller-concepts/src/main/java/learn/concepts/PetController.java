package learn.concepts;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @GetMapping
    public void findAll() {
    }

    @GetMapping("/{petId}")
    public void findById(@PathVariable int petId) {
    }

    @PostMapping
    public void create() {
    }

    @PutMapping("/{petId}")
    public void update(@PathVariable int petId) {
    }

    @DeleteMapping("/{petId}")
    public void delete(@PathVariable int petId) {
    }
}
