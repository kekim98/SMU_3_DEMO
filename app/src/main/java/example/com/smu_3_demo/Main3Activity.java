package example.com.smu_3_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView nameText = (TextView) findViewById(R.id.name);
        TextView codeText = (TextView) findViewById(R.id.code);



        Intent recvIntent = getIntent();
        Bundle extras = recvIntent.getExtras();
        String name = extras.getString("name", "UNKNOWN");
        String code = extras.getString("code", "UNKNOWN");



        nameText.setText(name);
        codeText.setText(code);

    }
}
