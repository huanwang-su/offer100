package com.yiguo.mvc.controller;

import com.yiguo.bean.Page;
import com.yiguo.bean.Zone;
import com.yiguo.service.ZoneService;
import com.yiguo.service.ZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API - ZoneController", description = "地区详情")
@RequestMapping("/zone")
public class ZoneController {
    @Autowired
    ZoneService zoneService;



    @ApiOperation(value = "创建地区",notes = "根据Zone对象创建Zone")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postZone(@RequestBody Zone zone) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="false";
        Integer count=  zoneService.selectByName(zone.getName());
        if(count==0) {
            zoneService.insert(zone);
            f="true";
        }
        return f;
    }

    @ApiOperation(value="获取地区详细信息", notes="根据url的id来获取地区详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method ={RequestMethod.GET})
    public Zone getZone(@PathVariable Integer id ) {
        // 处理"/Zones/{id}"的GET请求，用来获取url中id值的Zone信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return zoneService.selectByPrimaryKey(id);
    }

    @ApiOperation(value="更新地区详细信息", notes="根据url的id来指定更新对象，并根据传过来的Zone信息来更新地区详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putZone(@PathVariable Integer id, @ModelAttribute Zone zone) {
        // 处理"/Zones/{id}"的PUT请求，用来更新Zone信息
        String f="true";
        Zone zone1=new Zone();
        zoneService.updateByPrimaryKeySelective(zone);
        zone1=zoneService.selectByPrimaryKey(id);
        if(zone.equals(zone1))
            f="false";
        return f;
    }

    @ApiOperation(value="删除地区", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteZone(@PathVariable Integer id) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone
        String f="删除成功";
        zoneService.deleteByPrimaryKey(id);
        if(zoneService.selectByPrimaryKey(id)!=null)
            f="删除失败";
        return f;
    }
    @ApiOperation(value="获取子地区", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/getSonZone/{id}", method = RequestMethod.DELETE)
    public List<Zone> getSonZone(@PathVariable Integer id,Page page) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone

        Zone zone1=   zoneService.selectByPrimaryKey(id);
        Zone zone =new Zone();
        zone.setParentId(zone1.getParentId());

        List<Zone> zones= zoneService.select(zone, page);
        return zones;
    }
}
