package com.bsuir.qrscangenerate;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsuir.myapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Hashtable;

public class Generator extends AppCompatActivity {

    private EditText etInput;
    private Button btGenerate;
    private ImageView ivOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        etInput = findViewById(R.id.et_input);
        btGenerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = etInput.getText().toString().trim();
                if (!sText.isEmpty()) {
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {

                        Hashtable hints = new Hashtable();
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350, hints);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        ivOutput.setImageBitmap(bitmap);
//                        InputMethodManager manager = (InputMethodManager) getSystemService(
//                                Context.INPUT_METHOD_SERVICE
//                        );
//                        manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);
                    } catch (WriterException e) {
                        throw new RuntimeException(e);
                    }
                }
                etInput.setText("");
            }
        });
    }
}