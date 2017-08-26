package com.dx.ssh.utils;

public class AgentUtils {
	public static String getBrowser(String userAgent) {
		if (userAgent == null || userAgent.trim().length() < 1) {
			return "unknow ";
		}
		
		String[] brorserEN = new String[] { "MyIE2", "Firefox", "KuGooSoft", "LBBROWSER", "TheWord", "QQ", "Maxthon",
				"BIDUPlayerBrowser", "Opera", "Chrome", "Safari", "9A334", "UCWEB", "googlebot", "rv 11.0" };
		String[] brorserCN = new String[] { "MyIE2", "Firefox", "酷狗", "猎豹", "世界之窗", "QQ", "Maxthon", "百度影音", "Opera",
				"Chrome", "Safari", "360", "UCWEB", "googlebot", "IE 11.0" };
		for (int i = 0; i < brorserEN.length; i++) {
			if (userAgent.indexOf(brorserEN[i]) > -1) {
				return brorserCN[i];
			}
		}
		
		if (userAgent.indexOf("MSIE") > -1) {
			if (userAgent.indexOf("MSIE 9.0") > -1) {
				return "IE 9.0";
			} else if (userAgent.indexOf("MSIE 10.0") > -1) {
				return "IE 10.0";
			} else if (userAgent.indexOf("MSIE 8.0") > -1) {
				return "IE 8.0";
			} else if (userAgent.indexOf("MSIE 7.0") > -1) {
				return "IE 7.0";
			} else if (userAgent.indexOf("MSIE 6.0") > -1) {
				return "IE 6.0";
			}
			return "IE";
		}
		
		return "unknow Browser";
	}
}
