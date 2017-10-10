package com.dwebs.pchpol.common.util;import java.io.IOException;import java.util.Properties;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public class GlobalProperties {	private static final Logger logger = LoggerFactory.getLogger(GlobalProperties.class);	private static GlobalProperties THIS;	private static Properties props = null;	public static void init() throws IOException{		try {			props = new Properties();/*			ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();			IConfigService configService = (IConfigService) appCtx.getBean("configService");			List<AppPropertiesVO> proplist = configService.getPropertyAll();			for (AppPropertiesVO prop : proplist) {				props.put(prop.getProperty(), prop.getValue());			}*/		} catch (Exception e) {			e.printStackTrace();		}	}	public static void getInstance() {		if (THIS == null) {			try {				THIS = new GlobalProperties();				synchronized (THIS) {					GlobalProperties.init();				}			} catch (IOException e) {				e.printStackTrace();			}		}	}	public static String getProperty(String key) {		getInstance();		String value = null;		try {			value = props.getProperty(key);			if (value == null) {				value = System.getProperty(key);			} else{				value = value.trim();			}		} catch (Exception e) {			logger.error("key (" + key + ") is not found");			logger.error(e.getMessage());		}		if (value == null){			value = "";		}		return value;	}	public static void setProperty(String key, String value) {		getInstance();		props.setProperty(key, value);	}		public static void reset() {		try {			THIS = new GlobalProperties();			synchronized (THIS) {				GlobalProperties.init();			}		} catch (IOException e) {			e.printStackTrace();		}	}}