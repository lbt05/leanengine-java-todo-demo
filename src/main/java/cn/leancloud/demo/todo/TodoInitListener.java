package cn.leancloud.demo.todo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.internal.impl.EngineRequestSign;

import cn.leancloud.LeanEngine;

@WebListener
public class TodoInitListener implements ServletContextListener {

  private String appId = System.getenv("LEANCLOUD_APP_ID");
  private String appKey = System.getenv("LEANCLOUD_APP_KEY");
  private String appMasterKey = System.getenv("LEANCLOUD_APP_MASTER_KEY");

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {}

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    // 注册子类化
    AVObject.registerSubclass(Note.class);
    // 初始化AVOSCloud，请保证在整个项目中间只初始化一次
    AVOSCloud.initialize(appId, appKey, appMasterKey);
    // 在请求签名中使用masterKey以激活云代码的最高权限
    EngineRequestSign.instance().setUserMasterKey(true);
    // 打开日志
    AVOSCloud.setDebugLogEnabled(true);
    // 向云引擎注册云函数
    LeanEngine.register(TodoFunctions.class);
    LeanEngine.setLocalEngineCallEnabled(true);
  }
}