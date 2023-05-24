package group.pokedexbe.service;

import group.pokedexbe.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    // Creation method
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    List<PokemonDTO> getAllPokemon();
}
