package com.example.aidemo.tencent;

import com.google.gson.Gson;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: 发送短信
 * @date : 2023-02-26
 */
@Service
public class SmsService {

    @Value("${tencentcloud.sms.appid}")
    private String smsSdkAppid;
    @Value("${tencentcloud.sms.sign}")
    private String sign;

    @Autowired
    private SmsClient smsClient;

    public void sendSms(String phoneNumber, String templateId, String[] templateParams) {
        // 创建 SendSmsRequest 对象
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid(smsSdkAppid);
        req.setSign(sign);
        req.setTemplateID(templateId);
        req.setPhoneNumberSet(new String[] {phoneNumber});
        req.setTemplateParamSet(templateParams);
        // 发送短信
        try {
            SendSmsResponse resp = smsClient.SendSms(req);
            System.out.println(new Gson().toJson(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
