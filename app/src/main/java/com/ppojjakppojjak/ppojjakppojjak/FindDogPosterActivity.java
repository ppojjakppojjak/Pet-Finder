package com.ppojjakppojjak.ppojjakppojjak;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.ppojjakppojjak.ppojjakppojjak.R.layout.activity_find_dog_poster;

public class FindDogPosterActivity extends AppCompatActivity {

    public static TextView tvLostDay;
    public static TextView tvShare;
    List<UploadImage> items = new ArrayList<>();
    UploadImage[] item = new UploadImage[2];
    ImageButton ibtnUploadImage1;
    ImageButton ibtnUploadImage2;
    ImageButton ibtnBack;
    int imageCount = 0;

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            tvLostDay.setText(year + "-" + (month + 1) + "-" + day);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_find_dog_poster);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ibtnBack = findViewById(R.id.ibtn_back);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvLostDay = findViewById(R.id.tv_lost_day);
        tvLostDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        tvShare = findViewById(R.id.tv_share);
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(FindDogPosterActivity.this, "포스터 변환 완료!", Toast.LENGTH_SHORT).show();
                        share();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(FindDogPosterActivity.this, "포스터 변환 실패\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(FindDogPosterActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .check();

            }
        });

        ibtnUploadImage1 = (ImageButton) findViewById(R.id.ibtn_upload_1);
        ibtnUploadImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        ibtnUploadImage2 = (ImageButton) findViewById(R.id.ibtn_upload_2);
        ibtnUploadImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void share() {
        View container = findViewById(R.id.scroll_view);
        container.buildDrawingCache();
        Bitmap captureView = container.getDrawingCache();
        String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/capture.jpeg";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(address);
            captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(new File(address));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "공유"));
    }

    public void showDatePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                item[imageCount] = new UploadImage(image);
                items.add(item[imageCount]);
                imageCount++;

                if(imageCount % 2 != 0) {
                    ibtnUploadImage1.setScaleType(ImageButton.ScaleType.FIT_XY);
                    ibtnUploadImage1.setImageBitmap(image);
                } else {
                    ibtnUploadImage2.setScaleType(ImageButton.ScaleType.FIT_XY);
                    ibtnUploadImage2.setImageBitmap(image);
                    imageCount = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


