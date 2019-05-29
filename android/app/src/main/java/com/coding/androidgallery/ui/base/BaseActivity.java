package com.coding.androidgallery.ui.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P presenter;

    @Override
    protected void onDestroy() {
        if(presenter != null){
            presenter.destroyView();
        }
        super.onDestroy();
    }
}
