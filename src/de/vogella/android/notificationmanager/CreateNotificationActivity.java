package de.vogella.android.notificationmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateNotificationActivity extends Activity {

	private Button createNotificationButton;
	private Button timeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_notification);
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		String date=sdf.format(c.getTime());
		timeButton=(Button) findViewById(R.id.button2);
		int hour=c.get(Calendar.HOUR_OF_DAY);
		timeButton.setText(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
		createNotificationButton=(Button) findViewById(R.id.button1);
		
		if(hour==4 || hour==5){
			createNotificationButton.setText("It's snacks time");
			createNotification(getCurrentFocus());
		}
		
			
	}

	public void createNotification(View view){
		
		Intent intent=new Intent(this, NotificationReceiverActivity.class);
		PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
		
		Notification noti=new Notification.Builder(this)
		.setContentTitle("Come on! Snacks Time")
		.setContentText("Subject").setSmallIcon(R.drawable.ic_launcher)
		.setContentIntent(pendingIntent)
		.addAction(R.drawable.image2, "Call", pendingIntent).build();
		
		NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		noti.defaults |=Notification.DEFAULT_SOUND;
		noti.flags	|=Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, noti);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_notification, menu);
		return true;
	}

}
