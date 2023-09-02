package group.pokedexbe.service.impl;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.exceptions.ResourceNotFoundException;
import group.pokedexbe.model.Pokemon;
import group.pokedexbe.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PokemonServiceImplTest {

    private PokemonServiceImpl pokemonServiceImpl;

    private PokemonRepository pokemonRepositoryMock;

    @BeforeEach
    void setUp() {
        pokemonRepositoryMock = mock(PokemonRepository.class);
        pokemonServiceImpl = new PokemonServiceImpl(pokemonRepositoryMock);
    }

    @DisplayName("Testing that whatever the values of the DTO we send in are the same as what we get back")
    @Test
    void createPokemon_happyPathOne() {
//        PokemonDTO pokemonDTO = PokemonDTO.builder()
//                .id(123L)
//                .name("Pikabloo")
//                .isCaught(false)
//                .basicSprite("basicSprite")
//                .detailedSprite("detailedSprite")
//                .abilityName1("abilityName1")
//                .abilityName2("abilityName2")
//                .hp(100)
//                .attack(111)
//                .defence(222)
//                .specialAttack(333)
//                .specialDefence(444)
//                .speed(5)
//                .build();

//        Pokemon pokemon = Pokemon.builder()
//                .id(123L)
//                .name("Pikabloo")
//                .isCaught(false)
//                .basicSprite("basicSprite")
//                .detailedSprite("detailedSprite")
//                .abilityName1("abilityName1")
//                .abilityName2("abilityName2")
//                .hp(100)
//                .attack(111)
//                .defence(222)
//                .specialAttack(333)
//                .specialDefence(444)
//                .speed(5)
//                .build();

//        assertTrue(new ReflectionEquals(pokemonDTO).matches(pokemonDTO));
    }

    @Test
    void getAllPokemon() {
    }

    @DisplayName("" +
            "WHEN calling getPokemonById" +
            "GIVEN an id of a pokemon in the repository" +
            "THEN returns a DTO generated from that pokemon"
    )
    @Test
    void getPokemonById_happyPathOne() {
        Pokemon pokemonInRepo = Pokemon.builder()
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

        PokemonDTO expectedPokemonDTO = PokemonDTO.builder()
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

        when(pokemonRepositoryMock.findById(123L)).thenReturn(Optional.ofNullable(pokemonInRepo));

        assertTrue(new ReflectionEquals(expectedPokemonDTO).matches(pokemonServiceImpl.getPokemonById(123L)));

        verify(pokemonRepositoryMock).findById(123L);
    }

    @DisplayName("" +
            "WHEN calling getPokemonById" +
            "GIVEN an id of a pokemon not in the repository" +
            "THEN throws a ResourceNotFound exception"
    )
    @Test
    void getPokemonById_sadPathOne() {
        when(pokemonRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            pokemonServiceImpl.getPokemonById(404L);
        });

        verify(pokemonRepositoryMock).findById(404L);
    }

    @Test
    void deletePokemonById() {
    }

    @Test
    void updatePokemon() {
    }

    @Test
    void toggleIsCapturedState() {
    }
}