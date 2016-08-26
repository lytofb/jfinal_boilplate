package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jfinal.plugin.ehcache.CacheKit;

public class ModelEx<M extends Model> extends Model<M> {
	private String tableName;
	
	public ModelEx(){
		tableName = TableMapping.me().getTable(getClass()).getName();
	}
    
    private void checkTableName(){
        if (StrKit.isBlank(tableName))
            throw new IllegalArgumentException("tableName can not be blank,please setTableName(tableName)");
    }
 
    public M searchFirst(String key, Object value) {
        List<M> mList =  search(key, value, "");
        return mList!=null && mList.size()>0 ? mList.get(0):null;
    }
    
    public List<M> selectAll() {
		String sql = "select * from "+tableName+" where deleteflag = 1";
		return find(sql);
	}
 
    public List<M> search(String key, Object value) {
        return search(key, value, "");
    }
 
    public List<M> search(String key, Object value, String orderBy) {
        checkTableName();
        String sql = "select * from " + tableName + " where "+ key +"=? and deleteflag = 1 " + orderBy;
        return find(sql, value);
    }
 
    public M searchFirst(Map<String, Object> maps) {
        List<M> mList =  search(maps, "");
        return mList!=null && mList.size()>0 ? mList.get(0):null;
    }
 
    public List<M> search(Map<String, Object> maps) {
        return search(maps, "");
    }
 
    public List<M> search(Map<String, Object> maps, String orderBy) {
        checkTableName();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(tableName).append(" where 1=1 and deleteflag = 1 ");
        List<Object> values = new ArrayList<Object>();
        for(Entry<String,Object> entry:maps.entrySet()){
            if(entry.getValue() != null){
                sb.append(" and ").append(entry.getKey()).append("=?");
                values.add(entry.getValue());
            }
        }
        sb.append(" ").append(orderBy);
        return find(sb.toString(), values.toArray());
    }
 
    public List<M> searchByCache(String cacheName, Object key, Map<String, Object> maps){
        return this.searchByCache(cacheName, key, maps,"");
    }
 
    public List<M> searchByCache(String cacheName, Object key, Map<String, Object> maps, String orderBy) {
        checkTableName();
        List<M> result = CacheKit.get(cacheName, key);
        if (result == null) {
            result = search(maps);
            CacheKit.put(cacheName, key, result);
        }
        return result;
    }
 
    public Page<M> searchPaginate(int pageNumber, int pageSize, Map<String, Object> maps){
        return this.searchPaginate(pageNumber, pageSize, maps, "");
    }
 
    public Page<M> searchPaginate(int pageNumber, int pageSize, Map<String, Object> maps, String orderBy) {
        checkTableName();
        StringBuilder sb = new StringBuilder();
        sb.append("from ").append(tableName).append(" where 1=1 and deleteflag = 1 ");
        List<Object> values = new ArrayList<Object>();
        for(Entry<String,Object> entry:maps.entrySet()){
            if(entry.getValue() != null){
                sb.append(" and ").append(entry.getKey()).append("=?");
                values.add(entry.getValue());
            }
        }
        return paginate(pageNumber, pageSize, "select *", sb.toString(),values.toArray());
    }
 
    public Page<M> searchPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize, Map<String, Object> maps) {
        return this.searchPaginateByCache(cacheName, key, pageNumber, pageSize, maps, "");
    }
 
    public Page<M> searchPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize, Map<String, Object> maps, String orderBy) {
        checkTableName();
        Page<M> result = CacheKit.get(cacheName, key);
        if (result == null) {
            result = searchPaginate(pageNumber, pageSize, maps, orderBy);
            CacheKit.put(cacheName, key, result);
        }
        return result;
    }
}
