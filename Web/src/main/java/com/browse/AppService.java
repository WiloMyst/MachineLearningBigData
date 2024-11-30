package com.browse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

	@Autowired
	private AppDao appDao;


	public List<Map<String,Object>> getPart1(){
		return  appDao.getPart1() ;
	}

	public List<Map<String,Object>> getPart2(){
		return appDao.getPart2() ;
	}

	public List<Map<String,Object>> getPart3(){
		return appDao.getPart3() ;
	}

	public List<Map<String,Object>> getPart4(){
		return  appDao.getPart4() ;
	}
	
	public List<Map<String,Object>> getPart41(){
		return  appDao.getPart41() ;
	}
	public List<Map<String,Object>> getPart42(){
		return  appDao.getPart42() ;
	}
	public List<Map<String,Object>> getPart43(){
		return  appDao.getPart43() ;
	}

	
	public List<Map<String,Object>> getPart45(){
		return  appDao.getPart45() ;
	}
	public List<Map<String,Object>> getPart451(){
		return  appDao.getPart451() ;
	}
	public List<Map<String,Object>> getPart5(){
		return appDao.getPart5() ;
	}
	public List<Map<String,Object>> getPart51(){
		return appDao.getPart51() ;
	}

    public String getUser(String username){
        return appDao.getUser(username) ;
    }
    
    public List<Map<String,Object>> get45(String city){
		return appDao.get45(city);
	}
}
