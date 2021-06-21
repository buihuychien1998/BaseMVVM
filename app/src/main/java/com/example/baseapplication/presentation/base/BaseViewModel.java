package com.example.baseapplication.presentation.base;

import com.example.baseapplication.common.error.ErrorHandler;
import com.example.baseapplication.common.error.ErrorModel;
import com.example.baseapplication.common.error.NetworkError;
import com.example.baseapplication.common.error.TimeOutError;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private final MutableLiveData<ErrorModel> errorModelLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected final ErrorHandler errorHandler = new ErrorHandler() {
        @Override
        public void onUnknownError(ErrorModel errorModel) {
            errorModelLiveData.postValue(errorModel);
        }

        @Override
        public void onTimeout() {
            errorModelLiveData.postValue(new TimeOutError());
        }

        @Override
        public void onNetworkError() {
            errorModelLiveData.postValue(new NetworkError());
        }

        @Override
        public void onHttpError(ErrorModel errorModel) {
            errorModelLiveData.postValue(errorModel);
        }
    };

    public MutableLiveData<ErrorModel> getErrorModelLiveData() {
        return errorModelLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void postLoading(boolean loading) {
        isLoading.postValue(loading);
    }

    public void setLoading(boolean loading) {
        isLoading.setValue(loading);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
