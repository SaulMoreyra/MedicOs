package com.emergentes.medicapp.ui.Agenda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgendaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AgendaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Agenda");
    }

    public LiveData<String> getText() {
        return mText;
    }
}