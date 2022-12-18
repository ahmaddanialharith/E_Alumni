package com.example.e_alumni_application.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_alumni_application.R;
import com.example.e_alumni_application.adapter.NavCategoryAdapter;
import com.example.e_alumni_application.models.NavCategoryModel;

import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = root.findViewById(R.id.cat_rec);


        return root;
    }

}
