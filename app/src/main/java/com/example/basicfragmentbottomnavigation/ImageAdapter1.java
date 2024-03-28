package com.example.basicfragmentbottomnavigation;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter1 extends RecyclerView.Adapter<ImageAdapter1.ImageViewHolder> {
    private Context mContext;
    private List<Upload1> mUploads;

    public ImageAdapter1(Context context, List<Upload1> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_view1, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final Upload1 uploadCurrent = mUploads.get(position);
      /*  holder.textViewName.setText(uploadCurrent.getName());


        String str = uploadCurrent.getBranch();
        String [] parts = str.split(",");
        String f="";
        String s1 ="\n";
        String s2 =":";
        int i = 0;
        for(String ss: parts){
            if(i==0)
            {    f=  f.concat(s1);
                f = f.concat(ss);
                f=f.concat(s2);
                i=1;
            }
            else {

                f=f.concat(ss);
                f=f.concat(s1);
                i=0;
            }

        }




        holder.textViewDesc.setText(uploadCurrent.getDesc()+"\nBranch:Cutoff\n"+f);
        holder.textViewLoc.setText("Location:"+uploadCurrent.getLoc());
        holder.textViewRank.setText("Rank:"+uploadCurrent.getRank()+"*");*/


        // holder.textViewBranch.setText(uploadCurrent.getBranch());
        //  Picasso.get().load(mImageUri).into(mImageView);
        Picasso.get().load(uploadCurrent.getDoc()).into(holder.imageView);
        // Glide.with(mContext).load(uploadCurrent.getImageUrl()).into(holder.imageView);
       /* holder.expbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.cv.getVisibility()==View.GONE)
                {
                    holder.cv.setVisibility(View.VISIBLE);
                    holder.expbtn.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }
                else{
                    holder.cv.setVisibility(View.GONE);
                    holder.expbtn.setImageResource(R.drawable.ic_expand_more_black_24dp);

                }

            }
        });*/
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(uploadCurrent.getDoc());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDesc;
        // public TextView textViewBranch;

        public TextView textViewLoc;
        public TextView textViewRank;
        public TextView textViewName;
        public ImageView imageView;
        public  ImageView expbtn;
        public Button bt;
        public CardView cv;

        public ImageViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_img);

            bt=itemView.findViewById(R.id.btn_apply);
        }
    }





}
