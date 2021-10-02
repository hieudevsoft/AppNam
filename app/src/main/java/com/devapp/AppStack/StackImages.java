package com.devapp.AppStack;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StackImages {
    private static StackImages instance = null;
    private ArrayList<ItemImage> stackList;
    private int sizeOfStack;
    private int pointer = -1;
    private StackImages(){}
    public static StackImages getInstance(){
        if(instance==null) instance = new StackImages();
        return instance;
    }

    public void setSize(int size){
        if(stackList!=null) stackList.clear();
        stackList = new ArrayList();
        sizeOfStack = size;
    }

    private Boolean isFull(){
        return pointer==sizeOfStack-1;
    }

    private Boolean isEmpty(){
        return pointer==-1;
    }

    public Boolean push(ItemImage item){
        if(isFull()) return false;
        pointer++;
        stackList.add(item);
        return true;
    }

    public ItemImage pop(){
        if(isEmpty()) return null;
        ItemImage result = stackList.get(pointer);
        stackList.remove(pointer);
        pointer--;
        return result;
    }
    public void showStackImage(){
        for(ItemImage item : stackList){
            Log.d("TAG", "showStackImage: " + item);
        }
    }
}
