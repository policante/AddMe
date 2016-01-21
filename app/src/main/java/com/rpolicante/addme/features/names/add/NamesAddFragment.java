package com.rpolicante.addme.features.names.add;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.rpolicante.addme.R;
import com.rpolicante.presentation.model.NameModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by policante on 1/21/16.
 */
public class NamesAddFragment extends DialogFragment {

    private OnAddNameListener listener;

    @Bind(R.id.textinput_namesadd_title)
    TextInputLayout add_title;

    @Bind(R.id.textinput_namesadd_description)
    TextInputLayout add_description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            listener = (OnAddNameListener) getTargetFragment();
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }catch (ClassCastException e){
            throw new ClassCastException(getParentFragment().toString() + " must implement OnAddNameListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_names_add, null, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public int getTheme() {
        return R.style.AppDialogTheme;
    }

    @OnClick(R.id.button_save)
    void onSave(){
        if (validate()){
            hideKeyboard();

            NameModel model = new NameModel();
            model.setName(add_title.getEditText().getText().toString());
            model.setDescription(add_description.getEditText().getText().toString());
            listener.onAddName(model);

            dismiss();
        }
    }

    void hideKeyboard(){
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.button_cancel)
    void onCancel(){
        dismiss();
    }

    private boolean validate(){
        boolean result = true;

        add_title.setErrorEnabled(false);

        if (add_title.getEditText().getText().toString().trim().isEmpty()){
            add_title.setErrorEnabled(true);
            add_title.setError(getString(R.string.required));
            result = false;
        }

        return result;
    }

    public interface OnAddNameListener {
        void onAddName(NameModel model);
    }

}
