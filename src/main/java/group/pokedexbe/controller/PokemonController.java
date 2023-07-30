package group.pokedexbe.controller;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Used to enable CrossOrigin so that we can access all the methods through this controller
// if we don't have this enabled we can't call our API from our frontend
@CrossOrigin
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
        return new ResponseEntity<>(pokemonService.getPokemonById(id), HttpStatus.OK);
    }

    // Tells Spring this is a PUT request
    @PutMapping("/{id}")
    // @RequestBody is the new input to update the database - it takes input as JSON
    // Gets the pokemon to update by passing in the id via the @PathVariable
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody PokemonDTO pokemonDTO, @PathVariable(name = "id")long id) {
        // Updates the DB with the new values
        PokemonDTO pokemonResponse = pokemonService.updatePokemon(pokemonDTO, id);

        // Returns status ok message along with the model values that have been updated
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }

    // Tells Spring this is a DELETE request
    @DeleteMapping("/{id}")
    // Gets the pokemon to delete by passing in the id via the @PathVariable
    public ResponseEntity<String> deletePokemon(@PathVariable(name = "id")long id) {
        // Passes the id of the pokemon we want to delete
        pokemonService.deletePokemonById(id);

        // Returns status to say that we have successfully deleted
        return new ResponseEntity<>("Pokemon has been deleted successfully", HttpStatus.OK);
    }
}
