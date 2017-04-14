package org.yinet.tools.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONTools {
	/**
	 * 向数据库中添加json格式的数据
	 */
	public void Insert(){
		//String sql = "INSERT INTO friendidlist(playerId,friendId) VALUES(?,?)";
		String id = getJsonValue(new int[]{260227,2,3,4,5},260227);
		System.out.println(id);
		//JDBCtools.jdbcTemplate.update(sql,260227,id);
	}
	/**
	 * 将一串字符串转成json对象再解分
	 * 返回一个对象id的集合
	 * @param string
	 * @param key
	 * @param t
	 * @return
	 * 链接数据库示例：JDBCDAO dao = new JDBCDAO();
	 *	String sql = "SELECT friendId FROM friendidlist WHERE playerId=?";
	 *	String i = dao.getQureyFoValue(String.class, sql,1);
	 *	System.out.println(i);
	 *	List<Integer> list = jsonTools.jsonList(i, 1+"", int.class);
	 *	System.out.println(list.toString());
	 *
	 *注：key必须是集合中的第一个值的toString()类,一般为角色自己的id
	 */
	public <T> List<T> jsonList(String object,String key,Class<T> t){
		List<T> friendId;
		JSONObject jsonObject = new JSONObject(object);

		//拼接成一个list集合
		JSONArray jsonArray = jsonObject.getJSONArray(key);
		friendId = new ArrayList<T>(jsonArray.length());
		for(int i=0;i<jsonArray.length();i++){
			T value = (T)jsonArray.get(i);
			friendId.add(value);
		}
		return friendId;
	}
	/**
	 * 将一个对象转成json文件
	 * @param value
	 * @param playerId
	 * @return
	 * 示例：String id = getJsonValue(new int[]{260227,2,3,4,5},260227);
	 */
	public String getJsonValue(Object value,Integer playerId){
		JSONObject ppdashi = new JSONObject();
		try {
			ppdashi.put(playerId.toString(), value);
			
		} catch (Exception e) {
		}
		
		return ppdashi.toString();
	}
}
