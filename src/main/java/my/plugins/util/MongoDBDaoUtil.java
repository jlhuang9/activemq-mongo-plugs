package my.plugins.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;


/**
 * Created by hcq on 2017/7/18.
 */
public class MongoDBDaoUtil {
    public static final String HOST = ResourceUtil.getProperty("mongo.host");
    public static final int PORT = Integer.parseInt(ResourceUtil.getProperty("mongo.port"));
    public static final String DEFAULT_DBNAME = ResourceUtil.getProperty("dbName");

    private MongoClient mongoClient = null;
    private static final MongoDBDaoUtil mongoDBDaoUtil = new MongoDBDaoUtil();

    public static MongoDBDaoUtil getInstance() {
        return mongoDBDaoUtil;
    }

    /**
     * 这里是网上找的不知道靠谱不
     */
    private MongoDBDaoUtil() {
        if (mongoClient == null) {
            MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
            buide.connectionsPerHost(100);// 与目标数据库可以建立的最大链接数
            buide.connectTimeout(1000 * 60 * 20);// 与数据库建立链接的超时时间
            buide.maxWaitTime(100 * 60 * 5);// 一个线程成功获取到一个可用数据库之前的最大等待时间
            buide.threadsAllowedToBlockForConnectionMultiplier(100);
            buide.maxConnectionIdleTime(0);
            buide.maxConnectionLifeTime(0);
            buide.socketTimeout(0);
            buide.socketKeepAlive(true);
            MongoClientOptions myOptions = buide.build();
            try {
                mongoClient = new MongoClient(new ServerAddress(HOST, PORT), myOptions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insert(String collectionName, JSONObject jsonObject) {
        return insert(DEFAULT_DBNAME, collectionName, jsonObject);
    }

    public boolean insert(String collectionName, JSONArray jsonArray) {
        return insert(DEFAULT_DBNAME, collectionName, jsonArray);
    }

    /**
     * object 单挑插入
     * @param dbName
     * @param collectionName
     * @param jsonObject
     * @return
     */
    public boolean insert(String dbName, String collectionName, JSONObject jsonObject) {
        try {
            MongoDatabase database = mongoClient.getDatabase(dbName);

            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document doc = pareJSONObjectToDocument(jsonObject);
            collection.insertOne(doc);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * array批量插入
     * @param dbName
     * @param collectionName
     * @param jsonArray
     * @return
     */
    public boolean insert(String dbName, String collectionName, JSONArray jsonArray) {
        try {
            MongoDatabase database = mongoClient.getDatabase(dbName);

            MongoCollection<Document> collection = database.getCollection(collectionName);
            List<Document> docs = new ArrayList<Document>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Document doc = pareJSONObjectToDocument(jsonObject);
                docs.add(doc);
            }
            collection.insertMany(docs);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 把JSONObject转为Document
     * @param jsonObject
     * @return
     */
    private Document pareJSONObjectToDocument(JSONObject jsonObject) {
        Document doc = new Document();
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            doc.put(entry.getKey(), entry.getValue());
        }
        doc.put("handleTime", new Date());
        return doc;
    }
}
