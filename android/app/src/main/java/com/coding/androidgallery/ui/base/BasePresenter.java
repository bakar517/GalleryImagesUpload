package com.coding.androidgallery.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public abstract class BasePresenter<View> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private View view;

    public BasePresenter(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void addToDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public boolean isDisposed(){return compositeDisposable.isDisposed();}

    public void destroyView(){
        compositeDisposable.dispose();
        view = null;
    }

}
