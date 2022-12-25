package com.example.amazonprimeclone.adapters.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.fragments.people.PeopleFragmentDirections;
import com.example.amazonprimeclone.modal.PersonResponseResults;
import com.example.amazonprimeclone.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleSearchAdapter extends RecyclerView.Adapter<PeopleSearchAdapter.PeopleViewHolder> {

    private final Context context;
    private final List<PersonResponseResults> personList;

    public PeopleSearchAdapter(Context context, List<PersonResponseResults> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_people,parent,false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        PersonResponseResults responseResults = personList.get(position);

        Glide.with(holder.person_img).load(responseResults.getProfile_path()).into(holder.person_img);

        holder.textView.setText(responseResults.getName());

        holder.itemView.setOnClickListener(v -> {

            PeopleFragmentDirections.ActionNavPeopleToPersonDetailFragment navPeopleToPersonDetailFragment =
                    PeopleFragmentDirections.actionNavPeopleToPersonDetailFragment(String.valueOf(responseResults.getId()));
            Navigation.findNavController(holder.itemView).navigate(navPeopleToPersonDetailFragment);
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView person_img;
        private final TextView textView;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            person_img = itemView.findViewById(R.id.circular_person_img);
            textView = itemView.findViewById(R.id.person_name);
        }
    }

}
