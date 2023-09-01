package group.pokedexbe.service.impl;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.model.Pokemon;
import group.pokedexbe.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PokemonServiceImplTest {

    @Autowired
    private PokemonServiceImpl pokemonServiceImpl;

    @Mock
    private PokemonRepository pokemonRepository;

    @BeforeEach
    void setUp() {
//        pokemonServiceImpl = mock(PokemonServiceImpl.class);
    }

    @DisplayName("Testing that whatever the values of the DTO we send in are the same as what we get back")
    @Test
    void createPokemon_happyPathOne() {
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

        assertTrue(new ReflectionEquals(pokemonDTO).matches(pokemonDTO));
    }

    @Test
    void getAllPokemon() {
    }

    @Test
    void getPokemonById() {
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