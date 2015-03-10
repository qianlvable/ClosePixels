package com.lvable.closepixels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lvable.pixelart.PixelArt;

import static com.lvable.pixelart.PixelArt.renderPixels;


public class MainActivity extends ActionBarActivity {
    private Bitmap mBitmapSource;
    EditText resEditText;
    EditText sizeEditText;
    ImageView imageView;
    Button btnChoose;
    private PixelArt.SHAPE option = PixelArt.SHAPE.SQUARE;
    private int alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);
        btnChoose = (Button)findViewById(R.id.btn_choose);
        imageView = (ImageView)findViewById(R.id.image);
        resEditText = (EditText)findViewById(R.id.et_res);
        sizeEditText = (EditText)findViewById(R.id.et_size);

        mBitmapSource = BitmapFactory.decodeResource(getResources(),R.drawable.e2);

        imageView.setImageBitmap(mBitmapSource);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resolution = resEditText.getText().toString();
                String size       = sizeEditText.getText().toString();
                if (resolution == null || resolution.isEmpty()
                        || size.isEmpty() || size == null){
                    imageView.setImageBitmap(renderPixels(
                            mBitmapSource,20,20,option));
                    Toast.makeText(getApplicationContext(),
                            "The default resolution and size are 20",Toast.LENGTH_SHORT).show();
                    return;
                }
                 int rs = Integer.parseInt(resolution);
               int vl = Integer.parseInt(size);
                imageView.setImageBitmap(renderPixels(
                        mBitmapSource,rs,vl,option));

            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });

    }



    private Bitmap getGalleryImageFromIntent(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        String picturePath = c.getString(columnIndex);
        c.close();
        return (BitmapFactory.decodeFile(picturePath));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0){
            mBitmapSource = getGalleryImageFromIntent(data);
            imageView.setImageBitmap(mBitmapSource);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.circle:
                option = PixelArt.SHAPE.CIRCLE;
                return true;
            case R.id.square:
                option = PixelArt.SHAPE.SQUARE;
                return true;
            case R.id.diamond:
                option = PixelArt.SHAPE.DIAMOND;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
