package com.browse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

	@Autowired
	private AppService appService;

	//用户出行方式
	@RequestMapping(value = "/getPart1")
	public List<Map<String,Object>> getPart1(){
		List<Map<String,Object>> list = appService.getPart1();
		return list;
	}
	@RequestMapping(value = "/getPart2")
	public List<Map<String,Object>> getPart2(){
		List<Map<String,Object>> list = appService.getPart2();
		return list;
	}
	@RequestMapping(value = "/getPart3")
	public List<Map<String,Object>> getPart3(){
		List<Map<String,Object>> list = appService.getPart3();
		return list;
	}
	@RequestMapping(value = "/getPart4")
	public Map getPart4(){
		List<Map<String,Object>> list = appService.getPart4();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("key", keyList);
		return returnMap;
	}
	
	@RequestMapping(value = "/getPart45")
	public Map getPart45(){
		List<Map<String,Object>> list = appService.getPart45();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("key", keyList);
		return returnMap;
	}
	
	
	@RequestMapping(value = "/getPart451")
	public Map getPart451(){
		List<Map<String,Object>> list = appService.getPart451();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("key", keyList);
		return returnMap;
	}
	@RequestMapping(value = "/getPart41")
	public Map getPart41(){
		List<Map<String,Object>> list = appService.getPart41();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("key", keyList);
		return returnMap;
	}
	
	
	@RequestMapping(value = "/getPart42")
	public Map getPart42(){
		List<Map<String,Object>> list = appService.getPart42();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("key", keyList);
		return returnMap;
	}
	@RequestMapping(value = "/getPart43")
	public Map getPart43(){
		List<Map<String,Object>> list = appService.getPart43();

		List<String> keyList = new ArrayList<>();
		List<String> valList = new ArrayList<>();
		List<String> valList1 = new ArrayList<>();
		for (Map<String, Object> m : list) {
			keyList.add(m.get("name").toString());
			valList.add(m.get("value").toString()) ;
			valList1.add(m.get("value1").toString()) ;
		}

		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valList);
		returnMap.put("val1", valList1);
		returnMap.put("key", keyList);
		return returnMap;
	}
	@RequestMapping(value = "/getPart5")
	public List<Map<String,Object>> getPart5(){
		List<Map<String,Object>> list = appService.getPart5();
		return list;
	}

	@RequestMapping(value = "/getPart51")
	public List<Map<String,Object>> getPart51(){
		List<Map<String,Object>> list = appService.getPart51();
		return list;
	}

    @ResponseBody
    @RequestMapping(value = "/login2")
    public String login(String username , String password) {
        if(password.equals(appService.getUser(username))){
            return "sucess";
        }else{
            return "用户名密码错误";
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/get45")
    public Map getCountry45(String city){
        List<Map<String,Object>> list = appService.get45(city);
        List key = new ArrayList();
        List val = new ArrayList();
        Map returnMap = new HashMap<String, List>();
        for(Map<String,Object> m:list) {
            key.add(m.get("name"));
            val.add(m.get("value"));


        }
        returnMap.put("key", key);
        returnMap.put("val", val);
        return returnMap;

    }
}
