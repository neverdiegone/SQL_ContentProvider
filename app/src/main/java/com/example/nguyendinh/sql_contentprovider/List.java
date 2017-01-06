package com.example.nguyendinh.sql_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nguyendinh.sql_contentprovider.ContentProvider.ThoiTietProvider;
import com.example.nguyendinh.sql_contentprovider.Data.DataHelper;

public class List extends AppCompatActivity {
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Cursor c = getContentResolver().query(Uri.parse(ThoiTietProvider.URI),null,null,null,null);
        String[] ds = new String[c.getCount()];
        int pos = 0;
        c.moveToFirst();
        do
        {
            ds[pos] ="tu"
            +c.getString(c.getColumnIndex(DataHelper.TU_GIO))
            + c.getString(c.getColumnIndex(DataHelper.DEN_GIO))
                + "\nĐộ ẩm "
                + c.getString(c.getColumnIndex(DataHelper.DO_AM))
                + "%\nNhiệt độ thấp nhất "
                + c.getString(c.getColumnIndex(DataHelper.NHIET_DO_THAP_NHAT))
                + " độ C\nNhiệt độ cao nhất "
                    + c.getString(c.getColumnIndex(DataHelper.NHIET_DO_CAO_NHAT))
                    + " độ C";
            pos++;
        }while (c.moveToNext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,ds)
        {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };
        lst.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btndongbo) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
