package com.olx.smartlife_solutions.olx_syria;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateNewAdActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    TextInputLayout titleTIL, desTil, priceTIL, phoneTIL, nameTIL, catsTIL;
    HashMap<Integer,Boolean> checkerHM;
    Button submitBtn, previewBtn;
    ImageButton mainImgIB;
    RecyclerView selectedRV;
    LinearLayout addNewImgLL;
    List<Bitmap> adImages;
    Bitmap mainImgBitmap = null;
    SelectedImagesRVAdapter adImagesAdapter;
    HashMap<String, String> selectedCatsIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_ad);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adImages = new ArrayList<>();
        adImagesAdapter = new SelectedImagesRVAdapter(getApplicationContext(),adImages);
        selectedRV.setAdapter(adImagesAdapter);

    }
    void init()
    {
        toolbar = findViewById(R.id.toolbar);
        checkerHM = new HashMap<>();
        selectedCatsIds = new HashMap<>();

        selectedRV = findViewById(R.id.selectedImgsRV);
        selectedRV.setHasFixedSize(true);

        final GridLayoutManager selectedRVLayout = new GridLayoutManager(getApplicationContext(),2);


        selectedRV.setLayoutManager(selectedRVLayout);

        titleTIL = findViewById(R.id.titleTIL);
        checkerHM.put(titleTIL.getId(),false);
        desTil = findViewById(R.id.desTIL);
        checkerHM.put(desTil.getId(),false);
        priceTIL = findViewById(R.id.priceTIL);
        checkerHM.put(priceTIL.getId(),false);
        phoneTIL = findViewById(R.id.phoneTIL);
        checkerHM.put(phoneTIL.getId(),false);
        nameTIL = findViewById(R.id.nameTIL);
        checkerHM.put(nameTIL.getId(),false);
        catsTIL = findViewById(R.id.catsTIL);
        checkerHM.put(catsTIL.getId(),false);

        submitBtn = findViewById(R.id.submitBtn);
        previewBtn = findViewById(R.id.previewBtn);
        mainImgIB = findViewById(R.id.mainImgIB);

        addNewImgLL = findViewById(R.id.addNewImgLL);

        mainImgIB.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        addNewImgLL.setOnClickListener(this);
        previewBtn.setOnClickListener(this);
        catsTIL.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    final ChooseLocationDialog cld = new ChooseLocationDialog(CreateNewAdActivity.this);
                    cld.show();
                    cld.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            priceTIL.getEditText().requestFocus();
                            catsTIL.getEditText().setText(cld.getSelectedCatsString());
                            selectedCatsIds = cld.getSelectedCatsHM();
                        }
                    });
                }


            }
        });

        setTextWatcher(titleTIL);
        setTextWatcher(desTil);
        setTextWatcher(priceTIL);
        setTextWatcher(phoneTIL);
        setTextWatcher(nameTIL);
        setTextWatcher(catsTIL);

    }

    boolean isEveryThinkCorrect()
    {
        boolean isCorrect = false;
        for(Map.Entry<Integer,Boolean> map : checkerHM.entrySet()){
            if(!map.getValue())
            {
                isCorrect = false;
                break;
            }
            isCorrect = true;
        }
        return isCorrect;
    }

    void setTextWatcher(final TextInputLayout til)
    {
        til.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String txt = editable.toString();
                int len = txt.length();
                switch (til.getId())
                {
                    case R.id.titleTIL:
                        CheckTextValue(til, len, 5, 70);
                        break;
                    case R.id.desTIL:
                        CheckTextValue(til, len, 10, 2000);
                        break;
                    case R.id.priceTIL:
                        CheckTextValue(til, len, 1, 15);
                        break;
                    case R.id.phoneTIL:
                        CheckTextValue(til, len, 4, 20);
                        break;
                    case R.id.nameTIL:
                        CheckTextValue(til, len, 3, 20);
                        break;
                    case R.id.catsTIL:
                        CheckTextValue(til, len, 1, 2000);
                        break;

                }

            }
        });
    }

    void CheckTextValue(TextInputLayout til, int len, int minLen, int maxLen)
    {
        if(len < minLen)
        {
            if(len == 0) til.setError(null);
            else{
                setRed(til);
                til.setError(len + "/" + maxLen);
                checkerHM.put(til.getId(),false);
            }
        }
        else{
            setGreen(til);
            til.setError(len + "/" + maxLen);
            checkerHM.put(til.getId(),true);
        }
    }

    void setGreen(TextInputLayout til)
    {
        getTickImage(til).setImageResource(R.mipmap.tick_icon);
        til.setErrorTextAppearance(R.style.TextInputLayoutErrorGreen);
    }

    void setRed(TextInputLayout til)
    {
        getTickImage(til).setImageResource(R.mipmap.tick_icon_grey);
        til.setErrorTextAppearance(R.style.TextInputLayoutErrorRed);
    }

    ImageView getTickImage(TextInputLayout til)
    {

        ImageView img = null;
        LinearLayout parent = (LinearLayout) til.getParent();
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++)
        {
            View v = parent.getChildAt(i);
            if(v instanceof ImageView)
            {
                img = (ImageView) v;
                break;
            }
        }
        return img;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK && data != null)
        {
            if (requestCode == 55) {

                Uri path = data.getData();

                try {
                    mainImgBitmap = getResizedBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(),path),800);
                    mainImgIB.setImageBitmap(mainImgBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(requestCode == 44)
            {

                Bitmap bitmap;
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                        if(data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();
                            int currentItem = 0;
                            while(currentItem < count) {
                                Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                                adImages.add(getResizedBitmap(bitmap,800));
                                currentItem++;
                            }
                            msg("clip");
                        } else if(data.getData() != null) {
                            Uri path = data.getData();
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                            adImages.add(getResizedBitmap(bitmap,800));
                            msg("data");
                        }

                    }
                    else{
                        Uri path = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                        adImages.add(getResizedBitmap(bitmap,800));
                    }



                    adImagesAdapter.notifyDataSetChanged();

                } catch (IOException e) {
                    e.printStackTrace();
                    msg(e.getMessage()+"");
                }
            }
        }


    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.submitBtn:

                if(isEveryThinkCorrect())
                {
                    if(mainImgBitmap != null)
                    {
                        if(adImages.size() != 0)
                        {
                            /////////////
                        }
                        else{
                            msg("Please Add at least one image");
                        }
                    }
                    else{
                        msg("Please Add The Main Image");
                    }
                }
                else{
                    msg("Please fill all required fields");
                }
                break;
            case R.id.mainImgIB:
                Intent selectImgIntent = new Intent();
                selectImgIntent.setType("image/*");
                selectImgIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(selectImgIntent,55);
                break;

            case R.id.addNewImgLL:
                Intent imgsIntent = new Intent();
                imgsIntent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    imgsIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                    imgsIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(imgsIntent,"Select Pictures"),44);
                    msg("top");
                }
                else{
                    imgsIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(imgsIntent,44);
                    msg("down");
                }

                break;
            case R.id.previewBtn:

                break;
        }
    }

    void msg(String txt)
    {
        Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();
    }
}
