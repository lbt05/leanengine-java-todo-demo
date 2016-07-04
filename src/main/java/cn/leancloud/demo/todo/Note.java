package cn.leancloud.demo.todo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("Note")
public class Note extends AVObject {

  @Override
  public String toString() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("content", this.getString("content"));
    result.put("objectId", this.getObjectId());
    result.put("createdAt", this.getCreatedAt());
    return JSON.toJSONString(result);
  }
}