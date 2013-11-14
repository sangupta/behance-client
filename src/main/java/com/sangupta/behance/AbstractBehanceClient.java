package com.sangupta.behance;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

/**
 * Base abstraction for invoking any given end-point.
 * 
 * @author sangupta
 *
 */
public class AbstractBehanceClient {

	protected static final Gson GSON = GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
	
	protected static String BASE_URL = "http://www.behance.net/v2/";
	
	protected final String apiKey;
	
	protected final String clientSecret;
	
	/**
	 * Constructor
	 * 
	 * @param key
	 * @param secret
	 */
	protected AbstractBehanceClient(String key, String secret) {
		this.apiKey = key;
		this.clientSecret = secret;
	}
	
	/**
	 * Allows to change the base path so that API access can be tested against multiple
	 * servers. Should be used with CAUTION.
	 * 
	 * @param basePath
	 */
	protected void changeBaseUri(String basePath) {
		BASE_URL = basePath;
	}

	/**
	 * Invoke any given API
	 * 
	 * @param uriPath
	 * @param clazz
	 * @return
	 */
	protected <T> T invokeApi(String uriPath, Class<T> clazz) {
		char appendable;
		
		if(uriPath.indexOf('?') != -1) {
			appendable = '&';
		} else {
			appendable = '?';
		}
		
		String url = UriUtils.addWebPaths(BASE_URL, uriPath + appendable  + "api_key=" + this.apiKey);
		WebResponse response = WebInvoker.getResponse(url);
		if(response == null || !response.isSuccess()) {
			return null;
		}
		
		return GSON.fromJson(response.getContent(), clazz);
	}
}
