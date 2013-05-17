package com.karlliu.combinephrase;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ActivityManageApplication extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();
	private static ActivityManageApplication instance;
	
	private ActivityManageApplication() {
	}

	public static ActivityManageApplication getInstance() {
	    if (null == instance) {
	        instance = new ActivityManageApplication();
	    }
	    return instance;
	}

	public void addActivity(Activity activity) {
	    activityList.add(activity);
	}

	public void exit() {
	    for (Activity activity : activityList) {
	        activity.finish();
	    }
	    System.exit(0);
	}
}