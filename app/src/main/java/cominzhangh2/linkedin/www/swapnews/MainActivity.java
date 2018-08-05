package cominzhangh2.linkedin.www.swapnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cominzhangh2.linkedin.www.swapnews.common.BasicActivity;

public class MainActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add click listener here
        findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class); // Intent connect two activities.
                startActivity(intent);
                // finish(); will popout the main in the activity stack. destoried.
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showSnackBar(String message) {

    }
}

