package com.springboot3.db.mapper.second;

import com.springboot3.db.dto.second.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper {

  List<ItemDto> getItems();
}
