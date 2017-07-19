package my.plugins.util;

import java.util.ResourceBundle;

/**
 * Created by hcq on 2017/7/18.
 */
public class ResourceUtil {
    private static ResourceBundle resource;

    static{
        resource = ResourceBundle.getBundle("mongo");
    }

    /**
     * 读取配置文件内容
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return resource.getString(key);
    }

    public static void main(String[] args) {
        System.out.println(resource.getString("mongo.host"));
    }
}
