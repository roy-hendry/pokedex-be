package group.pokedexbe.service;

import group.pokedexbe.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    // Creation method
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    List<PokemonDTO> getAllPokemon();

    PokemonDTO getPokemonById(Long id);

    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, long id);

    void deletePokemonById(long id);

    PokemonDTO toggleIsCapturedState(PokemonDTO pokemonDTO);
}
