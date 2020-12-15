package a.event_handing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private TextView text3;
    private GestureDetectorCompat detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text3 = findViewById(R.id.textView3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Button pressMe = (Button) findViewById(R.id.button);
        TextView text1 = findViewById(R.id.textView);
        TextView text2 = findViewById(R.id.textView2);
        switch (id) {
            case R.id.button_click:
                setTitle("Button Click");
                pressMe.setVisibility(View.VISIBLE);
                text1.setVisibility(View.VISIBLE);
                text1.setText("Hello world!");
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                pressMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text1.setText("Button clicked!");
                    }
                });

                pressMe.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        text1.setText("Long button clicked!");
                        return true;
                    }
                });
                return  true;

            case R.id.motion_event:
                setTitle("Motion Event");
                pressMe.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                text1.setText("On motion event");
                text2.setText("On motion event");
                text3.setVisibility(View.INVISIBLE);
                ConstraintLayout cl = findViewById(R.id.activity_main);
                cl.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        handleTouch(event);
                        return true;
                    }
                });
                return true;

            case R.id.common_gestures:
                setTitle("Common gestures");
                pressMe.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.VISIBLE);
                text3.setText("On common gesture");
                ConstraintLayout layout = findViewById(R.id.activity_main);
                layout.setOnTouchListener(View::onTouchEvent);
                detector = new GestureDetectorCompat(this,this);
                detector.setOnDoubleTapListener(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void handleTouch(MotionEvent m){
        TextView touchView = findViewById(R.id.textView);
        TextView touchView2 = findViewById(R.id.textView2);
        int pointerCount = m.getPointerCount();
        int x,y,id,action,actionIndex;
        String actionString, TouchStatus;
        for(int i = 0; i < pointerCount; i++){
            x = (int) m.getX(i);
            y = (int) m.getY(i);
            id = m.getPointerId(i);
            action = m.getActionMasked();
            actionIndex = m.getActionIndex();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }
            TouchStatus = "Action: " + actionString + " Index: " + actionIndex + " ID: " + id +
                    " X: " + x + " Y: " + y;
            if(id == 0)
                touchView.setText(TouchStatus);
            else
                touchView2.setText(TouchStatus);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        text3.setText("Single tap");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        text3.setText("Double tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        text3.setText("Double tap event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        text3.setText("On Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        text3.setText("On Show press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        text3.setText("Single tap up");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        text3.setText("On scroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        text3.setText("Long press");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        text3.setText("On fling");
        return true;
    }
}