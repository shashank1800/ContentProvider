package com.shashankbhat.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.shashankbhat.contentprovider.Content_Provider.DatabaseContentProvider;

import static com.shashankbhat.contentprovider.Content_Provider.DatabaseContentProvider.CONTENT_URL;
import static com.shashankbhat.contentprovider.Content_Provider.DatabaseContract.COL_ID;
import static com.shashankbhat.contentprovider.Content_Provider.DatabaseContract.COL_NAME;

public class MainActivity extends AppCompatActivity {

    public DatabaseContentProvider resolver;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = new DatabaseContentProvider();

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.add);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if(!name.isEmpty()){
                    ContentValues values = new ContentValues();
                    values.put(COL_NAME,name);
                    resolver.insert(CONTENT_URL, values);

                    Toast.makeText(getBaseContext(), "New Name Added", Toast.LENGTH_LONG).show();
                    getContacts();
                }
            }
        });
        getContacts();
    }

    public void getContacts(){

        String[] projection = new String[]{COL_ID, COL_NAME};

        Cursor cursor = resolver.query(CONTENT_URL, projection, null, null, null);


        if(cursor.moveToFirst()){
            String contactList = "";
            do{
                String id = cursor.getString(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));

                contactList = contactList + id + " : " + name + "\n";
                textView.setText(contactList);
            }while (cursor.moveToNext());
        }

    }
}
