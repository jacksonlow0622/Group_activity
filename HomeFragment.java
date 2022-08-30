package com.example.final_group_assign_mobile_app;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    DatabaseReference ref;
    Activity activity = getActivity();
    ArrayList<Food> list;

    SearchView searchView;
    RecyclerView recyclerView;
    ViewPager2 viewPager2;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("Food");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_home_fragment);
        searchView = view.findViewById(R.id.search_view);
        viewPager2 = view.findViewById(R.id.viewpager_home_fragment);

        list = new ArrayList<>();

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        //recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        searchView.clearFocus();

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
                        for (DataSnapshot ds : snapshot.getChildren())
                        {
                            //Food food = ds.getValue(Food.class);
                            //String imageURL = food.getImage();
                            //imageURLList.add(imageURL);
                            list.add(ds.getValue(Food.class));
                        }
                        HomeFragmentViewPagerAdapter vpAdapter = new HomeFragmentViewPagerAdapter(list);
                        viewPager2.setAdapter(vpAdapter);

                        HomeFragmentRecyclerViewAdapter adapterClass = new HomeFragmentRecyclerViewAdapter(getContext(), list);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(adapterClass);
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

    /*private void search(String str){
        ArrayList<Food> myList = new ArrayList<>();
        for (Food object : list){
            if (object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }*/


}