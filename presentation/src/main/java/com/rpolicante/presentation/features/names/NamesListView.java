package com.rpolicante.presentation.features.names;

import com.rpolicante.presentation.features.detail.NameDetailView;
import com.rpolicante.presentation.model.NameModel;
import com.rpolicante.presentation.view.EmptyView;
import com.rpolicante.presentation.view.ErrorView;
import com.rpolicante.presentation.view.LoadView;

import java.util.Collection;

/**
 * Created by policante on 1/19/16.
 */
public interface NamesListView extends NameDetailView, EmptyView, LoadView, ErrorView {

    void renderNamesList(Collection<NameModel> namesModelCollection);

}
