package com.eathenliu.hospital.application;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.eathenliu.hospital.R;
import com.eathenliu.hospital.activity.MainActivity;

public class MainApplication extends Application{
	private static MainApplication sInstance = null;
	private static String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sInstance = this;
		if(!hasShortcut()){
			createShortcut();
		}
	}

	public static MainApplication getInstance(){
		if(sInstance == null){
			sInstance = new MainApplication();
		}
		return sInstance;
	}

	private String getDefaultLauncherPackageName(){
		if(Build.VERSION.SDK_INT < 8){
			return "com.android.launcher.settings";
		}else{
			return "com.android.launcher2.settings";
		}
	}

	private String getLauncherPackageName() {
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		final ResolveInfo res = getPackageManager().resolveActivity(intent, 0);
		if (res.activityInfo == null) {
			// should not happen. A home is always installed, isn't it?
			return getDefaultLauncherPackageName();
		}
		if (res.activityInfo.packageName.equals("android")) {
			// 有多个桌面程序存在，且未指定默认项时；
			return getDefaultLauncherPackageName();
		} else {
			return res.activityInfo.packageName;
		}
	}

	private boolean hasShortcut() {
		String title = getResources().getString(R.string.app_name);
		String uri = "content://" + getLauncherPackageName() + ".settings/favorites?notify=true";
		System.out.println(title);
		System.out.println(uri);
		Uri contentUri = Uri.parse(uri);
		try {
			Cursor cursor = getContentResolver().query(contentUri, null, "title=?", new String[]{title}, null);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.close();
				return true;
			}
		}catch(Exception e){
			//部分手机系统获取Settings的权限并不是com.android.launcher.permission.READ_SETTINGS
			//可能会因为没有权限导致SecurityException
			e.printStackTrace();
		}
		return false;
	}

	private void createShortcut(){
		Intent shortCutIntent = new Intent(ACTION_INSTALL_SHORTCUT);
		shortCutIntent.putExtra("duplicate", false);
		shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
		shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_launcher));
		shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(this, MainActivity.class));
		sendBroadcast(shortCutIntent);
	}
}
