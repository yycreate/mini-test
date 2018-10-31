package com.yxkj.app.ifunction.base.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxkj.app.common.constant.SystemKey;
import com.yxkj.app.common.constant.ValidationMessge;
import com.yxkj.app.common.eumn.DeleteFlag;
import com.yxkj.app.ifunction.base.BaseMapper;
import com.yxkj.app.ifunction.base.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	@Autowired
	BaseMapper<T> baseMapper;

	@Override
	public Map add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		HashMap<String, Object> resultMap = new  HashMap<>();
		
		try {
			Class c = t.getClass();
			if(existsField(c, "createTime")) {
				Field field = c.getDeclaredField("createTime");
				field.setAccessible(true);
				field.set(t, new Date());
			};
			if(existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			};
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		int result = baseMapper.add(t);
		if(result<=0) {
			resultMap.put(SystemKey.ERROR_KEY, ValidationMessge.SAVE_ERROR);
		}
		return resultMap;
	}

	@Override
	public Map update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Class c = t.getClass();
			if(existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		int result = baseMapper.update(t);
		if(result<=0) {
			resultMap.put(SystemKey.ERROR_KEY, ValidationMessge.SAVE_ERROR);
		}
		resultMap.put("obj", t);
		return null;
	}

	@Override
	public Map getMapById(T t) {
		Map resultMap = new HashMap();
    	T obj = getById(t);
    	if(obj == null)
    	{
    		resultMap.put(SystemKey.ERROR_KEY,ValidationMessge.QUERY_ERROR);
    	}else
    	{
    		resultMap.put("obj",obj);
    	}
        return resultMap;
	}

	@Override
	public T getById(T t) {
		try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return baseMapper.getById(t);
	}

	@Override
    public Map getMapByObj(T t) {
    	Map resultMap = new HashMap();
    	T obj = getByObj(t);
    	if(obj == null)
    	{
    		resultMap.put(SystemKey.ERROR_KEY,ValidationMessge.QUERY_ERROR);
    	}else
    	{
    		resultMap.put("obj",obj);
    	}
        return resultMap;
    }
    
    @Override
    public T getByObj(T t) {
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return baseMapper.getByObj(t);
    }

    @Override
    public List<Map> findListByMap(Map map) {
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.VALID.getCode());
    	}
        return baseMapper.findListByMap(map);
    }
    
    @Override
    public List<T> findListObjByMap(Map map) {
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.VALID.getCode());
    	}
        return baseMapper.findListObjByMap(map);
    }

    @Override
    public PageInfo<Map> findListPageByMap(Map map,Integer pageNum,Integer pageSize) {
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.VALID.getCode());
    	}
//    	PageHelper.startPage(pageNum, pageSize);
        List<Map> list = baseMapper.findListByMap(map);
        PageInfo<Map> page = new PageInfo<Map>(list);

        return page;
    }
    
    @Override
    public PageInfo<T> findListPageObjByMap(Map map,Integer pageNum,Integer pageSize) {
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.VALID.getCode());
    	}
//        PageHelper.startPage(pageNum, pageSize);
        List<T> list = baseMapper.findListObjByMap(map);
        PageInfo<T> page = new PageInfo<T>(list);

        return page;
    }

    @Override
    public Map delete(T t) {
    	Map resultMap = new HashMap();
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.DELETE.getCode());
			}
			if (existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	int result = baseMapper.delete(t);
    	if(result <= 0 )
    	{
    		resultMap.put(SystemKey.ERROR_KEY,ValidationMessge.DEL_ERROR);
    	}
        return resultMap;
    }
    
    @Override
    public Map delete(Map map) {
    	Map resultMap = new HashMap();
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.DELETE.getCode());
    	}
    	if(!map.containsKey("modifyTime"))
    	{
    		map.put("modifyTime", new Date());
    	}
    	int result = baseMapper.delete(map);
    	if(result <= 0 )
    	{
    		resultMap.put(SystemKey.ERROR_KEY,ValidationMessge.DEL_ERROR);
    	}
        return resultMap;
    }
    
	public static boolean existsField(Class clz,String fieldName){
    	try {
    		return clz.getDeclaredField(fieldName)!=null;
		} catch (Exception e) {
		}
    	if(clz!=Object.class) {
    		return existsField(clz.getSuperclass(), fieldName);
    	}
    	return false;
    }
}
