package com.yiguo.serviceImpl;

import com.yiguo.bean.Zone;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ZoneMapper;
import com.yiguo.service.ZoneService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Zone")
@Transactional
public class ZoneServiceImpl extends AbstractBaseServiceImpl<Integer, Zone> implements ZoneService {
    @Autowired
    ZoneMapper dao;

    @Override
    public BaseMapper<Integer, Zone> getDao() {
        // TODO Auto-generated method stub
        return dao;
    }

    @Override
    public Zone getLevelZone(int zoneId, int level) {
        if(level<0||level>3)
            level=3;
        Zone zone=selectByPrimaryKey(zoneId);
        if (zone==null)
            return zone;
        while (zone.getLevel()>level){
            zone=selectByPrimaryKey(zone.getParentId());
            if (zone==null)
                return zone;
        }
        return zone;
    }
}
