package com.sherlock.mallcommon.umeng.push;

import com.sherlock.mallcommon.enums.MallErrorEnum;
import com.sherlock.mallcommon.exception.MallException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

public abstract class IOSNotification extends UmengNotification {

	// Keys can be set in the aps level
	protected static final HashSet<String> APS_KEYS = new HashSet<String>(Arrays.asList(new String[]{
			"alert", "badge", "sound", "content-available"
	}));
	
	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws MallException {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (APS_KEYS.contains(key)) {
			// This key should be in the aps level
			JSONObject apsJson = null;
			JSONObject payloadJson = null;
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			if (payloadJson.has("aps")) {
				apsJson = payloadJson.getJSONObject("aps");
			} else {
				apsJson = new JSONObject();
				payloadJson.put("aps", apsJson);
			}
			apsJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson = null;
			if (rootJson.has("policy")) {
				policyJson = rootJson.getJSONObject("policy");
			} else {
				policyJson = new JSONObject();
				rootJson.put("policy", policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (key == "payload" || key == "aps" || key == "policy") {
				throw new MallException(MallErrorEnum.PUSH_IOS_MESSAGE_PARAM_FILL_ERROR);
			} else {
				throw new MallException(MallErrorEnum.PUSH_IOS_MESSAGE_UNKNOWN_PARAM_ERROR);
			}
		}
		
		return true;
	}
	// Set customized key/value for IOS notification
	public boolean setCustomizedField(String key, String value) throws MallException {
		//rootJson.put(key, value);
		JSONObject payloadJson = null;
		if (rootJson.has("payload")) {
			payloadJson = rootJson.getJSONObject("payload");
		} else {
			payloadJson = new JSONObject();
			rootJson.put("payload", payloadJson);
		}
		payloadJson.put(key, value);
		return true;
	}

	public void setAlert(JSONObject token) throws MallException{
    	setPredefinedKeyValue("alert", token);
    }
	
	public void setBadge(Integer badge) throws MallException {
    	setPredefinedKeyValue("badge", badge);
    }
	
	public void setSound(String sound) throws MallException {
    	setPredefinedKeyValue("sound", sound);
    }
	
	public void setContentAvailable(Integer contentAvailable) throws MallException {
    	setPredefinedKeyValue("content-available", contentAvailable);
    }
}
