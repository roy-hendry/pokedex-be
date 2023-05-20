package group.pokedexbe.controller;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // Request POST mapping to create an entry to the DB
    @PostMapping
    // Creates the (C of our CRUD methods) which takes in the DTO model - takes the input as JSON
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        // Sends the response back to the REST API to say it has been created
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }
}
