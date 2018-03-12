package com.yiguo.dao;

import com.yiguo.bean.Education;

import java.util.List;
import java.util.Map;

/**
 * 
 * dao层基类接口
 * 
 * @author liyue
 * @date 2018-01-20
 */

public interface BaseMapper<ID, T> {
	// 将数据插入数据库，插入对象为实体类对象
	int insert(T record);

	// 根据主键进行删除数据库数据，一次删除一条,返回数值
	int deleteByPrimaryKey(ID id);

	// 根据主键进行插入操作
	int insertSelective(ID id);

	// 根据主键进行查找数据
	T selectByPrimaryKey(ID id);

	// 根据实体类对象进行更新操作，可更新部分数据或者全部数据
	int updateByPrimaryKeySelective(T record);

	// 根据对象进行更新数据库，必须全部数据要进行更新
	int updateByPrimaryKey(T record);

	// 根据对象进行查询数据库，返回List列表
	List<T> query(T record);

	// 根据对象进行查询数据库，统计查出的数据有多哦少
	int selectCount(T record);
	int findById(ID id);
	// 进行分页处理
	List<T> select(Map map);

	int selectByName(String name);

	T findAll();
}
