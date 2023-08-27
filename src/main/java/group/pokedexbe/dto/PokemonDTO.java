package group.pokedexbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonDTO {
    // Same fields as the model class
    private Long id;
    private String name;
    private boolean isCaught;
    private String basicSprite;
    private String detailedSprite;
    private String abilityName1;
    private String abilityName2;
    private int hp;
    private int attack;
    private int defence;
    private int specialAttack;
    private int specialDefence;
    private int speed;

}
