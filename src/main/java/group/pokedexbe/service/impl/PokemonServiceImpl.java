package group.pokedexbe.service.impl;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.exceptions.ResourceNotFoundException;
import group.pokedexbe.model.Pokemon;
import group.pokedexbe.repository.PokemonRepository;
import group.pokedexbe.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    // The DI for the repo of the model we are using is PokemonRepo
    private PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        // Convert the DTO (from client) to entity (model) this will contain all the fields from the model like
        // title etc excluding the id by calling the map to model method
        Pokemon pokemon = mapToModel(pokemonDTO);

        // Saves the object/entity (in this case the pokemon model) to the DB
        Pokemon newPokemon = pokemonRepository.save(pokemon);

        // Converts model from DB to DTO for the response back to client by calling private method mapToDTO
        PokemonDTO pokemonResponse = mapToDTO(newPokemon);

        return pokemonResponse;
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        // Finds all the pokemon and puts them in a list
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        // Uses the stream map to get all other pokemon and pass them to the list
        return pokemonList
                .stream()
                .map(pokemon -> mapToDTO(pokemon))
                .collect(Collectors.toList());
    }

    @Override
    public PokemonDTO getPokemonById(Long id) {
        // Finds pokemon by its id if there is no pokemon with that id then throw the status code for the not found
        // custom exception we made
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon", "id", id));

        return mapToDTO(pokemon);
    }

    @Override
    public void deletePokemonById(long id) {
        // Get pokemon by id from the database - the same code as get id, so that we can get the pokemon by its id
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon", "id", id));

        // Deletes the pokemon by its id
        pokemonRepository.delete(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, long id) {
        // Get pokemon by id from the database - the same code as get id, so that we can get the pokemon by its id
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokémon", "id", id));

        // Now we have the object we can update its values
        pokemon.setName(pokemonDTO.getName());
        pokemon.setBasicSprite(pokemonDTO.getBasicSprite());
        pokemon.setDetailedSprite(pokemonDTO.getDetailedSprite());
        pokemon.setAbilityName1(pokemonDTO.getAbilityName1());
        pokemon.setAbilityName2(pokemonDTO.getAbilityName2());
        pokemon.setHp(pokemonDTO.getHp());
        pokemon.setAttack(pokemonDTO.getAttack());
        pokemon.setDefence(pokemonDTO.getDefence());
        pokemon.setSpecialAttack(pokemonDTO.getSpecialAttack());
        pokemon.setSpecialDefence(pokemonDTO.getSpecialDefence());
        pokemon.setSpeed(pokemonDTO.getSpeed());

        // Saving new values to the database
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        // Returns the new values as a response DTO for the client
        return mapToDTO(updatedPokemon);
    }

    // Converts the DTO (from client) to entity (model) this contains all the fields from the model e.g. first name etc
    private Pokemon mapToModel(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDTO.getId());
        pokemon.setName(pokemonDTO.getName());
        pokemon.setBasicSprite(pokemonDTO.getBasicSprite());
        pokemon.setDetailedSprite(pokemonDTO.getDetailedSprite());
        pokemon.setAbilityName1(pokemonDTO.getAbilityName1());
        pokemon.setAbilityName2(pokemonDTO.getAbilityName2());
        pokemon.setHp(pokemonDTO.getHp());
        pokemon.setAttack(pokemonDTO.getAttack());
        pokemon.setDefence(pokemonDTO.getDefence());
        pokemon.setSpecialAttack(pokemonDTO.getSpecialAttack());
        pokemon.setSpecialDefence(pokemonDTO.getSpecialDefence());
        pokemon.setSpeed(pokemonDTO.getSpeed());

        return pokemon;
    }

    // Converts model from DB to DTO for the response back to client
    // Create method id is set here as it is returned from the DB
    private PokemonDTO mapToDTO(Pokemon pokemon) {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setBasicSprite(pokemon.getBasicSprite());
        pokemonDTO.setDetailedSprite(pokemon.getDetailedSprite());
        pokemonDTO.setAbilityName1(pokemon.getAbilityName1());
        pokemonDTO.setAbilityName2(pokemon.getAbilityName2());
        pokemonDTO.setHp(pokemon.getHp());
        pokemonDTO.setAttack(pokemon.getAttack());
        pokemonDTO.setDefence(pokemon.getDefence());
        pokemonDTO.setSpecialAttack(pokemon.getSpecialAttack());
        pokemonDTO.setSpecialDefence(pokemon.getSpecialDefence());
        pokemonDTO.setSpeed(pokemon.getSpeed());

        return pokemonDTO;
    }
}
