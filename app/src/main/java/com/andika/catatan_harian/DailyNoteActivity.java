package com.andika.catatan_harian;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class DailyNoteActivity extends AppCompatActivity {
    private ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_note);
        lvNotes = findViewById(R.id.lv_notes);
        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InsertAndViewActivity.class);
                Map<String, Object> data = (Map<String, Object>) adapterView.getAdapter().getItem(i);
                intent.putExtra("filename", data.get("name").toString());
                Toast.makeText(getApplicationContext(), "You clicked" + data.get("name"), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> data = (Map<String, Object>) adapterView.getAdapter().getItem(i);
                return false;
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String judul = extras.getString(GlobalVariable.CATATAN_HARIAN);
            String nilaiBaru = extras.getString(GlobalVariable.NILAI, String.valueOf(0));
            setTitle(judul + " " + nilaiBaru);
        }
    }

    public void dialogKonfirmasiHapusCatatan(String filename) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Catatan?")
                .setMessage("Apakah Anda yakin menghapus catatan?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
    }

    public void hapusFile(String fileName) {
        String path = Environment.getExternalStorageDirectory().toString() + "/komifo.proyek1";
        File file = new File(path, fileName);
        if (file.exists())
            file.delete();
    }

    public void mengambilListFilePadaFolder() {
        String path = Environment.getExternalStorageDirectory().toString() + "kominfo.proyek1";
        File directory = new File(path);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            String[] filenames = new String[files.length];
            String[] dateCreated = new String[filenames.length];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM YYYY HH:mm:ss");
            ArrayList<Map<String, Object>> itemDataList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < files.length; i++) {
                filenames[i] = files[i].getName();
                Date lastModDate = new Date(files[i].lastModified());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case
                startActivity(new Intent(getApplicationContext(), InsertAndViewActivity.class))
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
}


}