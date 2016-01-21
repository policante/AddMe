package com.rpolicante.presentation.features.detail;

import com.rpolicante.presentation.model.NameModel;
import com.rpolicante.presentation.view.ErrorView;
import com.rpolicante.presentation.view.LoadView;

/**
 * Created by policante on 1/21/16.
 */
public interface NameDetailView extends LoadView, ErrorView{
    void renderName(NameModel nameModel);
}
