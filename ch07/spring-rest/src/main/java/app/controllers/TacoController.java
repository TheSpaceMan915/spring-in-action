package app.controllers;

import app.entities.Taco;
import app.repositories.TacoRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos",
                produces="application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {

    private final TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params="recent")
    public Iterable<Taco> getRecentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0, 2, Sort.by("createdAt").descending());
        return tacoRepo.findAll(pageRequest).getContent();
    }

//    @GetMapping(path="/{id}")
//    public Optional<Taco> getTacoById(@PathVariable("id") Long id) {
//        return tacoRepo.findById(id);
//    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}