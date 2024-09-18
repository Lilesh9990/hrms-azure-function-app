package com.adt.azfunction.util;

import com.microsoft.azure.functions.ExecutionContext;

public class Utility {

	public static Integer stringToInt(String intString, ExecutionContext context) {
		intString = intString != null ? intString.trim() : "";
		int intValue = 0;
		if (!intString.isEmpty()) {
			try {
				intValue = Integer.valueOf(intString);
			} catch (Exception e) {
				context.getLogger().info("Utility: Exception while stringToInt.");
			}
		}
		return intValue;
	}

}
