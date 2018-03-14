package com.yiguo.serviceImpl;

import com.yiguo.bean.Page;
import com.yiguo.dao.BaseMapper;
import com.yiguo.utils.ReflectUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * impl层的基础继承类
 * 
 * @author liyue
 * @date 2018-01-20
 */

@Transactional
public abstract class AbstractBaseServiceImpl<ID, T> {
	public abstract BaseMapper<ID, T> getDao();

	// 对分页查询进行page设置
	public List<T> select(T record, Page page) {
		if (page == null)
			page = new Page();
		if ((page.getPageNumber() == null&&page.getStart()==null) || page.getPageSize() == null || page.getPageSize() >= 100) {
			page.setPageNumber(1);
			page.setPageSize(10);
		}
		return getDao().select(ReflectUtil.generalMap(record, page));
	}
	public int findById(ID id){
		return getDao().findById(id);
	}
	// 插入设置，调用dao层函数
	public int insert(T record) {
		return getDao().insert(record);

	}

	// 通过主键进行插入
	public int insertSelective(ID record) {
		return getDao().insertSelective(record);

	}

	// 通过主键进行查询
	public T selectByPrimaryKey(ID id) {
		return getDao().selectByPrimaryKey(id);

	}

	// 通过实体类进行部分更新处理
	public int updateByPrimaryKeySelective(T record) {
		return getDao().updateByPrimaryKeySelective(record);
	}

	// 通过实体类对象进行全部更新处理
	public int updateByPrimaryKey(T record) {
		return getDao().updateByPrimaryKey(record);
	}

	// 查询数据，存储List
	public List<T> query(T record) {
 
		return getDao().query(record);
	}

	// 统计数据
	public int selectCount(T record) {
		return getDao().selectCount(record);
	}

	// 根据主键进行删除
	public int deleteByPrimaryKey(ID id) {

		return getDao().deleteByPrimaryKey(id);
	}

	//根据名字查询数据是否存在
	public int selectByName(String name) {
		return getDao().selectByName(name);
	}



	public T findAll(){
		return getDao().findAll();
	}
}