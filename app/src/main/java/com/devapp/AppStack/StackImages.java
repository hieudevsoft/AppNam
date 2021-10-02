package com.devapp.AppStack;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackImages {
    private static StackImages instance = null;
    private ArrayList<ItemImage> stackList; // Stack = List
    private int sizeOfStack; // So luong cac phan tu toi da cua stack
    private int pointer = -1; //Con tro dinh ngan xep
    private StackImages(){}
    public static StackImages getInstance(){
        if(instance==null) instance = new StackImages();
        return instance;
    }

    public ArrayList<ItemImage> getStackList() {
        return stackList;
    }

    public void setSize(int size){
        if(stackList!=null) stackList.clear();
        //tao ngan xep rong
        stackList = new ArrayList();
        sizeOfStack = size; //Tao so luong
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
        ItemImage result = stackList.remove(pointer);
        pointer--;
        return result;
    }

    public void showStackImage(){
        if(stackList.isEmpty())
            Log.d("TAG", "showStackImage: empty");
        else for(ItemImage item : stackList){
            Log.d("TAG", "showStackImage: " + item);
        }
    }
}
