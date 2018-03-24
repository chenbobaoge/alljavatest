package org.bobochen.center;



import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.io.UnsupportedEncodingException;

public class CenterMain {
    public static void main(String[] args)
    {
        for (int i=0;i<2;i++) {
            new Runnable() {
                public void run() {
                    CenterMain.stratnsqcustomer();
                }
            }.run();
        }
        while(true)
        {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static  void stratnsqcustomer(){
//        NSQLookup lookup = new NSQLookupDynMapImpl();
//        lookup.addAddr("192.168.178.128", 4161);
//        NSQConsumer consumer = new NSQConsumer(lookup, "JavaTopic", "dustin", new NSQMessageCallback() {
//
//            @Override
//            public void message(NSQMessage message) {
//                System.out.println("received: " + message);
//                //now mark the message as finished.
//                message.finished();
//
//                //or you could requeue it, which indicates a failure and puts it back on the queue.
//                //message.requeue();
//            }
//            @Override
//            public void error(Exception x) {
//                //handle errors
//
//            }
//        });
//
//        consumer.start();



        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("192.168.178.128", 4161);
        NSQConsumer consumer = new NSQConsumer(lookup, "JavaTopic", "dustin", (message) -> {
            byte[] b = message.getMessage();
            String res = null;
            try {
                res = new String(b,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("Tid:"+Thread.currentThread().getName()+" received: " + res);
            //now mark the message as finished.
            message.finished();

            //or you could requeue it, which indicates a failure and puts it back on the queue.
            //message.requeue();
        });

        consumer.start();
    }
}

