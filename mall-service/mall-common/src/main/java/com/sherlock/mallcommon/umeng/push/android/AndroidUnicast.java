package com.sherlock.mallcommon.umeng.push.android;


import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.AndroidNotification;

public class AndroidUnicast extends AndroidNotification {
	public AndroidUnicast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws MallException {
    	setPredefinedKeyValue("device_tokens", token);
    }

}