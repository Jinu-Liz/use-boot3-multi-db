package com.springboot3.db.mapper.first;

import com.springboot3.db.dto.first.PokemonDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PokemonMapper {

  List<PokemonDto> getPokemons();

  void addPokemon(PokemonDto pokemonDto);

}
