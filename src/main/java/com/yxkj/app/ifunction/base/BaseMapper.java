package com.yxkj.app.ifunction.base;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	public int add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public int update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public T getById(T t);
    
    public T getByObj(T t);

    public List<Map> findListByMap(Map map);
    
    public List<T> findListObjByMap(Map map);
    
    public int findCountByMap(Map map);

    public int delete(T t);
    
    public int delete(Map map);
	
}
