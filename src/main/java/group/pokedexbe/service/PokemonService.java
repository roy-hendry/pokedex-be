package group.pokedexbe.service;

import group.pokedexbe.dto.PokemonDTO;

public interface PokemonService {
    // Creation method
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
}
