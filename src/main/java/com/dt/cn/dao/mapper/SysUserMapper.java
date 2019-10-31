package com.dt.cn.dao.mapper;

import com.dt.cn.model.sysUser.SysUser;
import com.dt.cn.model.sysUser.SysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//@Repository
@Mapper
public interface SysUserMapper {
	//与SysUserMapper.xml的id对应   映射
    long countByExample(SysUserExample example);
    int count(@Param("username")String username);
    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(@Param("example")SysUserExample example, @Param("username")String username);

    SysUser selectByPrimaryKey(Integer id);
    
    SysUser selectSysUser(Map<String, String> map); //自己定义的函数，详情见xml

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}