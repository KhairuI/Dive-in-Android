package com.example.classfirebase.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.example.classfirebase.adapter.UserAdapter;
import com.example.classfirebase.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment implements UserAdapter.ClickInterface{

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ProgressBar progressBar;
    private UserAdapter adapter;

    private List<User> userList;
    private List<User> searchList;

    // firebase
    //firebase
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_Note");
    private StorageReference storageReference= FirebaseStorage.getInstance().getReference();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView= view.findViewById(R.id.listRecycleId);
        searchView= view.findViewById(R.id.searchViewId);
        progressBar= view.findViewById(R.id.listProgressId);
        adapter= new UserAdapter(this);

        userList= new ArrayList<>();
        searchList= new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setRecycle();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                progressBar.setVisibility(View.VISIBLE);
                myRef.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()){
                            String value= ds.child("search").getValue().toString();
                            if(value.equals(s)){
                                String total= String.valueOf(ds.child("images").getChildrenCount());
                                String noteId= ds.getKey().toString();
                                String name= ds.child("name").getValue().toString();
                                String description = ds.child("description").getValue().toString();
                                User user= new User(noteId,name,description,total);
                                searchList.add(user);
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                        adapter.getUserList(searchList);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private void setRecycle() {
        String currentUser= firebaseAuth.getCurrentUser().getUid();
        myRef.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    String total= String.valueOf(ds.child("images").getChildrenCount());
                    String noteId= ds.getKey().toString();
                    String name= ds.child("name").getValue().toString();
                    String description = ds.child("description").getValue().toString();
                    User user= new User(noteId,name,description,total);
                    userList.add(user);
                }
                progressBar.setVisibility(View.GONE);
                adapter.getUserList(userList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemClick(int position) {
       ListFragmentDirections.ActionListFragmentToDetailsFragment action=
               ListFragmentDirections.actionListFragmentToDetailsFragment();
       action.setNoteId(userList.get(position).getNoteId());
        NavController controller= Navigation.findNavController(getView());
        controller.navigate(action);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}