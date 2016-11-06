package com.salay.tasks;

import com.salay.remote.RemoteManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Radek Salay on 6.11.2016.
 */
@Service("taskManager")
public class TaskManager {
    Logger log = Logger.getLogger(TaskManager.class);

    @Autowired
    private RemoteManager client;

    public void sendKelpAliveMessage() {
        try{
            client.nudge();
            log.info("Nudge in server");
        }catch(Exception e){
            log.error("Server is down");
        }
    }
}
