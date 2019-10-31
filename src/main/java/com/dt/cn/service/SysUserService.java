package com.dt.cn.service;

import java.util.List;
import java.util.Map;

import com.dt.cn.model.sysUser.SysUser;
import com.dt.cn.model.sysUser.SysUserExample;

public interface SysUserService {

	//List<SysUser> getSystemUserById(int id);
	int insert(SysUser record);
	int deleteByPrimaryKey(Integer id);
	int updateByPrimaryKey(SysUser record);
	SysUser selectByPrimaryKey(Integer id);
	SysUser selectSysUser(Map<String, String> map);
	 List<SysUser> selectByExample(int pageStart, String username);
//	List<SysUser> selectByExample();
	 long count();
	int count(String username);

}
