package com.dt.cn.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.cn.dao.mapper.SysUserMapper;
import com.dt.cn.model.sysUser.SysUser;
import com.dt.cn.model.sysUser.SysUserExample;
import com.dt.cn.service.SysUserService;

@Service   //服务层
public class SysUserServiceImpl implements SysUserService{  //实现类实现接口类的所有方法
 
	@Resource
	   SysUserMapper sysUserMapper;
//	@Override
//	public List<SysUser> getSystemUserById(int id) {//通过数据库查询系统用户
//		
//		System.out.println("查询用户================="+id);
//		
//		SysUserExample sysUserExample=new SysUserExample();//实例化
//		//内部类实例化对象
//		SysUserExample.Criteria criteria=sysUserExample.createCriteria();//创建具体的条件实例
//		
//		criteria.andIdEqualTo(id);//添加id字段的值是否与参数id一致
//		
//		return sysUserMapper.selectByExample(sysUserExample);//执行查询并还回
//	}
	@Override
	public int insert(SysUser record) {
		return sysUserMapper.insert(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		SysUser qUser=sysUserMapper.selectByPrimaryKey(id);
		int i=0;
		if(qUser!=null) {
			i= sysUserMapper.deleteByPrimaryKey(id);
		}
		return i;
	}
	@Override
	public int updateByPrimaryKey(SysUser record) {
		return sysUserMapper.updateByPrimaryKey(record);
	}
	@Override
	public SysUser selectByPrimaryKey(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
	@Override
	public SysUser selectSysUser(Map<String, String> map) {
		return sysUserMapper.selectSysUser(map);
	}
//	分页
	@Override
	public List<SysUser> selectByExample(int pageStart,String username) {
		SysUserExample sysUserExample=new SysUserExample();
		sysUserExample.setDistinct(true);
		sysUserExample.setPageStart(pageStart);
	
		sysUserExample.setPageSize(2);//没有此行，页面数据不显示
		return sysUserMapper.selectByExample(sysUserExample,username);
	}
	/**
	 * 计算记录条数
	 * @param example  要么该函数有参数，要么该函数里实例化该类的对象
	 * @return  返回时调用了SysUserMapper类的函数，而此函数有参数，参数类型SysUserExample
	 */
	@Override
	public long count() { //
		SysUserExample example=new SysUserExample();
		return sysUserMapper.countByExample(example);
	}
	@Override
	public int count(String username) {
		return sysUserMapper.count(username);
	}


	





	

}
