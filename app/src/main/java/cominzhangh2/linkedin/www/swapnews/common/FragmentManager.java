package cominzhangh2.linkedin.www.swapnews.common;

import android.os.Bundle;

/**
 * Created by zht on 8/4/18.
 */

public interface FragmentManager {

//    void doFragmentTransaction(TinBasicFragment basicFragment);

    void startActivityWithBundle(Class<?> clazz, boolean isFinished, Bundle bundle);

    void showSnackBar(String message);
}
