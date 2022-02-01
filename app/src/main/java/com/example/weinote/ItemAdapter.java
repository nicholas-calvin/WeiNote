package com.example.weinote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Viewholder>{
    Context ctx;
    private ArrayList<String> noteTitle = new ArrayList<>();
    private ArrayList<String> noteDetails = new ArrayList<>();

    public ItemAdapter(Context ctx, ArrayList<String> noteTitle, ArrayList<String> noteDetails) {
        this.ctx = ctx;
        this.noteTitle = noteTitle;
        this.noteDetails = noteDetails;
    }

    @NonNull
    @Override
    public ItemAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_row_list, parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.Viewholder holder, int position) {
        String title = noteTitle.get(position);
        String detail = noteDetails.get(position);
        holder.tvNoteTitle.setText(title);
        holder.tvNoteDetails.setText(detail);
    }

    @Override
    public int getItemCount() {
        return noteTitle.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNoteTitle, tvNoteDetails;

        public Viewholder(@NonNull View noteView) {
            super(noteView);
            tvNoteTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvNoteDetails = itemView.findViewById(R.id.tvNoteDetails);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

//        @Override
//        public void onClick(View v) {
//            int pos = getAdapterPosition();
//            Intent intent= new Intent(ctx, ItemOrderQty.class);
//            intent.putExtra("itemimage", itemPic.get(pos));
//            intent.putExtra("itemname", itemName.get(pos));
//            intent.putExtra("itemprice", itemprice.get(pos));
//            ctx.startActivity(intent);
//        }
    }
}
