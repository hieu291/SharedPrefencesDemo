package storageitem.intent.data.com.demoassets;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvFont;
    ListView lvFont;
    ArrayAdapter<String> fontadapter;
    Button btnSharedprefence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addevent();
    }

    private void addevent() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                action(i);
            }
        });
        btnSharedprefence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SharedprefenceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void action(int i) {
        String fontName = fontadapter.getItem(i);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/"+fontName);
        tvFont.setTypeface(typeface);
        actionMusic();

    }

    private void actionMusic() {
        try {
            AssetFileDescriptor afd = getAssets().openFd("music/hocmeokeu.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
            
        }catch (Exception ex)
        {
            Log.e("Loi gi day",ex.toString());
        }
    }

    private void addControl() {
        tvFont = findViewById(R.id.tvFont);
        lvFont = findViewById(R.id.lvFont);

        btnSharedprefence = findViewById(R.id.btnSharedprefence);

        fontadapter  = new ArrayAdapter<>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item);
        lvFont.setAdapter(fontadapter);
        try{
            AssetManager assetmanager = getAssets();
            String [] arrFont = assetmanager.list("fonts");
            fontadapter.addAll(arrFont);

        }catch (Exception ex){
            Log.e("Loi",ex.toString());
        }

    }
}
