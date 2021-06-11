package com.example.covidtrackerandvaccinator.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtrackerandvaccinator.R;
import com.example.covidtrackerandvaccinator.adapter.vaccinationInfoAdapter;
import com.example.covidtrackerandvaccinator.model.vaccineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
String baseUrl="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?";
private EditText areaPINcode;
private Button forwardbtn;
ProgressBar holdProgress;
private ArrayList<vaccineModel> vaccination_centers;
private RecyclerView resultRecyclerView;
String areaPIN,avlDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main2);

        mapViews();
        onClickSetup();
    }

    private void onClickSetup() {
        forwardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdProgress.setVisibility(View.VISIBLE);
                DialogFragment dp =new PickDate();
                dp.show(getSupportFragmentManager(),"pick a date");

            }
        });
    }

    private void mapViews() {

        forwardbtn=findViewById(R.id.searchbtn);
        holdProgress=findViewById(R.id.progressbar_circular);
        areaPINcode=findViewById(R.id.searchEdit);
        resultRecyclerView=findViewById(R.id.vaccine_recyclr);
        resultRecyclerView.setHasFixedSize(true);
        vaccination_centers=new ArrayList<vaccineModel>();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar k =Calendar.getInstance();
        k.set(Calendar.YEAR,year);
        k.set(Calendar.MONTH,month);
        k.set(Calendar.DAY_OF_MONTH,dayOfMonth);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(k.getTimeZone());
        String d =dateFormat.format(k.getTime());
        setup(d);


    }

    private void setup(String d) {

        avlDate=d;
        fetchDataNow();
    }

    private void fetchDataNow() {

        vaccination_centers.clear();
        areaPIN=areaPINcode.getText().toString();
        String url_api=baseUrl + "pincode=" + areaPIN +"&date=" + avlDate;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessonArray = object.getJSONArray("sessions");
                    for (int i = 0; i < sessonArray.length(); i++) {
                        JSONObject sesObject = sessonArray.getJSONObject(i);
                        vaccineModel vModel =new vaccineModel();
                        vModel.setVaccineCenter(sesObject.getString("name"));
                        vModel.setVaccineCenterAddress(sesObject.getString("address"));
                        vModel.setVaccineTimings(sesObject.getString("from"));
                        vModel.setVaccineCenterTime(sesObject.getString("to"));
                        vModel.setVaccineName(sesObject.getString("vaccine"));

                        vModel.setVaccineCharges(sesObject.getString("fee_type"));
                        vModel.setVaccineAge(sesObject.getString("min_age_limit"));
                        vModel.setVaccineAvailable(sesObject.getString("available_capacity"));

                        vaccination_centers.add(vModel);
                    }
                    vaccinationInfoAdapter vaccinationinfoadapter =new  vaccinationInfoAdapter(getApplicationContext(), vaccination_centers);
                    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    resultRecyclerView.setAdapter(vaccinationinfoadapter);
                    holdProgress.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    holdProgress.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                holdProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }


        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}