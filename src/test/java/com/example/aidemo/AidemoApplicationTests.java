package com.example.aidemo;

import com.example.aidemo.lock.DistributedLock;
import com.example.aidemo.tencent.SmsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AidemoApplicationTests {

	@Autowired
	private DistributedLock distributedLock;

	@Autowired
	private SmsService smsService;

	@Test
	void contextLoads() {
		boolean result1 = distributedLock.acquire("lock1", 60);
		boolean result2 = distributedLock.acquire("lock1", 60);
		System.out.println(result1+","+result2);
	}

	@Test
	void sms(){
		String templateId = "1";
		String[] templateParams = {"1","2"};
		smsService.sendSms("18698569360", templateId, templateParams);
	}

}
