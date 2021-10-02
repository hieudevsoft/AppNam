package com.devapp.AppStack;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devapp.AppStack.databinding.ItemLayoutImageBinding;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private ArrayList<ItemImage> list = new ArrayList();

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<ItemImage> newList){
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void insertData(ItemImage item){
        list.add(item.getId(),item);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteData(int pos){
        list.remove(pos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLayoutImageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemImage item = list.get(position);
        holder.bind(item);
        holder.binding.getRoot().setOnLongClickListener(v -> {
            listener.onLongItemClickListener(item,position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface Listener{
        void onLongItemClickListener(ItemImage item,int pos);
    }
    private Listener listener;

    public void setListener(Listener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ItemLayoutImageBinding binding;
        public ViewHolder(@NonNull ItemLayoutImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(ItemImage image){
            binding.image.setImageResource(image.getImage());
        }
    }
}
