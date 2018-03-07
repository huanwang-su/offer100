package com.yiguo.service;

import com.yiguo.bean.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 
 * Service层的基类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public interface BaseService<ID, T> {
	// 通过实体类进行插入数据库
	int insert(T record);

	// 根据主键进行删除数据
	@CacheEvict(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	int deleteByPrimaryKey(ID id);

	// 通过实体类进行插入数据库
	int insertSelective(ID record);

	// 根据主键进行查询数据库
	@Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	T selectByPrimaryKey(ID id);

	// 根据实体类对象进行部分更新数据库
//	@CachePut(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	int updateByPrimaryKeySelective(T record);

	// 根据实体类对象进行全部更新数据库
	int updateByPrimaryKey(T record);

	// 查询数据库，保存在List
	List<T> query(T record);

	// 统计查询的数量
	int selectCount(T record);

	// 分页查询处理
	List<T> select(T record, Page page);

	int selectByName(String name);
}
