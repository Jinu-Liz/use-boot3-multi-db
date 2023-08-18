package com.springboot3.db.service.second;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot3.db.entity.second.ItemEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot3.db.entity.second.QItemEntity.itemEntity;

@Service
public class ItemService {

  private final JPAQueryFactory secondQueryFactory;

  public ItemService(@Qualifier("SecondQF") JPAQueryFactory secondQueryFactory) {
    this.secondQueryFactory = secondQueryFactory;
  }

  public List<ItemEntity> selectItems() {

    return secondQueryFactory
      .selectFrom(itemEntity)
      .fetch();
  }

}
