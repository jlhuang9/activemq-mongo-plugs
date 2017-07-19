package my.plugins.queue;

import my.plugins.model.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcq on 2017/7/18.
 */
public class ListMessageQueueImpl implements MessageQueue<MyMessage> {
    private static ListMessageQueueImpl ourInstance = new ListMessageQueueImpl();

    private List<MyMessage> list = new ArrayList<MyMessage>();

    public static ListMessageQueueImpl getInstance() {
        return ourInstance;
    }

    private ListMessageQueueImpl() {
    }

    public synchronized void add(MyMessage myMessage) {
        list.add(myMessage);
    }

    public synchronized List<MyMessage> getAll() {
        List<MyMessage> move = new ArrayList<MyMessage>(list);
        list.clear();
        return move;
    }

    /**
     * 暂时不用,就没有写
     * @param list
     */
    public synchronized void reBack(List<MyMessage> list) {

    }
}
