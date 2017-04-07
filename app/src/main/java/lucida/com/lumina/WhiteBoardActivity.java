package lucida.com.lumina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WhiteBoardActivity extends AppCompatActivity {

    int userMode=0;
    TextView usernameView;
    LinearLayout tabLayout,contentLayout;
    DeskView m_view;

    private int m_type=1;
    private Boolean selectStatus=false;
    private int resetDrawType;

    JSONObject recJson=new JSONObject();
    PointData obj1;
    Point pointvalue1;
    private class button2ClickListenner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=8;
        }
    }

    private class clearButtonListenner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setClearArrayList();
            m_view.setDashListClear();
            m_view.invalidate();
        }
    }

    private class button1Clicklistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=7;
        }
    }

    private class button8ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=6;
        }
    }

    private class button7ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=5;
        }
    }

    private class button6ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=4;
        }
    }

    private class button5ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=3;
        }
    }

    private class button4ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=1;
        }
    }

    private class button3ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            m_view.setDashListClear();
            m_type=2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class touchListener implements View.OnTouchListener
    {
        PointData obj;
        Point pointvalue;
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            double x = event.getX();
            double y = event.getY();

            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    m_view.moveObj.Init();

                    obj=new PointData();
                    obj.leftx=x;
                    obj.lefty=y;
                    //当选中的图形做标记
                    if(!m_view.dashList.isEmpty())
                    {
                        for(int i1=0; i1<m_view.dashList.size(); i1++)
                        {
                            DashPoint objSelect = m_view.dashList.get(i1);
                            if((int)x>=(int)objSelect.left && (int)x<=(int)objSelect.right && (int)y>=(int)objSelect.top && (int)y<=(int)objSelect.bottom)
                            {
                                selectStatus=true;
                            }
                            if((int)x<=(int)objSelect.left && (int)x>=(int)objSelect.right && (int)y<=(int)objSelect.top && (int)y>=(int)objSelect.bottom)
                            {
                                selectStatus=true;
                            }
                        }
                    }
                    if(!selectStatus)
                    {
                        m_view.moveObj.leftx=x;
                        m_view.moveObj.lefty=y;
                    }
                    send_point_value(MotionEvent.ACTION_DOWN,x,y);
                    break;
                case MotionEvent.ACTION_UP:
                    obj.rightx=x;
                    obj.righty=y;
                    obj.drawType=m_type;

                    if(selectStatus)//选中
                    {
                        resetDrawType = 9;
                        obj.drawType=resetDrawType;
                        selectStatus=false;
                    }

                    m_view.isUp=false;//临时的那个画图就没了
                    m_view.setPonintCollection(obj);
                    m_view.invalidate();
                    Log.d("debug", " send here");
                    send_point_value(MotionEvent.ACTION_UP,x,y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    pointvalue.x=x;
                    pointvalue.y=y;
                    obj.point.add(pointvalue);

                    if(!selectStatus)
                    {
                        m_view.isUp=true;
                        m_view.moveObj.rightx=x;
                        m_view.moveObj.righty=y;
                        m_view.moveObj.point.add(pointvalue);
                        m_view.moveObj.drawType=m_type;
                    }
                    m_view.invalidate();
                    send_point_value(MotionEvent.ACTION_MOVE, x, y);
                    break;
            }
            pointvalue = new Point();
            pointvalue.startx=x;
            pointvalue.starty=y;

            //receive_point_value(recJson);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_board);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        userMode = intent.getIntExtra("usermode",0);

        //send the screen
        if(userMode == 1)
        {

        }
        //draw
        else if(userMode == 2) {
            tabLayout = (LinearLayout) findViewById(R.id.tab_layout);
            contentLayout = (LinearLayout) findViewById(R.id.content_layout);

            m_view = new DeskView(WhiteBoardActivity.this);
            m_view.setOnTouchListener(new touchListener());

            RadioButton button3 = (RadioButton) findViewById(R.id.radio_button3);
            button3.setOnClickListener(new button3ClickListener());
            //直线
            RadioButton button4 = (RadioButton) findViewById(R.id.radio_button4);
            button4.setOnClickListener(new button4ClickListener());
            //空心矩形
            RadioButton button5 = (RadioButton) findViewById(R.id.radio_button5);
            button5.setOnClickListener(new button5ClickListener());
            //实心矩形
//        RadioButton button6=(RadioButton)findViewById(R.id.radio_button6);
//        button6.setOnClickListener(new button6ClickListener());
            //空心圆
            RadioButton button7 = (RadioButton) findViewById(R.id.radio_button7);
            button7.setOnClickListener(new button7ClickListener());
            //实心圆
//        RadioButton button8=(RadioButton)findViewById(R.id.radio_button8);
//        button8.setOnClickListener(new button8ClickListener());
            //选中用虚线的矩形
            RadioButton button1 = (RadioButton) findViewById(R.id.radio_button1);
            button1.setOnClickListener(new button1Clicklistener());
            //选中删除功能
            RadioButton button2 = (RadioButton) findViewById(R.id.radio_button2);
            button2.setOnClickListener(new button2ClickListenner());

            //清空
            Button clear_button_top = (Button) findViewById(R.id.clear_button_top);
            clear_button_top.setOnClickListener(new clearButtonListenner());

            contentLayout.addView(m_view);
        }



    }

    // Send Json
    void send_point_value(int action,double x,double y)
    {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("selectStatus", selectStatus);
            jsonObj.put("m_type", m_type);
            jsonObj.put("x", x);
            jsonObj.put("y", y);
            jsonObj.put("action", action);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("send json",jsonObj.toString());
        recJson = jsonObj;
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                Add:selectStatus
//                break;
//            case MotionEvent.ACTION_UP:
//                Add:m_type,selectStatus,
//                JSONArray jsonArray;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Add:selectStatus,m_type
//                break;
//        }
    }

    // Receive Json
    void receive_point_value(JSONObject jsonObj)
    {
        double x=0,y=0;
        int action=0;
        try {
            selectStatus=jsonObj.getBoolean("selectStatus");
            m_type = jsonObj.getInt("m_type");
            x = jsonObj.getDouble("x");
            y = jsonObj.getDouble("y");
            action = jsonObj.getInt("action");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        x+=100;
        y+=100;
        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                m_view.moveObj.Init();

                obj1=new PointData();
                obj1.leftx=x;
                obj1.lefty=y;
                //当选中的图形做标记
                if(!m_view.dashList.isEmpty())
                {
                    for(int i1=0; i1<m_view.dashList.size(); i1++)
                    {
                        DashPoint objSelect = m_view.dashList.get(i1);
                        if((int)x>=(int)objSelect.left && (int)x<=(int)objSelect.right && (int)y>=(int)objSelect.top && (int)y<=(int)objSelect.bottom)
                        {
                            selectStatus=true;
                        }
                        if((int)x<=(int)objSelect.left && (int)x>=(int)objSelect.right && (int)y<=(int)objSelect.top && (int)y>=(int)objSelect.bottom)
                        {
                            selectStatus=true;
                        }
                    }
                }
                if(!selectStatus)
                {
                    m_view.moveObj.leftx=x;
                    m_view.moveObj.lefty=y;
                }

                break;
            case MotionEvent.ACTION_UP:
                obj1.rightx=x;
                obj1.righty=y;
                obj1.drawType=m_type;

                if(selectStatus)//选中
                {
                    resetDrawType = 9;
                    obj1.drawType=resetDrawType;
                    selectStatus=false;
                }

                m_view.isUp=false;//临时的那个画图就没了
                m_view.setPonintCollection(obj1);
                m_view.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                pointvalue1.x=x;
                pointvalue1.y=y;
                obj1.point.add(pointvalue1);

                if(!selectStatus)
                {
                    m_view.isUp=true;
                    m_view.moveObj.rightx=x;
                    m_view.moveObj.righty=y;
                    m_view.moveObj.point.add(pointvalue1);
                    m_view.moveObj.drawType=m_type;
                }
                m_view.invalidate();

                break;
        }
        pointvalue1 = new Point();
        pointvalue1.startx=x;
        pointvalue1.starty=y;
    }

}
