package com.yiguo.mvc.controller;

import com.yiguo.bean.Bulletin;
import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yiguo.service.BulletinService;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@RestController
@Api(value = "API - BulletinController", description = "Bulletin详情")
@RequestMapping(value="/bulletin")
public class BulletinController {
	@Autowired
	BulletinService bulletionService;
	@ApiOperation(value = "获取公告列表",notes = "")
	@ResponseBody
	@RequestMapping(value = "/getBulletinList", method = RequestMethod.GET)
	public PageInfo<Bulletin> getBulletinList(@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
         Bulletin bulletin =new Bulletin();

		PageInfo<Bulletin> pageinfo=new PageInfo<Bulletin>();
		pageinfo.setPageNum(pageNumber);
		pageinfo.setPageSize(pageSize);
		Page page= new Page();
		page.setPageNumber(pageNumber);
		page.setPageSize(pageSize);

		pageinfo.setRows( bulletionService.select(bulletin,page));
		int count=bulletionService.selectCount(bulletin);
		pageinfo.setTotal(count);
		return pageinfo;
	}

	@ApiOperation(value = "创建公告信息",notes = "根据bulletin对象创建bulletin")
	@ResponseBody

	@RequestMapping(value = "/buildBulletin", method = RequestMethod.POST)
	public String buildBulletin(@RequestBody Bulletin bulletin) {
		String f=FAILURE;
		Integer count=  bulletionService.insert(bulletin);
		if(count > 0) {

			f=SUCCESS;
		}
		//System.out.println(f);
		return f;
	}


	@ApiOperation(value="根据id获取公告详细信息", notes="根据url的id来获取公告详细信息")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Bulletin getBulletin(@PathVariable Integer id) {

		Bulletin bulletin=new Bulletin();
		bulletin=bulletionService.selectByPrimaryKey(id);
		return bulletin;
	}

	@ApiOperation(value="更新公告详细信息", notes="根据url的id来指定更新对象，并根据传过来的bulletin信息来更新用户详细信息")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateBulletin(@PathVariable Integer id, @RequestBody Bulletin bulletin) {
		if (bulletionService.findById(id) > 0) {
			bulletin.setId(id);
			int num = bulletionService.updateByPrimaryKeySelective(bulletin);
			if (num > 0) {
				return SUCCESS;
			} else
				return FAILURE;
		}
		return "this id does not exist";
	}

	@ApiOperation(value="删除公告信息", notes="根据url的id来指定删除对象")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteBulletin(@PathVariable Integer id) {
		if (bulletionService.findById(id) > 0) {
		int num = bulletionService.deleteByPrimaryKey(id);
		if(num > 0) {
			return SUCCESS;
		}else
			return FAILURE;

		}
		return "this id does not exist";
	}


}
