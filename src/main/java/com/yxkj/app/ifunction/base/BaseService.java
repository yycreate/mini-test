package com.yxkj.app.ifunction.base;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {
	
	public Map add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public Map update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public Map getMapById(T t);
    
    public T getById(T t);
    
    public Map getMapByObj(T t);
    
    public T getByObj(T t);
    
    public List<Map> findListByMap(Map map);
    
    public List<T> findListObjByMap(Map map);

    public PageInfo<Map> findListPageByMap(Map map,Integer pageNum,Integer pageSize);
    
    public PageInfo<T> findListPageObjByMap(Map map,Integer pageNum,Integer pageSize);

    public Map delete(Map map);
    
    public Map delete(T t);
	
}
