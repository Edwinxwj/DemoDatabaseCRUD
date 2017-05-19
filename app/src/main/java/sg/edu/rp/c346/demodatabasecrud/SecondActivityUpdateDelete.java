package sg.edu.rp.c346.demodatabasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivityUpdateDelete extends AppCompatActivity {
    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;

    //Get data from the note class
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_update_delete);

        etContent = (EditText) findViewById(R.id.etContent);
        btnUpdate =(Button) findViewById(R.id.btnUpdate);
        btnDelete =(Button) findViewById(R.id.btnDelete);
        tvID = (TextView) findViewById(R.id.tvID);

        //Intent to class note page
        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivityUpdateDelete.this);
                data.setNoteContent(etContent.getText().toString());
                dbh.updateNote(data);
                dbh.close();

                //To be us back to the main activity
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivityUpdateDelete.this);
                dbh.deleteNote(data.getId());
                dbh.close();

                //To be us back to the main activity
                setResult(RESULT_OK);
                finish();
            }
        });



    }





}
