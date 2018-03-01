package com.yiguo.dao;

import com.yiguo.bean.Zone;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ZoneMapper extends BaseMapper<Integer, Zone> {

}