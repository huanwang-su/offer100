package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.Zone;

import java.util.List;
import java.util.Map;

public interface ZoneService extends BaseService<Integer, Zone> {
    /**
     * 获取指定级别的Zone，第0级为国家，依次为省（直辖市）、市（市）、县（区）
     * @return
     */
    Zone getLevelZone(int zoneId, int level);
    List<Zone> selects(Integer parentId, Page page);

}