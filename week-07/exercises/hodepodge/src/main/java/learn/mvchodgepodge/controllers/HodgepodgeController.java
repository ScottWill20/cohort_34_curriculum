package learn.mvchodgepodge.controllers;

import learn.mvchodgepodge.models.SheepValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class HodgepodgeController {



    // endpoint = method in a controller
    @GetMapping("/")
    public String index() {
        return "Hello World.";
    }

    @GetMapping("/name")
    public String name() {
        return "Scott Williams";
    }

    @GetMapping("/current/time")
    public LocalDateTime datetime() {
        return LocalDateTime.now();
    }

    @GetMapping("/greet/{name}")
    public String greeting(@PathVariable String name) {
        return String.format("Hello, %s!", name);
    }

    private static int sheepCount = 0;

    @PutMapping("/sheep")
    public void countingSheep() {
        sheepCount++;
    }

    @GetMapping("/sheep")
    public int howManySheep() {
        return sheepCount;
    }

    @PutMapping("/sheep/{amount}")
    public void increaseSheepByAmount(@PathVariable int amount) {
        sheepCount = sheepCount + amount;
    }

    // ??????????????????????????????????????????????????????
    @PostMapping("/sheep")
    public void sheepValue(@RequestBody SheepValue sheepValue) {
        sheepCount = sheepCount + sheepValue.getAmount();
    } // ??????????????? WHY NO WORK????????????????????

    @DeleteMapping("/sheep")
    public void lostSheep() {
        sheepCount--;
    }

    private static ArrayList<String> todos = new ArrayList<>(List.of("Walk the dog", "Make dinner"));

    @GetMapping("/todo")
    public List<String> getTodos() {
        return todos;
    }

    @PutMapping("/todo")
    public void addTodos(@RequestBody List<String> items) {
        todos.addAll(items);
    }

    @PutMapping("/todo/{item}")
    public void addTodos(@PathVariable String item) {
        todos.add(item);
    }






}
