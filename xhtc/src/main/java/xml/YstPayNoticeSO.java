package xml;

import java.io.Serializable;

public class YstPayNoticeSO implements Serializable{

    private static final long serialVersionUID = 4289453285503271416L;

    private String request_type;

    private String req_seq;

    private String channel;

    private String trans_seq;

    private String total_fee;

    private String pay_time;

    private String status;

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getReq_seq() {
        return req_seq;
    }

    public void setReq_seq(String req_seq) {
        this.req_seq = req_seq;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTrans_seq() {
        return trans_seq;
    }

    public void setTrans_seq(String trans_seq) {
        this.trans_seq = trans_seq;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
