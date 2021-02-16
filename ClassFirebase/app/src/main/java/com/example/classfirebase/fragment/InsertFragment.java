package com.example.classfirebase.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.classfirebase.R;
import com.example.classfirebase.adapter.ImageAdapter;
import com.example.classfirebase.model.Image;
import com.example.classfirebase.model.UriImage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InsertFragment extends Fragment {

    private EditText nameEditText, descriptionEditText;
    private ImageView blankImage;
    private RecyclerView recyclerView;
    private List<Image> imageNameList;
    private List<UriImage> savedImagesUri;
    private Button saveButton, uploadButton;
    public static final int IMAGE_CODE =1;
    private int counter=0;
    private ImageAdapter adapter;

    // firebase
    private final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_Note");
    private final StorageReference storageReference= FirebaseStorage.getInstance().getReference();



    public InsertFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_insert, container, false);
        nameEditText= view.findViewById(R.id.noteNameEditTextId);
        descriptionEditText= view.findViewById(R.id.noteNameDescriptionTextId);
        saveButton = view.findViewById(R.id.saveButtonId);
        uploadButton = view.findViewById(R.id.uploadButtonId);
        blankImage= view.findViewById(R.id.blankImageId);
        recyclerView= view.findViewById(R.id.uploadRecycleId);
        imageNameList= new ArrayList<>();
        savedImagesUri= new ArrayList<>();
        adapter= new ImageAdapter(imageNameList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(adapter.getItemCount() !=0){
                    blankImage.setVisibility(View.GONE);
                }
                else {
                    blankImage.setVisibility(View.VISIBLE);
                }

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(intent,IMAGE_CODE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          String name= nameEditText.getText().toString();
          String description= descriptionEditText.getText().toString();

          if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)){
                    uploadData(name,description);

          }
          else {
              Toast.makeText(getActivity(), "Fill up all field", Toast.LENGTH_SHORT).show();
          }



            }
        });
        return view;
    }

    private void uploadData(String name, String description) {

        if(imageNameList.size() !=0){


            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Uploaded 0/"+imageNameList.size());
            progressDialog.setCanceledOnTouchOutside(false); //Remove this line if you want your user to be able to cancel upload
            progressDialog.setCancelable(false);    //Remove this line if you want your user to be able to cancel upload
            progressDialog.show();

            // data base
            String currentUser= firebaseAuth.getCurrentUser().getUid();
            String key= myRef.push().getKey();
            DatabaseReference dataPath= myRef.child(currentUser).child(key);

            Map<String,String> dataMap= new HashMap<>();
            dataMap.put("name",name);
            dataMap.put("description",description);
            dataMap.put("search",name.toLowerCase());
            dataPath.setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        StorageReference image_path = storageReference.child("Note_Image")
                                .child(currentUser).child(key);
                        for(int i=0; i<imageNameList.size();i++){

                            int finalI = i;
                            image_path.child(imageNameList.get(i).getImageName())
                                    .putFile(imageNameList.get(i).getUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    image_path.child(imageNameList.get(finalI).getImageName()).getDownloadUrl()
                                            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task) {
                                                    counter++;
                                                    progressDialog.setMessage("Uploaded "+counter+"/"+imageNameList.size());

                                                    if(task.isSuccessful()){

                                                        String url= task.getResult().toString();
                                                        String name= imageNameList.get(finalI).getImageName();
                                                        UriImage uriImage= new UriImage(name,url);
                                                        savedImagesUri.add(uriImage);
                                                    }
                                                    else {
                                                        Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                    }

                                                    if(counter== imageNameList.size()){
                                                        storeImageUrl(progressDialog,key);
                                                        counter=0;
                                                    }

                                                }
                                            });

                                }
                            });
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }
        else
        {
            Toast.makeText(getActivity(), "Select Image", Toast.LENGTH_SHORT).show();
        }


    }

    private void storeImageUrl(ProgressDialog progressDialog, String key) {
        DatabaseReference uriPath=  myRef.child(firebaseAuth.getCurrentUser().getUid())
                .child(key).child("images");

        for(int i=0;i<savedImagesUri.size();i++){
            Map<String,String> uriMap= new HashMap<>();
            uriMap.put("link",savedImagesUri.get(i).getUrl());
            uriMap.put("name",savedImagesUri.get(i).getImageName());
            uriPath.push().setValue(uriMap);

            if(i== savedImagesUri.size()-1){
                progressDialog.dismiss();
            }
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageNameList.clear();
        savedImagesUri.clear();
        getActivity();

        if(requestCode== IMAGE_CODE &&  resultCode== Activity.RESULT_OK){

            if(data.getClipData() != null){
                // multiple image
                blankImage.setVisibility(View.GONE);
                int totalImage= data.getClipData().getItemCount();
                for(int i=0; i<totalImage;i++){
                    Uri uri= data.getClipData().getItemAt(i).getUri();
                    String imageName= getFileName(uri);
                    Image image= new Image(imageName,uri);
                    imageNameList.add(image);


                }

            }
            else if(data.getData() != null){
                // single Image...
                blankImage.setVisibility(View.GONE);
                Uri uri= data.getData();
                String imageName= getFileName(uri);
                Image image= new Image(imageName,uri);
                imageNameList.add(image);
            }

        }
    }

    //get image name....
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}