package com.emergentes.medicapp.ui.medico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MedicoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Información de Perfil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}