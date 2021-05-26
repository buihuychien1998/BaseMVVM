package com.example.baseapplication.presentation.base;

import com.example.baseapplication.common.error.ErrorModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    public final MutableLiveData<ErrorModel> errorModelLiveData = new MutableLiveData<>();
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
