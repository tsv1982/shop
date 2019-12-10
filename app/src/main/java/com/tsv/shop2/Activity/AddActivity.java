package com.tsv.shop2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsv.shop2.R;
import com.tsv.shop2.Util.Util;
import com.tsv.shop2.dagger.DaggerApp;
import com.tsv.shop2.entity.ShopEntity1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.editName)
    EditText editTextName;
    @BindView(R.id.editSize)
    EditText editTexSize;
    @BindView(R.id.editPrice)
    EditText editTexPrice;
    @BindView(R.id.imageTov)
    ImageView imageViewTov;
    String urlPicture;

    @Inject
    Util util;

    private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    private static final String WRITE_STROGATE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String READ_STROGATE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;

    private String ss[] = {CAMERA_PERMISSION, WRITE_STROGATE_PERMISSION, READ_STROGATE_PERMISSION};

    private final int REQUEST_CAMERA = 100;
    private final int REQUEST_CODE_GAL = 101;
    private final int REQUEST_CODE_PHOTO = 102;

    @OnClick({R.id.openGal, R.id.btnADD, R.id.openCam})
    void onSaveClick(View view) {
        switch (view.getId()) {
            case R.id.openGal:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1, REQUEST_CODE_GAL);
                break;
            case R.id.openCam:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
                break;
            case R.id.btnADD:
                util.addShop(new ShopEntity1(String.valueOf(editTextName.getText()),
                        String.valueOf(editTexSize.getText()),
                        Double.parseDouble(String.valueOf(editTexPrice.getText())),
                        urlPicture, false, new Random().nextInt(1000)), this);
                Intent intent5 = new Intent(this, MainActivity.class);
                startActivity(intent5);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case REQUEST_CODE_GAL:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                    imageViewTov.setImageBitmap(bitmap);
                    saveFoto(bitmap);
                }
                break;
            case REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Object obj = imageReturnedIntent.getExtras().get("data");
                    Bitmap bitmap = (Bitmap) obj;
                    imageViewTov.setImageBitmap(bitmap);
                    saveFoto(bitmap);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        DaggerApp.getComponent().injects(this);

        if (!isPermissionGranted(CAMERA_PERMISSION)) {
            requestPermission(CAMERA_PERMISSION, REQUEST_CAMERA);
        }

        if (!isPermissionGranted(WRITE_STROGATE_PERMISSION)) {
            requestPermission(WRITE_STROGATE_PERMISSION, 104);
        }

        if (!isPermissionGranted(READ_STROGATE_PERMISSION)) {
            requestPermission(READ_STROGATE_PERMISSION, 105);
        }

    }

    void saveFoto(Bitmap bitmap) {
        File directory = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ShopTansey");
        if (!directory.exists())
            directory.mkdirs();

        File photoFile = new File(directory.getPath(), System.currentTimeMillis() + ".jpg");
        urlPicture = photoFile.getPath();
        try (FileOutputStream out = new FileOutputStream(photoFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isPermissionGranted(String permission) {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Разрешения получены", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Разрешения не получены", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

}
