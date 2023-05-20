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
    private String front_sprite;
    private String ability_name_1;
    private String ability_name_2;
    private int hp;
    private int attack;
    private int defence;
    private int special_defence;
    private int special_attack;
    private int speed;
}
