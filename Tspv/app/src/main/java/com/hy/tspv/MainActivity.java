package com.hy.tspv;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private Handler mHandler;
    private Tspv tspv1, tspv2, tspv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(this);
        initView();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                tspv1();
                break;
            case 2:
                tspv2();
                break;
            case 3:
                tspv3();
                break;

        }
        return false;
    }

    private void initView() {
        tspv1 = (Tspv) findViewById(R.id.tspv_1);
        tspv2 = (Tspv) findViewById(R.id.tspv_2);
        tspv3 = (Tspv) findViewById(R.id.tspv_3);

        mHandler.sendEmptyMessageDelayed(1, 2000);
        mHandler.sendEmptyMessageDelayed(2, 3000);
        mHandler.sendEmptyMessageDelayed(3, 4000);

    }


    //当前百分比
    int p1 = 0;
    //完成百分比
    int PERCENT = 30;
    //刷新速度 值越小越快
    int DELAY = 150;
    //递增速度 值越大速度越快
    int INCREASE = 1;

    /**
     * 自己控制速度变化
     */
    private void tspv1() {
        p1 += INCREASE;
        tspv1.setPercent(p1);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (p1 < PERCENT) {
                    tspv1();
                }
            }
        }, DELAY);

    }

    int p2 = 0;

    private void tspv2() {
        if (p2 + 2 > 50) {
            p2 = 50;
        } else {
            p2 += 2;
        }
        tspv2.setPercent(p1);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (p2 < 50) {
                    tspv2();
                }
            }
        }, 100);

    }

    int p3 = 0;

    private void tspv3() {
        if (p3 + 3 >= 100) {
            p3 = 100;
        } else {
            p3 += 3;
        }
        tspv3.setPercent(p3);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (p3 < 100) {
                    tspv3();
                }
            }
        }, 30);

    }

    public void continuePlay(View view) {
        p1 = 0;
        p2 = 0;
        p3 = 0;
        mHandler.sendEmptyMessageDelayed(1, 1000);
        mHandler.sendEmptyMessageDelayed(2, 2000);
        mHandler.sendEmptyMessageDelayed(3, 3000);
    }
}
