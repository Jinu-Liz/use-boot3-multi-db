package com.springboot3.db.entity.first;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.*;

@Getter
@Entity
@Table(name = "pokemon")
public class PokemonEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long pokemonId;

  private String name;

  private String type;

}
