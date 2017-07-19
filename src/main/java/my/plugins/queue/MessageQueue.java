package my.plugins.queue;

import java.util.List;

/**
 * Created by hcq on 2017/7/18.
 */
public interface MessageQueue<T> {

    void add(T t);

    List<T> getAll();

    void reBack(List<T> list);
}

