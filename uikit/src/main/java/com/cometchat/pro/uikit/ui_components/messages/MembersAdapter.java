package com.cometchat.pro.uikit.ui_components.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.uikit.R;

import java.util.ArrayList;
import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<String> memberList;
    private List<String> memberListFiltered;
    private MembersAdapterListener listener;

    public MembersAdapter(Context context, List<String> memberList, MembersAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.memberList = memberList;
        this.memberListFiltered = memberList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_member, parent, false);
        return new MembersAdapter.TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        String name = memberListFiltered.get(position);

        ((TextViewHolder) viewHolder).tvName.setText(name);

        ((TextViewHolder) viewHolder).tvName.setOnClickListener(v -> listener.onMemberSelected(name));
    }

    @Override
    public int getItemCount() {
        return memberListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    memberListFiltered = memberList;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String row : memberList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (row.toLowerCase().contains(charString.toLowerCase()) || row.contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    memberListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = memberListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                memberListFiltered = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class TextViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        TextViewHolder(@NonNull View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
        }
    }

    public interface MembersAdapterListener {
        void onMemberSelected(String name);
    }
}

