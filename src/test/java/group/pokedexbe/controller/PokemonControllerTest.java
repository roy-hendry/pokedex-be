package group.pokedexbe.controller;

import group.pokedexbe.dto.PokemonDTO;
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

        PokemonDTO pokemonDTO = PokemonDTO.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(false)
                .basicSprite("basicSprite")
                .detailedSprite("detailedSprite")
                .abilityName1("abilityName1")
                .abilityName2("abilityName2")
                .hp(100)
                .attack(111)
                .defence(222)
                .specialAttack(333)
                .specialDefence(444)
                .speed(5)
                .build();

        pokemonController.createPokemon(pokemonDTO);

        verify(pokemonServiceMock).createPokemon(pokemonDTO);
    }

    @Test
    void getAllPokemon() {

        pokemonController.getAllPokemon();

        verify(pokemonServiceMock).getAllPokemon();
    }

    @Test
    void getPokemonById() {

        pokemonController.getPokemonById(123L);

        verify(pokemonServiceMock).getPokemonById(123L);
    }

    @Test
    void updatePokemon() {

        PokemonDTO pokemonDTO = PokemonDTO.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(false)
                .basicSprite("basicSprite")
                .detailedSprite("detailedSprite")
                .abilityName1("abilityName1")
                .abilityName2("abilityName2")
                .hp(100)
                .attack(111)
                .defence(222)
                .specialAttack(333)
                .specialDefence(444)
                .speed(5)
                .build();

        pokemonController.updatePokemon(pokemonDTO, 123L);

        verify(pokemonServiceMock).updatePokemon(pokemonDTO, 123L);
    }

    @Test
    void deletePokemon() {

        pokemonController.deletePokemon(123L);

        verify(pokemonServiceMock).deletePokemonById(123L);
    }

//    @Test
//    void toggleIsCapturedState() {
//
//        pokemonController.toggleIsCapturedState(123L);
//
//        verify(pokemonServiceMock).toggleIsCapturedState(123L);
//    }
}