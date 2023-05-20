package group.pokedexbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Tells Spring that this is an entity
@Entity
// Lombok generates these things we need for us
@Getter
@Setter
// All args constructor because we actually need it
@AllArgsConstructor
// No args constructors are needed for hibernate to work
@NoArgsConstructor
// Setting the table name in our database
@Table(name = "pokemon")
public class Pokemon {
    // Indicates id as the primary key
    @Id
    // Makes the id auto incremented
    @GeneratedValue
    // Setting column names
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "front_sprite", nullable = false)
    String frontSprite;

    @Column(name = "ability_name_1", nullable = false)
    String abilityName1;

    @Column(name = "ability_name_2")
    String abilityName2;

    @Column(name = "hp", nullable = false)
    int hp;

    @Column(name = "attack", nullable = false)
    int attack;

    @Column(name = "defence", nullable = false)
    int defence;

    @Column(name = "special_attack", nullable = false)
    int specialAttack;

    @Column(name = "special_defence", nullable = false)
    int specialDefence;

    @Column(name = "speed", nullable = false)
    int speed;

}
