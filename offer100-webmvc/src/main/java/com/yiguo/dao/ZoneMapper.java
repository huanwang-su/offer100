package com.yiguo.dao;

import com.yiguo.bean.Page;
import com.yiguo.bean.Zone;
import io.swagger.models.auth.In;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ZoneMapper extends BaseMapper<Integer, Zone> {
    List<Zone> selects(Integer parentId, Page page);
}