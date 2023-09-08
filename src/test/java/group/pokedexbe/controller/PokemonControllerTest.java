package group.pokedexbe.controller;

import group.pokedexbe.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PokemonControllerTest {

    PokemonService pokemonServiceMock;
    private PokemonController pokemonController;

    @BeforeEach
    void setUp() {
        pokemonServiceMock = mock(PokemonService.class);

        pokemonController = new PokemonController(pokemonServiceMock);
    }

    @Test
    void createPokemon() {
    }

    @Test
    void getAllPokemon() {
        pokemonController.getAllPokemon();

        verify(pokemonServiceMock).getAllPokemon();
    }

    @Test
    void getPokemonById() {
    }

    @Test
    void updatePokemon() {
    }

    @Test
    void deletePokemon() {
    }

    @Test
    void toggleIsCapturedState() {
    }
}