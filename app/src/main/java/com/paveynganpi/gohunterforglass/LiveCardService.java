package com.paveynganpi.gohunterforglass;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;

public class LiveCardService extends Service {

    LiveCard mLiveCard;
    RemoteViews mRemoteViews;
    private static final String TAG = "GoHunt For Glass Status";
    private static final String ACTION_STOP = "Stop";

    public LiveCardService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        if(mLiveCard != null){
            mLiveCard.unpublish();//takes us out of the timeline
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mLiveCard == null){
            mRemoteViews = new RemoteViews(getPackageName(),R.layout.status_live_card_layout);
            mLiveCard = new LiveCard(this,TAG);

            mLiveCard.setViews(mRemoteViews);

            Intent cardActionIntent = new Intent(this,LiveCardService.class);//create an intent for this service
            cardActionIntent.setAction(ACTION_STOP);
            mLiveCard.setAction(PendingIntent.getService(this, 0, cardActionIntent, 0));

            mLiveCard.publish(LiveCard.PublishMode.REVEAL);//add live card to timeline and take user to it


        }
        else {

            if(ACTION_STOP == intent.getAction()){
                stopSelf();
            }
            else{

                mLiveCard.navigate();

            }

        }
        return START_STICKY;//keeps it running
    }
}
