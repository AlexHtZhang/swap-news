package cominzhangh2.linkedin.www.swapnews.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zht on 8/4/18.
 */

// all activity shall have same method, show startActivityWithBundle.
    // abstract class partial interface, there are abstrct method inside the class. Abstract method has no body, it's supposed to be override by all the child class.
    // interface can only have abstract method in it.
public abstract class BasicActivity extends AppCompatActivity implements FragmentManager {
    protected static final String BUNDLE = "bundle";// bundle is used to transmitt the data from one activty to anthoer activity.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }
// startActivityWithBundle is the same with all activiies.
    @Override
    public void startActivityWithBundle(Class<?> clazz, boolean isFinished, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BUNDLE, bundle);
        this.startActivity(intent);
        if (isFinished) {
            finish();
        }
    }
// getLayout is not same with each activity.
    @LayoutRes
    protected abstract int getLayout(); //

}

