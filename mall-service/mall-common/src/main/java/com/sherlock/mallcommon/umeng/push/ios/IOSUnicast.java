package com.sherlock.mallcommon.umeng.push.ios;


import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.IOSNotification;

public class IOSUnicast extends IOSNotification {
	public IOSUnicast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws MallException {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
