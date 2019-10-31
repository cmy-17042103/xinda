
package com.dt.cn.controller.sysUser;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.cn.model.sysUser.SysUser;
import com.dt.cn.model.sysUser.SysUserExample;
import com.dt.cn.service.SysUserService;

//通过注解匹配路径
 @Controller
// @RequestMapping("/sysuser")
public class SystemUserController {
 
	@Resource // 初始化对象
   SysUserService sysUserService;
	/**
	 * 根据id获取用户列表
	 * @param map  存放返回的用户信息
	 * @param userid   根据id查用户
	 * @return   返回用户信息页面
	 */
//	@RequestMapping("/sysuserlist")
//	public String getSystemUserById(Map<String,Object> map,Integer userid) {   //Model model
//		List<SysUser> sysUserList=sysUserService.getSystemUserById(userid);
//		map.put("Userlist", sysUserList);//第一个参数与html对应
//		System.out.println(sysUserList);
//		return "userlist";  //userlist
//	}
/**
 * 分页
 * @param map  向页面传值     Model model也阔以，用函数addA。。
 * @param pageStart  为limit函数设置起始值
 * @return
 */
	@RequestMapping("/page")
	public String fenye(Map<String,Object> map,
			@RequestParam(defaultValue="0")int pageStart,SysUserExample example,
			@RequestParam(defaultValue="")String username) {  //封装数据类型可以不赋初始值
		List<SysUser> sysUserList=sysUserService.selectByExample(pageStart,username);
		SysUserExample sysUserExample=new SysUserExample();
		sysUserExample.setPageSize(2);
		int pageSize=sysUserExample.getPageSize();
		long n=sysUserService.count();
		if(username.trim()==null || username.trim().equals("")) {
			n=sysUserService.count();
		}else {
			n=sysUserService.count(username);
		}
		map.put("Userlist", sysUserList);
		map.put("pageStart", pageStart);
		map.put("username1", username);
		map.put("pageSize", pageSize);
		map.put("count",n);
		System.out.println("find username:"+username);
		System.out.println("总的记录条数count："+n);
		return "userlist2";//redirect:..userlist.html
	}
	/**
	 * 注册
	 * @param map   存放填写的用户信息
	 * @param sysUser  实体类类型，把填写的用户信息存里面
	 * @return  注册成功返回刚注册的用户信息，否则，错误页面
	 */
	@RequestMapping("/insert")
	public String showUser(Map<String,Object> map,SysUser sysUser) {
		int i=sysUserService.insert(sysUser);
		map.put("insertUser", sysUser);//第一个参数与html对应
		//System.out.println(sysUser);
		if(i==1) {
			return "showuser";
		}else {
			return "error";
		}
	}
	/**
	 * 查询
	 * @param map  存放查询的用户信息
	 * @param id   根据id查用户
	 * @return   返回查询的用户信息页面，否则错误页面
	 */
	@RequestMapping("/query")
	public String queryUser(Map<String,Object> map,Integer id) {
		  //@RequestParam(value="xid",defaultValue="1",required=false)
		  //  value属性为传参别名，defaultValue默认赋值，required是否必须传值默认true，设置为false，不传参则不报错
		SysUser qUser=sysUserService.selectByPrimaryKey(id);
		System.out.println("Controller  query===="+qUser);
		map.put("queryUser", qUser);
		if(qUser==null) {
			return "error";
		}else {
			return "queryuser";
		}
	}
	
	/**
	 * 删除
	 * @param id  根据id删除用户
	 * @return  删除成功，返回成功页面。否则，错误页面
	 */
	@RequestMapping("/delete")
	public String deleteUser(Integer id) {
//		SysUser qUser=sysUserService.selectByPrimaryKey(id);
//		System.out.println("Controller    delete===="+qUser);
		int i=sysUserService.deleteByPrimaryKey(id);
		if(i==1) {
			return "success";
		}else {
			return "error";
		}
	}
	/**
	 * 修改用户信息
	 * @param sysUser  存放修改过的用户信息
	 * @return    修改成功，返回成功页面。否则，错误页面
	 */
	@RequestMapping("/edit")
	public String editUser(SysUser sysUser) {
		int i=sysUserService.updateByPrimaryKey(sysUser);
		System.out.println("Controller    edit===="+i);
		if(i==1) {
			return "success";
		}else {
			return "error";
		}
	}
	/**
	 * 修改用户信息（老师的方法）   在查询后的页面进行直接修改。所以用到@ResponseBody，不返回页面只返回数据
	 * @param id   根据id修改用户信息
	 * @return  返回map集合，存放修改后的用户信息
	 */
	@ResponseBody    //作用：只接收返回的数据，忽略页面。使用 jquery ajax 时，不用它，一直进入error : ，检查后台并无异常。
	@RequestMapping("/edit02")
	public Map<String, Object>  edit02(Integer id) {		
		SysUser sysUser = sysUserService.selectByPrimaryKey(id);
//		int i=sysUserService.updateByPrimaryKey(sysUser);
//		System.out.println("Controller    edit===="+i);   //没有反应
		System.out.println("sysid=:"+sysUser.getId());
//		SystemUserController s=new SystemUserController();
//		s.editUser(sysUser);  //查询出现警告窗口，没有数据
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sysUser", sysUser);
		return map;
	}
	/**
	 * 登录
	 * @param username 用户名
	 * @param password  密码
	 * @return  有该用户返回成功页面，否则，失败页面
	 */
	@RequestMapping("/login")
	public String login(String username,String password) {
		Map<String,String> m=new HashMap<String,String>();
		m.put("username", username);
		m.put("password", password);
		System.out.println("Controller    login  username===="+username);
		SysUser s=sysUserService.selectSysUser(m);
		System.out.println("Controller    login  s===="+s);
		if(s!=null) {
			 return "success";//redirect:..success.html
			
		}else {
			return "error";
		}
	   
	}
	
	
}
