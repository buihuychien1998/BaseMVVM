package com.example.baseapplication.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.baseapplication.R;
import com.example.baseapplication.common.utils.DialogUtils;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel>
    extends AppCompatActivity implements View.OnClickListener {
    protected DB viewBinding;
    protected VM viewModel;
    protected SharedViewModel sharedViewModel;

    public abstract void initViewModel();

    public abstract int getLayoutId();

    /**
     * Initialize views
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initObservers();

    /**
     * Initialize listeners
     */
    public abstract void initListeners();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initViewModel();
        initSharedViewModel();
        initViews(savedInstanceState);
        initBaseObservers();
        initObservers();
        initListeners();
    }

    private void initBaseObservers() {
        viewModel.getErrorModelLiveData().observe(this, errorModel -> {
            if (errorModel.getMessage() == null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, errorModel.getMessage(), Toast.LENGTH_SHORT).show();
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading == null) {
                return;
            }
            if (isLoading) {
                showProgressDialog(R.string.app_name);
                return;
            }
            dismissProgressDialog();
        });
    }


    private void initSharedViewModel() {
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    /**
     * This function to get ViewModel
     *
     * @param clazz
     * @return
     */
    protected VM getViewModel(Class<VM> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }

    /**
     * This function is used to show soft keyboard
     * この機能は、ソフトキーボードを表示するために使用されます
     */
    public void showKeyboard() {
        View view = getCurrentFocus();
        if (view == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public void forceShowKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * This function is used to hide soft keyboard
     * この機能は、ソフトキーボードを非表示にするために使用されます
     */
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
//            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showProgressDialog(String message){
        DialogUtils.showProgressDialog(this, message);
    }

    public void showProgressDialog(@StringRes int messageId){
        showProgressDialog(getString(messageId));
    }

    public void dismissProgressDialog(){
        DialogUtils.dismissProgressDialog();
    }
}
