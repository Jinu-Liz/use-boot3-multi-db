package com.springboot3.db.service.first;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot3.db.entity.first.PokemonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot3.db.entity.first.QPokemonEntity.pokemonEntity;

@Service
@RequiredArgsConstructor
public class PokemonService {

  private final JPAQueryFactory queryFactory;

  public List<PokemonEntity> selectPokemon() {
    return queryFactory
      .selectFrom(pokemonEntity)
      .fetch();
  }

}
