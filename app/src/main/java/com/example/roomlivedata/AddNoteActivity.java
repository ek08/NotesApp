package com.example.roomlivedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";
    public static final String EXTRA_ID = "EXTRA_ID";
    private EditText edtTitle;
    private EditText edtDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        numberPicker = (NumberPicker) findViewById(R.id.numberPickerPriority);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            edtDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            edtTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        } else {
            setTitle("Add Note");
        }

    }

    public void saveNote() {
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        int priority = numberPicker.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please Enter a Title and Description ",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1)
        {
            intent.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}