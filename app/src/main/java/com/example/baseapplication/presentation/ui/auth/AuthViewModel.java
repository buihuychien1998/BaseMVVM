package com.example.baseapplication.presentation.ui.auth;

import android.util.Log;

import com.example.baseapplication.common.error.ErrorHandler;
import com.example.baseapplication.common.utils.CallbackWrapper;
import com.example.baseapplication.model.User;
import com.example.baseapplication.presentation.base.BaseViewModel;
import com.example.baseapplication.repository.remote.AuthDataSource;
import com.example.baseapplication.repository.remote.AuthRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
class AuthViewModel extends BaseViewModel {
    private final AuthDataSource authDataSource;
    private final MutableLiveData<User> loginResult = new MutableLiveData<>();
    private final LiveData<User> userLiveData = loginResult;

    @Inject
    public AuthViewModel(AuthRepository authDataSource) {
        this.authDataSource = authDataSource;
    }

    public void login(String username, String password) {
        authDataSource.login(username, password)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(compositeDisposable::add)
            .subscribeWith(new CallbackWrapper<User>(errorHandler) {

                @Override
                protected void onSuccess(User user) {

                }
            });
    }

    public void getUserList() {
        authDataSource.getUserList()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(compositeDisposable::add)
            .subscribeWith(new CallbackWrapper<List<User>>(errorHandler) {

                @Override
                protected void onSuccess(List<User> userList) {
                    Log.d("TAG", "onSuccess: "+userList.size());
                }
            });
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
