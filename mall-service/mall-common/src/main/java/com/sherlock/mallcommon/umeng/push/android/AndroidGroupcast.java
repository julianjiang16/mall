package com.sherlock.mallcommon.umeng.push.android;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.umeng.push.AndroidNotification;
import org.json.JSONObject;

public class AndroidGroupcast extends AndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws MallException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws MallException {
    	setPredefinedKeyValue("filter", filter);
    }
}
