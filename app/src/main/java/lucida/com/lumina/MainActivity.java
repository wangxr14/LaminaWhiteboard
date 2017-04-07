package lucida.com.lumina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
//import com.microsoft.windowsazure.mobileservices.*;

public class MainActivity extends AppCompatActivity {
    Button usermode1Button,usermode2Button;
    TextView usernameView;
    int userMode = 0;//0 cannot do anything, 1 can share screen, 2 can draw

//    private MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mClient = new MobileServiceClient(
//                "https://luminaremote.azurewebsites.net",
//                this
//        );
        TodoItem item = new TodoItem();
        item.Text = "Awesome item";
//        mClient.getTable(TodoItem.class).insert(item, new TableOperationCallback<item>() {
//            public void onCompleted(TodoItem entity, Exception exception, ServiceFilterResponse response) {
//                if (exception == null) {
//                     Insert succeeded
//                } else {
                    // Insert failed
//                }
//            }
//        });
        usermode1Button =(Button) findViewById(R.id.UserMode1Button);
        usermode1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username="Lumina";
                Intent intent = new Intent(MainActivity.this, WhiteBoardActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("usermode",1);
                startActivity(intent);
            }
        });
        usermode2Button =(Button) findViewById(R.id.UserMode2Button);
        usermode2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username="Lumina";
                Intent intent = new Intent(MainActivity.this, WhiteBoardActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("usermode",2);
                startActivity(intent);
            }
        });
    }
}
