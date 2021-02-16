package com.example.classfirebase.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.example.classfirebase.adapter.GridImageAdapter;
import com.example.classfirebase.utils.UniversalImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment {

    private TextView detailsName, detailsDescription;
    private GridView gridView;
    private ImageView delete,edit;
    private String uId= null;

    private List<String> imageList;
    private List<String> imageNameList;
    int c=0;



    //firebase
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_Note");
    private StorageReference storageReference= FirebaseStorage.getInstance().getReference();


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_details, container, false);

        detailsName= view.findViewById(R.id.detailsNameId);
        detailsDescription= view.findViewById(R.id.detailsDescriptionId);
        gridView= view.findViewById(R.id.gridViewId);
        delete= view.findViewById(R.id.deleteImageId);
        edit= view.findViewById(R.id.editImageId);
        imageList = new ArrayList<>();
        imageNameList = new ArrayList<>();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentUser= firebaseAuth.getCurrentUser().getUid();
                StorageReference deletePath= storageReference.child("Note_Image").child(currentUser).child(uId);
                for(int i=0;i<imageNameList.size();i++){
                    deletePath.child(imageNameList.get(i)).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            c++;
                            if(task.isSuccessful()){

                                if(c==imageNameList.size()){
                                    myRef.child(currentUser).child(uId).removeValue();
                                    Toast.makeText(getActivity(), "Delete Complete", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {
                                Toast.makeText(getActivity(), ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiImageLoader();

        if(getArguments() != null){

            DetailsFragmentArgs args= DetailsFragmentArgs.fromBundle(getArguments());
            uId= args.getNoteId();
            imageList.clear();
            imageNameList.clear();
            String currentUser= firebaseAuth.getCurrentUser().getUid();
            myRef.child(currentUser).child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String name= snapshot.child("name").getValue().toString();
                    String description= snapshot.child("description").getValue().toString();
                    detailsName.setText(name);
                    detailsDescription.setText(description);

                    for(DataSnapshot ds: snapshot.child("images").getChildren()){
                        String link= ds.child("link").getValue().toString();
                        String imageName= ds.child("name").getValue().toString();
                        imageList.add(link);
                        imageNameList.add(imageName);
                    }
                    setGridImage(imageList);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                }
            });




        }
    }

    private void setGridImage(List<String> imageList) {

        int gridWidth= getResources().getDisplayMetrics().widthPixels;
        int imageWidth= gridWidth/2;
        gridView.setColumnWidth(imageWidth);
        GridImageAdapter adapter = new GridImageAdapter(getActivity(),R.layout.grid_single_item,"",imageList);
        gridView.setAdapter(adapter);


    }

    private void intiImageLoader() {

        UniversalImageLoader universalImageLoader= new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfiguration());
    }


}