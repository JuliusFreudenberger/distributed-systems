package de.hse.distributedsystems.distributedsystems;

import de.hse.distributedsystems.distributedsystems.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class RestAPI {

    List<Person> dataStorage = new ArrayList<>();

    @GetMapping("/hi")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping(value = "/echo/{request}")
    public String echo(@PathVariable String request) {
        return request;
    }

    @PostMapping("/api/person")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        dataStorage.add(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/person")
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok().body(dataStorage);
    }

    @GetMapping("/api/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        final Person person;
        try {
            person = dataStorage.get(id);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/api/person/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable int id) {
        try {
            dataStorage.remove(id);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/person/{id}")
    public ResponseEntity<Void> updatePersonById(@PathVariable int id, @RequestBody Person person) {
        dataStorage.set(id, person);
        return ResponseEntity.ok().build();
    }
}
