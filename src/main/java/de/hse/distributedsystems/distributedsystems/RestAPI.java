package de.hse.distributedsystems.distributedsystems;

import de.hse.distributedsystems.distributedsystems.database.PersonRepository;
import de.hse.distributedsystems.distributedsystems.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RestController
public class RestAPI {

    private final PersonRepository personRepository;

    public RestAPI(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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
        personRepository.save(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/person")
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok().body(StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/api/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        final Optional<Person> personOptional;
        personOptional = personRepository.findById(id);
        if (personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personOptional.get());
    }

    @DeleteMapping("/api/person/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/person/{id}")
    public ResponseEntity<Void> updatePersonById(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        personRepository.save(person);
        return ResponseEntity.ok().build();
    }
}
