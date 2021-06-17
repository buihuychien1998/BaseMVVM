package com.example.baseapplication.common.wrapper;

import com.example.baseapplication.common.error.BusinessError;
import com.example.baseapplication.common.error.ErrorHandler;
import com.example.baseapplication.common.error.ErrorModel;
import com.example.baseapplication.common.error.HttpError;
import com.example.baseapplication.model.base.BaseResponse;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import androidx.lifecycle.LiveData;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class CallbackWrapper<T> extends DisposableObserver<T> {
    //BaseView is just a reference of a View in MVP
    private final WeakReference<ErrorHandler> weakReference;

    public CallbackWrapper(ErrorHandler view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(@NotNull T t) {
        //You can return StatusCodes of different cases from your API and handle it here. I usually include these cases on BaseResponse and iherit it from every Response
        onSuccess(t);
    }

    @Override
    public void onError(@NotNull Throwable e) {
        ErrorHandler errorHandler = weakReference.get();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            errorHandler.onHttpError(new HttpError(getErrorMessage(responseBody)));
        } else if (e instanceof SocketTimeoutException) {
            errorHandler.onTimeout();
        } else if (e instanceof IOException) {
            errorHandler.onNetworkError();
        } else {
            errorHandler.onUnknownError(new BusinessError(e.getMessage()));
        }
    }

    @Override
    public void onComplete() {

    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}