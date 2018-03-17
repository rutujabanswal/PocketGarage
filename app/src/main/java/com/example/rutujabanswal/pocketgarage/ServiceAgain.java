package com.example.rutujabanswal.pocketgarage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceAgain extends AppCompatActivity {
    private EditText vechical_number,vechical_model,notes;
    private Spinner vechical_manufacturer,service_type;
    private Button next;
    TextView manufacturer,service;
    ArrayAdapter<CharSequence> adapter1,adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_again);

        vechical_number = (EditText)findViewById(R.id.vechical_number);
        vechical_model = (EditText)findViewById(R.id.vechical_model);
        notes=(EditText)findViewById(R.id.notes);
        vechical_manufacturer =(Spinner)findViewById(R.id.vechical_manufacturer);
        service_type=(Spinner)findViewById(R.id.service_type);
        next=(Button)findViewById(R.id.btnNext);
        manufacturer = (TextView)findViewById(R.id.spinner_manufacturer);
        service=(TextView)findViewById(R.id.spinner_service);

        adapter1 = ArrayAdapter.createFromResource(this,R.array.manufacturer_array,R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vechical_manufacturer.setAdapter(adapter1);
        vechical_manufacturer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2= ArrayAdapter.createFromResource(this,R.array.service_type_array,R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        service_type.setAdapter(adapter2);
        service_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vechical_number.getText().toString().length()==0)
                    vechical_number.setError("Vechical Number is Required");

                if(vechical_model.getText().toString().length() == 0)
                    vechical_model.setError("Vechical Model is Required");

                if(vechical_manufacturer.getId() < 0)
                    manufacturer.setError("Vechical Manufacturer is Required");


            }
        });
    }
}
