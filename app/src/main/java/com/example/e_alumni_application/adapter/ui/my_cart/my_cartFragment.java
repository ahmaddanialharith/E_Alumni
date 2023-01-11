package com.example.e_alumni_application.adapter.ui.my_cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_alumni_application.R;
import com.example.e_alumni_application.adapter.MyCartAdapter;
import com.example.e_alumni_application.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class my_cartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;


    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

       db = FirebaseFirestore.getInstance();
       auth = FirebaseAuth.getInstance();
       recyclerView = root.findViewById(R.id.recyclerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       cartModelList = new ArrayList<>();
       cartAdapter = new MyCartAdapter(getActivity(),cartModelList);
       recyclerView.setAdapter(cartAdapter);

       db.collection("AddToCart").document(auth.getCurrentUser().getUid())
               .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {

                       if (task.isSuccessful()){
                           for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                               MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                               cartModelList.add(cartModel);
                               cartAdapter.notifyDataSetChanged();
                           }
                       }

                   }
               });


        return root;
    }

}
