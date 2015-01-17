package com.paveynganpi.gohunterforglass;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.glass.content.Intents;
import com.google.android.glass.timeline.LiveCard;


public class MenuActivity extends Activity {

    private boolean mAttachedWindow;
    private boolean mOptionsMenuOpen;
    private static final int PHOTO_REQUEST_CODE = 1;
    private boolean mTakingPhotos;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void openOptionsMenu() {
        if(!mOptionsMenuOpen && mAttachedWindow){
            super.openOptionsMenu();
        }
        mOptionsMenuOpen = true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        mOptionsMenuOpen = false;
        finish();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttachedWindow = true;
        openOptionsMenu();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttachedWindow = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean handled = true;

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_start_new_game:
                handleStartNewGame();
                break;
            case R.id.action_take_a_picture:
                handleTakeAPicture();
                break;
            case R.id.action_view_categories:
                handleViewCategories();
                break;
            case R.id.action_stop:
                handleStop();
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void handleTakeAPicture() {

        //Toast.makeText(this,"Take a picture Selected",Toast.LENGTH_LONG).show();
        mHandler.post(new Runnable() {
            @Override
            public void run() {

                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent,PHOTO_REQUEST_CODE);

            }
        });


    }

    private void handleStop() {

        Toast.makeText(this,"BYE BYE",Toast.LENGTH_LONG).show();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                stopService(new Intent(MenuActivity.this, LiveCardService.class));
            }
        });

    }

    private void handleViewCategories() {

        Toast.makeText(this,"View Categories Selected",Toast.LENGTH_LONG).show();
    }

    private void handleStartNewGame() {

        Toast.makeText(this,"Start Selected",Toast.LENGTH_LONG).show();
        CategoryManager.getInstance().InitializeWithNewCategories();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK){

            String photoFilePath=data.getStringExtra(Intents.EXTRA_PICTURE_FILE_PATH);
            Toast.makeText(this,"Photo :"+photoFilePath, Toast.LENGTH_LONG).show();
        }

     }
}
