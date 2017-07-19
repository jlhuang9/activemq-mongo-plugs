package my.plugins.plugin;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;
/**
 * Created by hcq on 2017/7/18.
 */
public class MongoDBPlugin implements BrokerPlugin {

    public Broker installPlugin(Broker broker) throws Exception {
        System.out.println("new plusn ");
        return new MongoDBQueue(broker);
    }
}