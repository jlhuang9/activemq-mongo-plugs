package my.plugins.plugin;

import my.plugins.handle.MyListAndHandle;
import my.plugins.model.MyMessage;
import my.plugins.queue.ListMessageQueueImpl;
import my.plugins.queue.MessageQueue;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hcq on 2017/7/18.
 */
public class MongoDBQueue extends BrokerFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageQueue<MyMessage> queue = ListMessageQueueImpl.getInstance();

    static {
        MyListAndHandle.init();
    }
    public MongoDBQueue(Broker next) {

        super(next);
    }

    /**
     * 把监听到的消息放入队列
     * @param producerExchange
     * @param msg
     * @throws Exception
     */
    @Override
    public void send(ProducerBrokerExchange producerExchange, Message msg) throws Exception {
        MyMessage myMessage = new MyMessage();
        myMessage.setDestination(msg.getDestination().getPhysicalName());
        //判断是否为文本消息
        if (msg instanceof ActiveMQTextMessage) {
            myMessage.setConText(((ActiveMQTextMessage) msg).getText());
            queue.add(myMessage);
        }


//        if (msg instanceof ActiveMQTextMessage) {
//
//        }
//        System.out.println(msg.getMessage());
//        System.out.println("=============================================");
//        System.out.println("目的地"+msg.getDestination().getPhysicalName());
//        System.out.println("消息内容:"+((ActiveMQTextMessage) msg).getText());
//        if (msg.getProperty("appname") != null && msg.getProperty("misid") != null) {
//            if (!msg.getProperty("appname").equals("OSCM") && !msg.getProperty("appname").equals("OSPM")) {
//                logger.info("[FLOW_LOG] MissionID:  " + msg.getProperty("misid") + " Destination: " + msg.getDestination());
//            }
//        }
//        System.out.println("目的地"+msg.getDestination());
//        System.out.println("=============================================");
        super.send(producerExchange, msg);
    }
}
