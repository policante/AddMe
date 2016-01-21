package com.rpolicante.addme.features.names.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.rpolicante.addme.R;
import com.rpolicante.presentation.model.NameModel;

import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.dift.ui.SwipeToAction;

/**
 * Created by policante on 1/19/16.
 */
public class NamesListAdapter extends RecyclerView.Adapter<NamesListAdapter.NamesViewHolder> {

    private Context context;

    private List<NameModel> namesCollection;
    private LayoutInflater layoutInflater;

    public NamesListAdapter(Context context, Collection<NameModel> collection){
        validateObjectNotNull(collection);
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.namesCollection = (List<NameModel>) collection;

    }

    @Override
    public NamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.row_nameslist, parent, false);
        NamesViewHolder holder = new NamesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NamesViewHolder holder, int position) {
        final NameModel model = this.namesCollection.get(position);
        holder.title.setText(model.getName());
        holder.data = model;

        setAnimation(holder.itemView);
    }

    private void setAnimation(View viewToAnimate)
    {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return (this.namesCollection != null) ? this.namesCollection.size() : 0;
    }

    public void setNamesCollection(Collection<NameModel> collection) {
        validateObjectNotNull(collection);
        this.namesCollection = (List<NameModel>) collection;
        this.notifyDataSetChanged();
    }

    public void addNameModel(int position, NameModel model){
        validateObjectNotNull(model);
        this.namesCollection.add(position,model);
        this.notifyItemInserted(position);
    }

    public int removeNameModel(NameModel model){
        int position = this.namesCollection.indexOf(model);
        this.namesCollection.remove(position);
        this.notifyItemRemoved(position);

        return position;
    }

    private void validateObjectNotNull(Object object){
        if (object == null){
            throw new IllegalArgumentException("The list cannot be null!");
        }
    }

    static class NamesViewHolder extends SwipeToAction.ViewHolder<NameModel> {

        @Bind(R.id.textview_rowname_title)
        TextView title;

        public NamesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
