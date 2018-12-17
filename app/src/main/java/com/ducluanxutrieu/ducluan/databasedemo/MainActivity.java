package com.ducluanxutrieu.ducluan.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Khởi tạo SQLite
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("User", MODE_PRIVATE, null);

            //Tạo databate nếu như chưa tồn tại với 2 cột là name và tuổi với tối đa 3 ký tự
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newuser (name VARCHAR, age INT(3), id INTERGER PRIMARY KEY)");

            //Đưa dữ liệu vào database với các hàng và cột ở trên
            sqLiteDatabase.execSQL("INSERT INTO newuser (name, age) VALUES ('Tuyet', 20)");
            sqLiteDatabase.execSQL("INSERT INTO newuser (name, age) VALUES ('Luan', 22)");
            sqLiteDatabase.execSQL("INSERT INTO newuser (name, age) VALUES ('Temp', 30)");

            //Lấy toàn bộ dữ liệu đưa vào con trỏ
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM newuser", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            cursor.moveToFirst();

            while (cursor != null) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", cursor.getString(ageIndex));
                Log.i("id", cursor.getString(idIndex));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
