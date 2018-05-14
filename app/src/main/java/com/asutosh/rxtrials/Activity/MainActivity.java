package com.asutosh.rxtrials.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asutosh.rxtrials.R;

public class MainActivity extends AppCompatActivity{

    LinearLayout LL_root;
    String[] activityNames =
            {"Basic: simplest RxJava example",
             "Basic: emit & Receive Objects Array",
             "Basic: emit & Receive Objects List",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LL_root = findViewById(R.id.container);

        for(int j=0;j<activityNames.length;j++) {

            Button button = new Button(this);
            button.setText(activityNames[j]);
            LL_root.addView(button);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(2, 2, 2, 2);
            lp.gravity = Gravity.CENTER;
            button.setLayoutParams(lp);
            button.setBackgroundResource(R.color.lightIndigo);
        }

    }
}
