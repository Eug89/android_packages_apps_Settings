package com.android.settings.widget.buttons;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.widget.SettingsAppWidgetProvider;
import com.android.settings.widget.WidgetSettings;

public class AutoRotateButton extends WidgetButton {

	static AutoRotateButton ownButton=null;

	@Override
	public void toggleState(Context context) {
		if(getOrientationState(context)==0) {        	
			Settings.System.putInt(
					context.getContentResolver(), 
					Settings.System.ACCELEROMETER_ROTATION, 1);        	
		} else {
			Settings.System.putInt(
					context.getContentResolver(), 
					Settings.System.ACCELEROMETER_ROTATION, 0);
		}			}

	@Override
	public void updateState(Context context,
			SharedPreferences globalPreferences, int[] appWidgetIds) {
		if (getOrientationState(context)==1) {
			currentIcon=R.drawable.ic_appwidget_settings_orientation_on;
			currentState=SettingsAppWidgetProvider.STATE_ENABLED;
		} else {
			currentIcon=R.drawable.ic_appwidget_settings_orientation_off;
			currentState=SettingsAppWidgetProvider.STATE_DISABLED;
		}		
	}	

	public static int getOrientationState(Context context) {
		return Settings.System.getInt(
				context.getContentResolver(), 
				Settings.System.ACCELEROMETER_ROTATION, 0);
	}    

	public static AutoRotateButton getInstance() {
		if (ownButton==null)
			ownButton = new AutoRotateButton();

		return ownButton;

	}    

	@Override
	void initButton() {
		buttonID=WidgetButton.BUTTON_AUTO_ROTATE;
		isDefault=false;
		preferenceName=WidgetSettings.TOGGLE_AUTO_ROTATE ;

		buttonLayout=R.id.btn_auto_rotate;
		buttonSep=R.id.sep_auto_rotate;
		buttonIcon=R.id.img_auto_rotate;
		buttonState=R.id.ind_auto_rotate;

	}


}
