package com.ct.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chen.jinshu 2018/08/03
 */
@Configuration
@ConfigurationProperties(prefix = "pubservice")
public class PubservicePropertiesConfig {

    private String accessId;

    private String accessKey;

    private String endPoint;

    private String projectName;

    private String topicName;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "PubservicePropertiesConfig{" + "accessId='" + accessId + '\'' + ", accessKey='" + accessKey + '\''
            + ", endPoint='" + endPoint + '\'' + ", projectName='" + projectName + '\'' + ", topicName='" + topicName
            + '\'' + '}';
    }
}
