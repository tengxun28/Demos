package com.iboxpay.android.designmode.create;

import java.util.ArrayList;
import java.util.List;

public class Builder {

    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }


    /**
     * Created by wangyaohui on 16-4-29.
     */
    public  class SmsSender implements Sender {
        @Override
        public void send() {
            System.out.println("SmsSender");
        }
    }

    /**
     * Created by wangyaohui on 16-4-29.
     */
    public  class MailSender implements Sender {
        @Override
        public void send() {
            System.out.println("MailSender");
        }
    }

    /**
     * Created by wangyaohui on 16-4-29.
     */
    public  interface Sender {
        public void send();
    }
}