package group.pokedexbe.controller;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Tells Spring this is a REST controller
@RestController
// Mapping for the REST endpoint
@RequestMapping("/api/pokemon")
public class PokemonController {
    // DI for the Service class
    private PokemonService pokemonService;

    // DI constructor for the Service class
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Tells Spring this is a POST Request to create an entry to the DB
    @PostMapping
    // Creates the (C of our CRUD methods) which takes in the DTO model - takes the input as JSON
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        // Sends the response back to the REST API to say it has been created
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    // Tells Spring this is a GET request
    @GetMapping
    // Gets all the pokemon (R of our CRUD methods)
    public List<PokemonDTO> getAllPokemon() {
        // Uses the method in the service to return get all
        return pokemonService.getAllPokemon();
    }
}
