package com.sherlock.mallcommon.umeng.push.android;


import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
