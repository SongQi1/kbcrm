package com.bocs.core.properties;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/27.
 */

@Configuration
@Primary
public class RedissonProperties extends RedisProperties{


    private boolean enable = false;

    //公共属性
    private Integer idleConnectionTimeout;
    
    private Integer pingTimeout;
    
    private Integer connectTimeout;
    

    private Integer retryAttempts;
    
    private Integer retryInterval;
    
    private Integer reconnectionTimeout;
    
    private Integer failedAttempts;
    

    private Integer subscriptionsPerConnection;
    
    private String clientName;

    //cluster模式和主从模式共有的属性
    
    private String loadBalancer;
    
    private Integer slaveSubscriptionConnectionMinimumIdleSize;
    
    private Integer slaveSubscriptionConnectionPoolSize;
    
    private Integer slaveConnectionMinimumIdleSize;
    
    private Integer slaveConnectionPoolSize;
    
    private Integer masterConnectionMinimumIdleSize;
    
    private Integer masterConnectionPoolSize;
    
    private String readMode;

    private Integer scanInterval;

    //主从模式
    
    private List<String> slaveAddresses;
    
    private String masterAddress;


    //单节点模式
    private Integer subscriptionConnectionMinimumIdleSize;
    
    private Integer subscriptionConnectionPoolSize;
    
    private Integer connectionMinimumIdleSize;
    
    private Integer connectionPoolSize;
    

    private Boolean dnsMonitoring;
    
    private Integer dnsMonitoringInterval;


    public Integer getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(Integer idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public Integer getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(Integer pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }


    public Integer getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(Integer retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    public Integer getReconnectionTimeout() {
        return reconnectionTimeout;
    }

    public void setReconnectionTimeout(Integer reconnectionTimeout) {
        this.reconnectionTimeout = reconnectionTimeout;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }


    public Integer getSubscriptionsPerConnection() {
        return subscriptionsPerConnection;
    }

    public void setSubscriptionsPerConnection(Integer subscriptionsPerConnection) {
        this.subscriptionsPerConnection = subscriptionsPerConnection;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLoadBalancer() {
        return loadBalancer;
    }

    public void setLoadBalancer(String loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public Integer getSlaveSubscriptionConnectionMinimumIdleSize() {
        return slaveSubscriptionConnectionMinimumIdleSize;
    }

    public void setSlaveSubscriptionConnectionMinimumIdleSize(Integer slaveSubscriptionConnectionMinimumIdleSize) {
        this.slaveSubscriptionConnectionMinimumIdleSize = slaveSubscriptionConnectionMinimumIdleSize;
    }

    public Integer getSlaveSubscriptionConnectionPoolSize() {
        return slaveSubscriptionConnectionPoolSize;
    }

    public void setSlaveSubscriptionConnectionPoolSize(Integer slaveSubscriptionConnectionPoolSize) {
        this.slaveSubscriptionConnectionPoolSize = slaveSubscriptionConnectionPoolSize;
    }

    public Integer getSlaveConnectionMinimumIdleSize() {
        return slaveConnectionMinimumIdleSize;
    }

    public void setSlaveConnectionMinimumIdleSize(Integer slaveConnectionMinimumIdleSize) {
        this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
    }

    public Integer getSlaveConnectionPoolSize() {
        return slaveConnectionPoolSize;
    }

    public void setSlaveConnectionPoolSize(Integer slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
    }

    public Integer getMasterConnectionMinimumIdleSize() {
        return masterConnectionMinimumIdleSize;
    }

    public void setMasterConnectionMinimumIdleSize(Integer masterConnectionMinimumIdleSize) {
        this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
    }

    public Integer getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(Integer masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public String getReadMode() {
        return readMode;
    }

    public void setReadMode(String readMode) {
        this.readMode = readMode;
    }


    public Integer getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(Integer scanInterval) {
        this.scanInterval = scanInterval;
    }

    public List<String> getSlaveAddresses() {
        return slaveAddresses;
    }

    public void setSlaveAddresses(List<String> slaveAddresses) {
        this.slaveAddresses = slaveAddresses;
    }

    public String getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }


    public Integer getSubscriptionConnectionMinimumIdleSize() {
        return subscriptionConnectionMinimumIdleSize;
    }

    public void setSubscriptionConnectionMinimumIdleSize(Integer subscriptionConnectionMinimumIdleSize) {
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
    }

    public Integer getSubscriptionConnectionPoolSize() {
        return subscriptionConnectionPoolSize;
    }

    public void setSubscriptionConnectionPoolSize(Integer subscriptionConnectionPoolSize) {
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
    }

    public Integer getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(Integer connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public Integer getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }


    public Boolean getDnsMonitoring() {
        return dnsMonitoring;
    }

    public void setDnsMonitoring(Boolean dnsMonitoring) {
        this.dnsMonitoring = dnsMonitoring;
    }

    public Integer getDnsMonitoringInterval() {
        return dnsMonitoringInterval;
    }

    public void setDnsMonitoringInterval(Integer dnsMonitoringInterval) {
        this.dnsMonitoringInterval = dnsMonitoringInterval;
    }

    /**
     * 是否使用redis
     * @return
     */
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
