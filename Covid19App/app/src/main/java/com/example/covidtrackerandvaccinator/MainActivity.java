package com.example.covidtrackerandvaccinator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidtrackerandvaccinator.api.CountryData;
import com.example.covidtrackerandvaccinator.view.MainActivity2;
import com.example.covidtrackerandvaccinator.view.PickDate;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private TextView totalActive,totalConfirm,totalTests,totalRecovered,totalDeath;
private TextView todayConfirm,todayRecoverd,todayDeath,date;
private PieChart pieChart;
private Button forwardbtn;
private List<CountryData> list;
String country="india";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
list =new ArrayList<>();
if(getIntent().getStringExtra("country") !=null)
{
    country=getIntent().getStringExtra("country");
}
        init();
TextView cName= findViewById(R.id.cName);
cName.setText(country);
        cName.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,CountryActivity.class))
                );
        ApiUtilities.getApiInterface().getCountryData().enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
list.addAll(response.body());

for (int i=0;i<list.size();i++)
{
    if(list.get(i).getCountry().equals(country))
    {
        int confirm =Integer.parseInt(list.get(i).getCases());
        int active =Integer.parseInt(list.get(i).getActive());
        int recovered =Integer.parseInt(list.get(i).getRecovered());
        int death =Integer.parseInt(list.get(i).getDeaths());

        totalConfirm.setText(NumberFormat.getInstance().format(confirm));
        totalActive.setText(NumberFormat.getInstance().format(active));
        totalRecovered.setText(NumberFormat.getInstance().format(recovered));
        totalDeath.setText(NumberFormat.getInstance().format(death));

        todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
        totalDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
        todayRecoverd.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
        totalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

         setText(list.get(i).getUpdated());

        pieChart.addPieSlice(new PieModel("Confirm",confirm, Color.parseColor("#FF6D00")));
        pieChart.addPieSlice(new PieModel("Active",active, Color.parseColor("#304FFE")));
        pieChart.addPieSlice(new PieModel("Recovered",recovered, Color.parseColor("#007A33")));
        pieChart.addPieSlice(new PieModel("Death",death, Color.parseColor("#D50000")));

pieChart.startAnimation();




    }


}
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Log.d("op1",t.getMessage());
                Toast.makeText(MainActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        forwardbtn=findViewById(R.id.checkbtn);
       forwardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }

    private void setText(String updated) {
        DateFormat format=new SimpleDateFormat("MMM dd,yyyy");
        long milliseconds = Long.parseLong(updated);
        Calendar calendar =Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        date.setText("Updated at "+format.format(calendar.getTime()));
    }

    private void init() {
        totalActive=findViewById(R.id.totalActive);
        totalConfirm=findViewById(R.id.totalConfirm);
        totalDeath=findViewById(R.id.totalDeath);
        totalRecovered=findViewById(R.id.totalRecovered);
        totalTests=findViewById(R.id.totalTests);
        todayConfirm=findViewById(R.id.todayConfirm);
        todayDeath=findViewById(R.id.todayDeath);
        todayRecoverd=findViewById(R.id.todayRecovered);
        pieChart=findViewById(R.id.piechart);
        totalTests=findViewById(R.id.totalTests);
        date=findViewById(R.id.updatedDate);

    }
}