package com.devapp.AppStack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devapp.AppStack.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<ItemImage> list= new ArrayList();
    private ImageAdapter adapter;
    private ImageAdapter adapterStack;
    private StackImages stackImages = StackImages.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list.add(new ItemImage(0, R.drawable.img));
        list.add(new ItemImage(1, R.drawable.img_1));
        list.add(new ItemImage(2, R.drawable.img_2));
        list.add(new ItemImage(3, R.drawable.img_3));
        list.add(new ItemImage(4, R.drawable.img_4));
        list.add(new ItemImage(5, R.drawable.img_5));
        stackImages.setSize(list.size());
        setupRecyclerView();
        setupRecyclerViewStack();
        binding.cardUndo.setOnClickListener(v -> {
            ItemImage data = stackImages.pop();
            if(data!=null){
                Log.d("TAG", "sau khi pop thanh cong");
                stackImages.showStackImage();
                adapter.insertData(data);
                adapterStack.setData(stackImages.getStackList());
            }else{
                Snackbar.make(binding.getRoot(),"No Image Deleted ~",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        stackImages.showStackImage();
        super.onResume();
    }

    private void setupRecyclerViewStack(){
        adapterStack = new ImageAdapter();
        adapterStack.setData(stackImages.getStackList());
        binding.recyclerViewStack.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerViewStack.setAdapter(adapterStack);
    }

    private void setupRecyclerView() {
        adapter = new ImageAdapter();
        adapter.setData(list);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((item,pos) -> {
            item.setId(pos);
          if(stackImages.push(item)){
              Log.d("TAG", "setupRecyclerView: Sau khi push thanh cong");
              stackImages.showStackImage();
              adapterStack.setData(stackImages.getStackList());
              adapter.deleteData(item.getId());
              Snackbar.make(binding.getRoot(),"Successfully deleted ~",Snackbar.LENGTH_LONG).show();
          }else{
              Snackbar.make(binding.getRoot(),"Fail deleted ~",Snackbar.LENGTH_LONG).show();
          }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}