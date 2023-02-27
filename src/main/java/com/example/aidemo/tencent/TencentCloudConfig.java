package com.example.aidemo.tencent;

import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.tencentcloudapi.common.Credential;
import org.springframework.context.annotation.Configuration;

/**
 * @description: smsClient注册
 * @date : 2023-02-26
 */
@Configuration
public class TencentCloudConfig {

    @Value("${tencentcloud.secretId}")
    private String secretId;
    @Value("${tencentcloud.secretKey}")
    private String secretKey;

    @Bean
    public SmsClient smsClient() {
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new SmsClient(cred, "", clientProfile);
    }
}
