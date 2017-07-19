package my.plugins.model;

/**
 * Created by hcq on 2017/7/18.
 */
public class MyMessage {
    /**
     * 目的地
     */
    private String destination;
    /**
     * 内容
     */
    private String conText;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConText() {
        return conText;
    }

    public void setConText(String conText) {
        this.conText = conText;
    }
}
