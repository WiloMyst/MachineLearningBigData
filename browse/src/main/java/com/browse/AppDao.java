package com.browse;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppDao {

    @Select("select password from users where username = #{username, jdbcType=VARCHAR}")
    public String getUser( @Param("username") String username);

	@Select("select style name,num value from demo4")
	public List<Map<String,Object>> getPart1();

	@Select("select  name,value from demo6")
	public List<Map<String,Object>> getPart2();

	@Select("select country name,num value from demo8")
	public List<Map<String,Object>> getPart3();

	@Select("select name, value from demo3 order by name")
	public List<Map<String,Object>> getPart4();
	
	@Select("select  name, value from demo6 order by name")
	public List<Map<String,Object>> getPart41();
	
	@Select("select  value  name, name  value from demo1 order by name desc  limit 50")
	public List<Map<String,Object>> getPart42();
	@Select("select  name, value from demo7")
	public List<Map<String,Object>> getPart45();
	
	@Select("select  name, value from demo4 order by name ")
	public List<Map<String,Object>> getPart451();
	@Select("SELECT name,TRUNCATE(AVG(VALUE),3) value,TRUNCATE(AVG(value1),3) value1 FROM demo2 GROUP BY NAME")
	public List<Map<String,Object>> getPart43();

	@Select("select sex name,num value from demo7")
	public List<Map<String,Object>> getPart5();
	
	@Select("select  name, value from demo5")
	public List<Map<String,Object>> getPart51();
	
	  @Select("select goodid name,num value from demo8 where style = #{city, jdbcType=VARCHAR} order by num desc limit 10")
	    public List<Map<String,Object>> get45( @Param("city") String city);

}
