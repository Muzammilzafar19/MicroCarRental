package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import BeanClasses.BeanClassForDish;
import design.ws.com.MicroCarRental.MainActivity;
import design.ws.com.MicroCarRental.R;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_Dish extends RecyclerView.Adapter<RecycleAdapter_Dish.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private List<BeanClassForDish> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView dish_name;

        TextView price;

        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            dish_name = (TextView) view.findViewById(R.id.tv_dish_name);

            price = (TextView) view.findViewById(R.id.tv_price);
        }

    }



    public RecycleAdapter_Dish(MainActivity mainActivityContacts, List<BeanClassForDish> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish_list, parent, false);



        return new MyViewHolder(itemView);


    }





    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        
        
        
        BeanClassForDish movie = moviesList.get(position);
        holder.dish_name.setText(movie.getDish_name());

        holder.price.setText(movie.getPrice());
        holder.image.setImageResource(movie.getImage());
        

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


