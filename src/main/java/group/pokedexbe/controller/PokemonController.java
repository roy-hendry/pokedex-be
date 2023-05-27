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

    // Tells Spring this is a GET request
    @GetMapping("/{id}")
    // Gets a single pokemon by their id (R of our CRUD methods)
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable(name = "id")long id) {
        // Return status ok message along with the pokemonDTO
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    //Tells Spring this is a PUT request
    @PutMapping("/{id}")
    // @RequestBody is the new input to update the database - it takes input as JSON
    // gets the pokemon to update passing in the url bound to the @path variable as id
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody PokemonDTO pokemonDTO, @PathVariable(name = "id")long id) {
        // Updates the DB with the new values
        PokemonDTO pokemonResponse = pokemonService.updatePokemon(pokemonDTO, id);

        // Returns status ok message along with the model values that have been updated
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }
}
