package group.pokedexbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PokemonDTO {
    // Same fields as the model class
    private Long id;
    private String name;
    private String frontSprite;
    private String abilityName1;
    private String abilityName2;
    private int hp;
    private int attack;
    private int defence;
    private int specialDefence;
    private int specialAttack;
    private int speed;
}
