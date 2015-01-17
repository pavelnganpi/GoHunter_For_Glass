package com.paveynganpi.gohunterforglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;


public class CategoryScrollActivity extends Activity implements GestureDetector.BaseListener{

    public static final String EXTRA_PHOTO_FILE_NAME = "photo file name";
    String mNewPhotoFileName ;
    GestureDetector mDetector;
    CardScrollView mCardScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDetector = new GestureDetector(this);
        mDetector.setBaseListener(this);

        setUpCardScrollView();

        Intent startUpIntent = getIntent();
        mNewPhotoFileName = getIntent().getStringExtra(EXTRA_PHOTO_FILE_NAME);

    }

    public void setUpCardScrollView(){

        mCardScrollView = new CardScrollView(this);
        mCardScrollView.setHorizontalScrollBarEnabled(true);//enables horizontal scrolling

        //feed data into this card scroll view
        CardScrollAdapter adapter = new CategoryScrollAdapter(this,CategoryManager.getInstance());
        mCardScrollView.setAdapter(adapter);

        setContentView(mCardScrollView);//content of this activity is this mCardScrollView


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_scroll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //means any detection from glass should be passed to the mDetector for handling
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mDetector.onMotionEvent(event);
    }

    @Override
    public boolean onGesture(Gesture gesture) {
        return false;
    }
}
