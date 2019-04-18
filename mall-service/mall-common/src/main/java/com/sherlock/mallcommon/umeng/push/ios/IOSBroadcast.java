package com.sherlock.mallcommon.umeng.push.ios;


import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
