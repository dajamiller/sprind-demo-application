package com.example.springDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

    private final ArtistService service;
    public SpringDemoApplication(ArtistService service) {
        this.service = service; // Spring injects this
    }
    //private ArtistService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot!";
    }

    // -------------------------
    // 1️⃣ Simple GET endpoints
    // -------------------------

    @GetMapping("/hello")
    public String basicHello() {
        return "Hello again!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hi " + name + "!";
    }

    @GetMapping("/greet")
    public String greetWithQuery(@RequestParam(defaultValue = "world") String name) {
        return "Greetings, " + name + "!";
    }


    // -----------------------------------------
    // 2️⃣ Return JSON object (DTO-style in-file)
    // -----------------------------------------

    static class Person {
        public int id;
        public String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @GetMapping("/person")
    public Person getPerson() {
        return new Person(1, "Remsey");
    }


    // ------------------------------------------------
    // 3️⃣ Return list of objects as JSON (mini-database)
    // ------------------------------------------------

    @GetMapping("/people")
    public java.util.List<Person> getPeople() {
        return java.util.List.of(
                new Person(1, "Remsey"),
                new Person(2, "Sarah"),
                new Person(3, "Bilal")
        );
    }


    // ---------------------------------------------------------
    // 4️⃣ POST endpoint — receiving JSON and returning JSON back
    // ---------------------------------------------------------

    static class NameRequest {
        public String name; // MUST be public for Spring to map it
    }

    @PostMapping("/hello-json")
    public Person createPerson(@RequestBody NameRequest body) {
        return new Person(99, body.name);
    }


    // ------------------------------------------------------------------
    // 5️⃣ PUT endpoint — updating something (fake update example)
    // ------------------------------------------------------------------

    @PutMapping("/person/{id}")
    public Person updatePerson(
            @PathVariable int id,
            @RequestBody NameRequest body) {
        return new Person(id, body.name + " (updated)");
    }


    // ----------------------------------------------------
    // 6️⃣ DELETE endpoint — simple ID-based delete example
    // ----------------------------------------------------

    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable int id) {
        return "Person with ID " + id + " deleted (fake).";
    }

    //------------------------------------------------------
    // 7 - RETURN favorite artist - GET endpoint
    //------------------------------------------------------

    @GetMapping("/MyFavoriteArtist")
    public String MyFavoriteArtist() {
        return "SZA";
    }



    @GetMapping("/artist")
    public Artist getFavoriteArtist(){
        return service.getFavoriteArtist();
    }

}
