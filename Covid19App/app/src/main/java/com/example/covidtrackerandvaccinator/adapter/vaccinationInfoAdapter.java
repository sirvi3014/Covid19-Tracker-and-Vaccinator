package com.example.covidtrackerandvaccinator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtrackerandvaccinator.R;
import com.example.covidtrackerandvaccinator.model.vaccineModel;

import java.util.List;

public class vaccinationInfoAdapter extends RecyclerView.Adapter<vaccinationInfoAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<vaccineModel> list_vaccine_center;

    public vaccinationInfoAdapter(Context mcontext, List<vaccineModel> list_vaccine_center) {
        this.layoutInflater = LayoutInflater.from(mcontext);
        this.list_vaccine_center = list_vaccine_center;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view=layoutInflater.inflate(R.layout.vaccination_info_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.vaccinationCenter.setText(list_vaccine_center.get(position).getVaccineCenter());
        holder.vaccinationCenterAddr.setText(list_vaccine_center.get(position).getVaccineCenterAddress());
        holder.vaccinationTiming.setText(list_vaccine_center.get(position).getVaccineTimings() + "-" +list_vaccine_center.get(position).getVaccineCenterTime());
        holder.vaccineName.setText(list_vaccine_center.get(position).getVaccineName());
        holder.vaccinationAvailable.setText(list_vaccine_center.get(position).getVaccineAvailable());
        holder.vaccineCharges.setText(list_vaccine_center.get(position).getVaccineCharges());
        holder.vaccinationAge.setText(list_vaccine_center.get(position).getVaccineAge());



    }

    @Override
    public int getItemCount() {
        return list_vaccine_center.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView vaccinationCenter;
        TextView vaccinationCenterAddr;
        TextView vaccinationTiming;
        TextView vaccineName;
        TextView vaccineCharges;
        TextView vaccinationAge;
        TextView vaccinationAvailable;









        public ViewHolder(@NonNull View itemView) {
            super(itemView);
vaccinationAge=itemView.findViewById(R.id.vaccine_age);
vaccinationAvailable=itemView.findViewById(R.id.vaccine_avaibility);
vaccineCharges=itemView.findViewById(R.id.vaccine_charges);
vaccineName=itemView.findViewById(R.id.vaccine_name);
vaccinationTiming=itemView.findViewById(R.id.vaccine_timings);
vaccinationCenter=itemView.findViewById(R.id.vaccine_center);
vaccinationCenterAddr=itemView.findViewById(R.id.vaccine_location);



        }
    }
}
