package com.emergentes.medicapp.ui.finanzas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinanzasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FinanzasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is finanzas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}