package com.salay.config;

import com.salay.remote.RemoteManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by Radek Salay on 6.11.2016.
 */
@Configuration
public class HessianConfig {

    @Bean(name = "client")
    public RemoteManager hessianProxyFactoryBean(){
        HessianProxyFactoryBean bean = new HessianProxyFactoryBean();
        bean.setServiceUrl("http://localhost:8080/saveTicket.http");
        bean.setServiceInterface(RemoteManager.class);
        bean.afterPropertiesSet();
        return (RemoteManager) bean.getObject();
    }
}
