package com.example.final_group_assign_mobile_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {

    DatabaseReference ref;
    Activity activity = getActivity();

    ArrayList<Food> list;
    ArrayList<String> foodTypeList;
    ArrayList<String> imageList;
    ArrayList<String> separateFoodTypeArrayList;

    SearchView searchView;
    Button searchRecipe;
    Button searchIngredient;
    RecyclerView dataList;

//    SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("Food");

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search_view);
        searchRecipe = view.findViewById(R.id.button_search_recipe);
        searchIngredient = view.findViewById(R.id.button_search_ingredient);
        dataList = view.findViewById(R.id.recycler_view_search_fragment);

        list = new ArrayList<Food>();
        foodTypeList = new ArrayList<String>();
        imageList = new ArrayList<String>();
        separateFoodTypeArrayList = new ArrayList<String>();

        searchView.clearFocus();

//        titles = new ArrayList<>();
//        images = new ArrayList<>();
//
//        titles.add("First Item");
//        titles.add("Second Item");
//        titles.add("Third Item");
//        titles.add("Fourth Item");
//
//        images.add(R.drawable.ic_baseline_favorite_24);
//        images.add(R.drawable.ic_baseline_home_24);
//        images.add(R.drawable.ic_baseline_list_24);
//        images.add(R.drawable.ic_baseline_person_24);

//        adapter = new SearchAdapter(getContext(),titles,images);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
//        dataList.setLayoutManager(gridLayoutManager);
//        dataList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                    {
                        list = new ArrayList<>();
                        foodTypeList = new ArrayList<>();
                        imageList = new ArrayList<>();
                        separateFoodTypeArrayList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren())
                        {
                            //Food food = ds.getValue(Food.class);
                            //String imageURL = food.getImage();
                            //imageURLList.add(imageURL);
                            list.add(ds.getValue(Food.class));
                        }

                        for (int i = 0; i < list.size(); i++){

                            separateFoodTypeArrayList = new ArrayList<>(Arrays.asList(list.get(i).foodtype.split("\\n")));

                            for (int j = 0; j < separateFoodTypeArrayList.size(); j++){

                                Boolean checkRepeat = false;

                                Log.i("count: ", String.valueOf(foodTypeList.size()));

                                if (foodTypeList.isEmpty()){
                                    foodTypeList.add(separateFoodTypeArrayList.get(j));
                                    imageList.add(list.get(i).getImage());

                                    Log.i("foodType: ", separateFoodTypeArrayList.get(j));
                                }

                                for (int k = 0; k < foodTypeList.size(); k ++){
                                    if (foodTypeList.get(k).equals(separateFoodTypeArrayList.get(j))){
                                        checkRepeat = true;
                                        break;
                                    }
                                }

                                if (!checkRepeat){
                                    foodTypeList.add(separateFoodTypeArrayList.get(j));
                                    imageList.add(list.get(i).getImage());

                                    Log.i("foodType: ", separateFoodTypeArrayList.get(j));
                                }

                            }

                            Log.i("foodTypeListSize: ", String.valueOf(foodTypeList.size()));

                            separateFoodTypeArrayList.removeAll(separateFoodTypeArrayList);

                            Log.i("ListSizeAfterDeleted: ", String.valueOf(separateFoodTypeArrayList.size()));

                        }

                        SearchAdapter adapterClass = new SearchAdapter(getContext(), list, foodTypeList, imageList);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                        dataList.setLayoutManager(gridLayoutManager);
                        dataList.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        /*if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }*/
    }
}