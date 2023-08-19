package com.springboot3.db.service.second;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot3.db.entity.second.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot3.db.entity.second.QItemEntity.itemEntity;

@Service
@RequiredArgsConstructor
public class ItemService {

  @Qualifier("SecondQF")
  private final JPAQueryFactory queryFactory;

  public List<ItemEntity> selectItems() {

    return queryFactory
      .selectFrom(itemEntity)
      .fetch();
  }

}
