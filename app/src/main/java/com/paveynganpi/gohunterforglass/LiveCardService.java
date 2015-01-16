package com.paveynganpi.gohunterforglass;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;

public class LiveCardService extends Service {

    LiveCard mLiveCard;
    RemoteViews mRemoteViews;
    private static final String TAG = "GoHunt For Glass Status";

    public LiveCardService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mLiveCard == null){
            mRemoteViews = new RemoteViews(getPackageName(),R.layout.status_live_card_layout);
            mLiveCard = new LiveCard(this,TAG);

            mLiveCard.setViews(mRemoteViews);
        }
        return START_STICKY;//keeps it running
    }
}
