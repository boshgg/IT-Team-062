package com.example.myapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.NoteAddActivity;
import com.example.myapplication.activity.UpdateNoteActivity;
import com.example.myapplication.bean.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

        private Context context;
        private List<Note> nData;

        //set up adapter of note
        public NoteListAdapter(Context context, List<Note> data) {
            this.context = context;
            nData = data;
        }

        //create adapter of note
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.my_note_row,parent,false);
            return new MyViewHolder(view);
        }

        //set up all the values into the list
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            Note note = nData.get(position);

            String nid = position+1+"";
            String cid = note.getCid();
            String event = note.getEvent();
            String date = note.getDate();
            String addnote = note.getAddnote();
            String type = note.getType();

            holder.tvId.setText(nid);
            holder.tvCid.setText(cid);
            holder.tvEvent.setText(event);
            holder.tvDate.setText(date);
            holder.tvType.setText(type);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateNoteActivity.class);
                    intent.putExtra("id",note.getId());
                    intent.putExtra("cid",cid);
                    intent.putExtra("event",event);
                    intent.putExtra("date",date);
                    intent.putExtra("addnote",addnote);
                    intent.putExtra("type",type);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return nData==null?0:nData.size();
        }

        //get all the values from the layout (my_note_row.xml)
        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvCid, tvId, tvEvent,tvDate, tvType;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.note_id_txt);
                tvCid = itemView.findViewById(R.id.customer_name_txt);
                tvEvent = itemView.findViewById(R.id.event_name_txt);
                tvDate = itemView.findViewById(R.id.event_date_txt);
                tvType = itemView.findViewById(R.id.event_type_txt);
            }
        }
    }

