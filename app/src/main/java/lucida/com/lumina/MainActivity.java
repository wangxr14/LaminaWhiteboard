package lucida.com.lumina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button connectButton;
    TextView usernameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton =(Button) findViewById(R.id.ConnectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username="Lumina";
                Intent intent = new Intent(MainActivity.this, WhiteBoardActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}
