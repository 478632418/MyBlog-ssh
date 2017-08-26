package com.dx.ssh.utils;

public class StringUtils {
	public static boolean isBlank(String text) {
		return (text == null || text.trim().length() == 0);
	}
}
