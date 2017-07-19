package my.plugins;

import java.net.URL;

/**
 * Created by hcq on 2017/7/18.
 */
public class MongoDbTest {
    public static void main(String[] args) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("mongo.properties");
    }
}
