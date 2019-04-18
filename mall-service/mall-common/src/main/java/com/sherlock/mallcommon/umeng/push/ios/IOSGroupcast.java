package com.sherlock.mallcommon.umeng.push.ios;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.IOSNotification;
import org.json.JSONObject;

public class IOSGroupcast extends IOSNotification {
	public IOSGroupcast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws MallException {
    	setPredefinedKeyValue("filter", filter);
    }
}
