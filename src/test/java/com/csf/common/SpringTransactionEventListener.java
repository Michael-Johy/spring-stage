package com.csf.common;

import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by johnson.yang on 2016/5/13.
 */
@Component
public class SpringTransactionEventListener {

    @TransactionalEventListener
    public void doAfterCommit(){
        RmiServiceExporter exporter = new RmiServiceExporter();
    }

}
