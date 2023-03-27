package com.bsuir.qrscangenerate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bsuir.myapplication.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private Button btn_scan;
    private Button btn_generator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_scan = findViewById(R.id.btn_scan);
        btn_generator = findViewById(R.id.btn_generator);

        btn_scan.setOnClickListener(v->
        {
            scanCode();
        });

        btn_generator.setOnClickListener(v->
        {
            Intent intent = new Intent(MainActivity.this, Generator.class);
            startActivity(intent);
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash  on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    //Для регистрации функции, которая будет обрабатывать результат,
    // Activity Result API предоставляет метод registerForActivityResult().
    //Этот метод в качестве параметров принимает объекты ActivityResultContract и ActivityResultCallback
    // и возвращает объект ActivityResultLauncher, который применяется для запуска другой activity.

    //Контракт — это класс, реализующий интерфейс ActivityResultContract<I,O>. Где I определяет
    // тип входных данных, необходимых для запуска Activity, а O — тип возвращаемого результата.
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result ->
    {
       if (result.getContents() != null) {
           //save to phone buffer
           ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
           ClipData clip = ClipData.newPlainText("", result.getContents().toString());
           clipboard.setPrimaryClip(clip);

           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("Result");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
               }
           }).show();
       }
    });
}