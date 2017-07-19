package my.plugins.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import my.plugins.model.MyMessage;
import my.plugins.queue.ListMessageQueueImpl;
import my.plugins.util.MongoDBDaoUtil;

import java.util.List;

/**
 * Created by hcq on 2017/7/18.
 */
public class MyListAndHandleRunable implements Runnable {
    private ListMessageQueueImpl messageQueue = ListMessageQueueImpl.getInstance();

    private MongoDBDaoUtil mongoDBDao = MongoDBDaoUtil.getInstance();

    /**
     * 定数处理消息队列
     */
    public void run() {
        List<MyMessage> all = messageQueue.getAll();
        for (MyMessage myMessage : all) {
            String destination = myMessage.getDestination();
            Object parse = JSON.parse(myMessage.getConText());
            if (parse instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) parse;
                mongoDBDao.insert(destination, jsonObject);
            } else if (parse instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) parse;
                mongoDBDao.insert(destination, jsonArray);
            }
        }
    }
}
