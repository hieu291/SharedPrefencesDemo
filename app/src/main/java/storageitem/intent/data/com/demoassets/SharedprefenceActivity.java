package storageitem.intent.data.com.demoassets;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class SharedprefenceActivity extends AppCompatActivity {
    Button btnSave;
    String tenSharePrefence = "trang thai";
    Button btnRead;
    TextView tvShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedprefence);
        addControl();
        addEvent();

    }

    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(tenSharePrefence,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("b",true);
                editor.putFloat("c",1.1f);
                editor.putInt("d",1);
                editor.putLong("f",1234);
                editor.putString("g","123456Olalala");
                Set<String>array= new HashSet<>();
                array.add("Meo");
                array.add("Cho");
                array.add("Lon");
                array.add("ga");
                editor.putStringSet("Danh Sach Chuoi",array);
                editor.apply();//xac nhan luu
                Toast.makeText(SharedprefenceActivity.this,"Trang thai da duoc luu",Toast.LENGTH_SHORT).show();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(tenSharePrefence,MODE_PRIVATE);
                boolean b = preferences.getBoolean("b",false);
                float c = preferences.getFloat("f",0.0f);
                int d = preferences.getInt("d",0);
                long f = preferences.getLong("f", 01);
                String g = preferences.getString("g","");
                Set<String> array = preferences.getStringSet("Danh Sach Chuoi",null);
                tvShared.append("b="+b+"\n");
                tvShared.append("c="+c+"\n");
                tvShared.append("d="+d+"\n");
                tvShared.append("f="+f+"\n");
                tvShared.append("g="+g+"\n");
                StringBuilder builder = new StringBuilder();
                for (String x : array){
                    builder.append(x+"\n");
                }
                tvShared.append("Danh sach: \n");
                tvShared.append(builder.toString());




            }
        });
    }

    private void addControl() {
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        tvShared = findViewById(R.id.tvShared);
    }
}
