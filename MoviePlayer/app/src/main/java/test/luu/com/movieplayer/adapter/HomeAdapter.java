package test.luu.com.movieplayer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import test.luu.com.movieplayer.R;
import test.luu.com.movieplayer.model.Movie;
import test.luu.com.movieplayer.util.AnimateUtil;

/**
 * Created by luu trinh on 8/16/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ItemViewHolder> {



    class ItemViewHolder extends RecyclerView.ViewHolder{

        WeakReference<CardView> cardView;
        WeakReference<ImageView> imgView;
        WeakReference<TextView> txtTitle;
        WeakReference<TextView> txtSubtitle;

        public ItemViewHolder(View view){

            super(view);

            cardView = new WeakReference<CardView>((CardView)view.findViewById(R.id.cv_now_playing));
            imgView = new WeakReference<ImageView>((ImageView)view.findViewById(R.id.img_pic));
            txtTitle = new WeakReference<TextView>((TextView)view.findViewById(R.id.txt_title));
            txtSubtitle = new WeakReference<TextView>((TextView)view.findViewById(R.id.txt_date));
        }
    }

    Context mContext;
    List<Movie> mMovies;
    int mSize;
    Listener mListener;
    int mLastPositionAnimated = -1;

    public HomeAdapter(Context context, List<Movie> movies){

        mContext = context;
        mMovies = movies;
        mSize = (int)mContext.getResources().getDimension(R.dimen.image_home_size);
        if(movies == null){

            mMovies = new ArrayList<>();
        }
    }

    public void resetData() {

        mMovies = new ArrayList<>();
    }

    public void addItems(List<Movie> movies){

        if(movies != null) {
            mMovies.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public void setListener(Listener listener){

        mListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_playing_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        final Movie movie = mMovies.get(holder.getAdapterPosition());
        String posterPath = movie.getPosterPath();
        Glide.with(mContext).load(Movie.BASE_IMAGE_URL + posterPath).override(mSize, mSize).centerCrop().into(holder.imgView.get());
        holder.txtTitle.get().setText(movie.getTitle());
        holder.txtSubtitle.get().setText(movie.getReleaseDate());
        holder.cardView.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mListener != null){

                    mListener.onItemClick(movie);
                }
            }
        });

        if(holder.getAdapterPosition() > mLastPositionAnimated) {
            AnimateUtil.setCardViewFromLeftAnimation(mContext, holder.itemView);
            mLastPositionAnimated = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public interface Listener{

        void onItemClick(Movie movie);
    }
}
