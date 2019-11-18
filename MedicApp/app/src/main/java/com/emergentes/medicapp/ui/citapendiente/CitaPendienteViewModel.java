package com.emergentes.medicapp.ui.citapendiente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitaPendienteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CitaPendienteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is citapendiente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}