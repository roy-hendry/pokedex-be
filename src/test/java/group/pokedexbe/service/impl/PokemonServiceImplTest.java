package group.pokedexbe.service.impl;

import group.pokedexbe.dto.PokemonDTO;
import group.pokedexbe.exceptions.ResourceNotFoundException;
import group.pokedexbe.model.Pokemon;
import group.pokedexbe.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    @DisplayName("" +
            "Testing that whatever the values of the DTO we send in are the same as what we get back" +
            "WHEN calling createPokemon; " +
            "GIVEN valid pokemon data; " +
            "THEN maps DTO to a pokemon " +
            " AND calls the save to repo method"
    )
    @Test
    void createPokemon_happyPathOne() {

        // Arrange
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

        Pokemon newPokemon = Pokemon.builder()
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

        when(pokemonRepositoryMock.save(any())).thenReturn(newPokemon);

        // Act & Assert
        assertTrue(new ReflectionEquals(pokemonDTO).matches(pokemonServiceImpl.createPokemon(pokemonDTO)));
        verify(pokemonRepositoryMock).save(any());
    }

    @DisplayName("" +
            "WHEN calling getAllPokemon; " +
            "GIVEN there are are pokemon in the repository; " +
            "THEN returns a list containing those pokemon"
    )
    @Test
    void getAllPokemon_happyPathOne() {

        // Arrange
        List<PokemonDTO> expectedPokemonDTOList = Arrays.asList(
                PokemonDTO.builder()
                        .id(1L)
                        .name("Bulbasaur")
                        .isCaught(false)
                        .basicSprite("basicSprite")
                        .detailedSprite("detailedSprite1")
                        .abilityName1("abilityName11")
                        .abilityName2("abilityName12")
                        .hp(100)
                        .attack(111)
                        .defence(222)
                        .specialAttack(333)
                        .specialDefence(444)
                        .speed(5)
                        .build(),
                PokemonDTO.builder()
                        .id(2L)
                        .name("Squirtle")
                        .isCaught(false)
                        .basicSprite("basicSprite")
                        .detailedSprite("detailedSprite2")
                        .abilityName1("abilityName21")
                        .abilityName2("abilityName22")
                        .hp(100)
                        .attack(111)
                        .defence(222)
                        .specialAttack(333)
                        .specialDefence(444)
                        .speed(5)
                        .build()
        );

        List<Pokemon> allPokemon = Arrays.asList(
                Pokemon.builder()
                        .id(1L)
                        .name("Bulbasaur")
                        .isCaught(false)
                        .basicSprite("basicSprite")
                        .detailedSprite("detailedSprite1")
                        .abilityName1("abilityName11")
                        .abilityName2("abilityName12")
                        .hp(100)
                        .attack(111)
                        .defence(222)
                        .specialAttack(333)
                        .specialDefence(444)
                        .speed(5)
                        .build(),
                Pokemon.builder()
                        .id(2L)
                        .name("Squirtle")
                        .isCaught(false)
                        .basicSprite("basicSprite")
                        .detailedSprite("detailedSprite2")
                        .abilityName1("abilityName21")
                        .abilityName2("abilityName22")
                        .hp(100)
                        .attack(111)
                        .defence(222)
                        .specialAttack(333)
                        .specialDefence(444)
                        .speed(5)
                        .build()
        );

        when(pokemonRepositoryMock.findAll()).thenReturn(allPokemon);

        // Act
        List<PokemonDTO> result = pokemonServiceImpl.getAllPokemon();

        // Assert
        assertTrue(new ReflectionEquals(expectedPokemonDTOList.get(0)).matches(result.get(0)));
        assertTrue(new ReflectionEquals(expectedPokemonDTOList.get(1)).matches(result.get(1)));

        verify(pokemonRepositoryMock).findAll();
    }

    @DisplayName("" +
            "WHEN calling getAllPokemon; " +
            "GIVEN there are no pokemon in the repository; " +
            "THEN returns an empty list"
    )
    @Test
    void getAllPokemon_happyPathTwo() {

        // Arrange
        List<PokemonDTO> expectedPokemonDTOList = Collections.emptyList();
        List<Pokemon> allPokemon = Collections.emptyList();

        when(pokemonRepositoryMock.findAll()).thenReturn(allPokemon);

        // Act & Assert
        assertTrue(expectedPokemonDTOList.equals(pokemonServiceImpl.getAllPokemon()));

        verify(pokemonRepositoryMock).findAll();
    }

    @DisplayName("" +
            "WHEN calling getPokemonById; " +
            "GIVEN an id of a pokemon in the repository; " +
            "THEN returns a DTO generated from that pokemon"
    )
    @Test
    void getPokemonById_happyPath() {

        // Arrange
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

        // Act & Assert
        assertTrue(new ReflectionEquals(expectedPokemonDTO).matches(pokemonServiceImpl.getPokemonById(123L)));

        verify(pokemonRepositoryMock).findById(123L);
    }

    @DisplayName("" +
            "WHEN calling getPokemonById; " +
            "GIVEN an id of a pokemon not in the repository; " +
            "THEN throws a ResourceNotFound exception; "
    )
    @Test
    void getPokemonById_sadPath() {

        // Arrange
        when(pokemonRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pokemonServiceImpl.getPokemonById(404L);
        });

        verify(pokemonRepositoryMock).findById(404L);
    }

    @DisplayName("" +
            "WHEN calling deletePokemonById; " +
            "GIVEN an id of a pokemon in the repository; " +
            "THEN calls the delete method"
    )
    @Test
    void deletePokemonById_happyPath() {

        // Arrange
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

        when(pokemonRepositoryMock.findById(123L)).thenReturn(Optional.ofNullable(pokemonInRepo));

        // Act
        pokemonServiceImpl.deletePokemonById(123L);

        // Assert
        verify(pokemonRepositoryMock).findById(123L);
        verify(pokemonRepositoryMock).delete(pokemonInRepo);
    }

    @DisplayName("" +
            "WHEN calling deletePokemonById; " +
            "GIVEN an id of a pokemon not in the repository; " +
            "THEN throws a ResourceNotFound exception; "
    )
    @Test
    void deletePokemonById_sadPath() {

        // Arrange
        when(pokemonRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pokemonServiceImpl.getPokemonById(404L);
        });

        verify(pokemonRepositoryMock).findById(404L);
    }

    @DisplayName("" +
            "WHEN calling updatePokemon; " +
            "GIVEN an id of a pokemon in the database" +
            " AND a modified pokemonDTO; " +
            "THEN calls the save method" +
            " AND returns the updated pokemonDTO"
    )
    @Test
    void updatePokemon_happyPath() {

        // Arrange
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

        PokemonDTO modifiedPokemonDTO = PokemonDTO.builder()
                .id(123L)
                .name("Tanjed")
                .isCaught(true)
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
                .name("Tanjed")
                .isCaught(true)
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
        when(pokemonRepositoryMock.save(pokemonInRepo)).thenReturn(pokemonInRepo);

        // Act & Assert
        assertTrue(new ReflectionEquals(expectedPokemonDTO).matches(pokemonServiceImpl.updatePokemon(modifiedPokemonDTO, 123L)));

        verify(pokemonRepositoryMock).findById(123L);
        verify(pokemonRepositoryMock).save(pokemonInRepo);
    }

    @DisplayName("" +
            "WHEN calling updatePokemon; " +
            "GIVEN an id of a pokemon not in the repository; " +
            "THEN throws a ResourceNotFound exception; "
    )
    @Test
    void updatePokemon_sadPath() {

        // Arrange
        when(pokemonRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pokemonServiceImpl.getPokemonById(404L);
        });

        verify(pokemonRepositoryMock).findById(404L);
        verify(pokemonRepositoryMock, never()).save(any());
    }

    @DisplayName("" +
            "WHEN calling toggleIsCapturedState; " +
            "GIVEN an id of a pokemon with a false isCaptured state in the repository; " +
            "THEN inverts the state of the isCaught property to true" +
            " AND returns the updated pokemonDTO"
    )
    @Test
    void toggleIsCapturedState_happyPathOne() {

        // Arrange
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

        Pokemon updatedPokemon = Pokemon.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(true)
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

        PokemonDTO updatedPokemonDTO = PokemonDTO.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(true)
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
                .isCaught(true)
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
        when(pokemonRepositoryMock.save(pokemonInRepo)).thenReturn(updatedPokemon);

        assertTrue(new ReflectionEquals(expectedPokemonDTO).matches(pokemonServiceImpl.toggleIsCapturedState(updatedPokemonDTO)));
    }

    @DisplayName("" +
            "WHEN calling toggleIsCapturedState; " +
            "GIVEN an id of a pokemon with a true isCaptured state in the repository; " +
            "THEN inverts the state of the isCaught property to false" +
            " AND returns the updated pokemonDTO"
    )
    @Test
    void toggleIsCapturedState_happyPathTwo() {

        // Arrange
        Pokemon pokemonInRepo = Pokemon.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(true)
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

        Pokemon updatedPokemon = Pokemon.builder()
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

        PokemonDTO updatedPokemonDTO = PokemonDTO.builder()
                .id(123L)
                .name("Pikabloo")
                .isCaught(true)
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
        when(pokemonRepositoryMock.save(pokemonInRepo)).thenReturn(updatedPokemon);

        assertTrue(new ReflectionEquals(expectedPokemonDTO).matches(pokemonServiceImpl.toggleIsCapturedState(updatedPokemonDTO)));
    }

    @DisplayName("" +
            "WHEN calling toggleIsCapturedState; " +
            "GIVEN an id of a pokemon not in the repository; " +
            "THEN throws a ResourceNotFoundException"
    )
    @Test
    void toggleIsCapturedState_sadPath() {

        // Arrange
        when(pokemonRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pokemonServiceImpl.getPokemonById(404L);
        });

        verify(pokemonRepositoryMock).findById(404L);
        verify(pokemonRepositoryMock, never()).save(any());
    }
}