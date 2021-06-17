package com.example.baseapplication.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<DB extends ViewDataBinding, VM extends ViewModel>
    extends AppCompatActivity implements View.OnClickListener{
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
        initObservers();
        initListeners();
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
        } else {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
