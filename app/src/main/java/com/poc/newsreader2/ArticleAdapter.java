package com.poc.newsreader2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView; // Import ImageView
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide; // Import Glide
import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    // Setting the TAG for debugging purposes
    private static String TAG = "ArticleAdapter";

    private ArrayList<NewsArticle> mArrayList;
    private Context mContext;

    public ArticleAdapter(Context context, ArrayList<NewsArticle> list) {
        // Initializing the constructor
        this.mContext = context;
        this.mArrayList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout with the article view (R.layout.article_item)
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // The parameter position is the index of the current article
        // Getting the current article from the ArrayList using the position
        NewsArticle currentArticle = mArrayList.get(position);

        // Setting the text of TextViews
        holder.title.setText(currentArticle.getTitle());
        holder.description.setText(currentArticle.getDescription());

        // Substring(0,10) trims the date to make it short
        holder.contributordate.setText(currentArticle.getAuthor() +
                " | " + currentArticle.getPublishedAt().substring(0, 10));

        // Loading image from network into ImageView using Glide
        Glide.with(mContext)
                .load(currentArticle.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                .error(R.drawable.ic_launcher_foreground) // Error image
                .into(holder.image);

        // Setting the content description on the Image
        holder.image.setContentDescription(currentArticle.getContent());

        // Handling click event of the article
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // An intent to the WebActivity that displays web pages
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url_key", currentArticle.getUrl());

                // Starting an Activity to display the page of the article
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declaring the views
        private TextView title, description, contributordate;
        private ImageView image; // Change to ImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assigning views to their ids
            title = itemView.findViewById(R.id.title_id);
            description = itemView.findViewById(R.id.description_id);
            image = itemView.findViewById(R.id.image_id); // Change to ImageView
            contributordate = itemView.findViewById(R.id.contributordate_id);
        }
    }
}

