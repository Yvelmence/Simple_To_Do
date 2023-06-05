package sg.edu.rp.c346.id22021958.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTasks;
    ArrayAdapter<String> adapter;
    ArrayList<String> tasksList;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTasks = findViewById(R.id.listViewTasks);
        spn = findViewById(R.id.spinner);

        tasksList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasksList);
        lvTasks.setAdapter(adapter);

        //Spinner
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        et.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        btnClear.setEnabled(true);
                        break;
                    case 1:
                        et.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = et.getText().toString();

                tasksList.add(newTask);

                adapter.notifyDataSetChanged();
                et.setText("");
            }
        });

        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index =Integer.parseInt(et.getText().toString());

                tasksList.remove(index);

                adapter.notifyDataSetChanged();
                et.setText("");
            }
        });

        //Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksList.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}