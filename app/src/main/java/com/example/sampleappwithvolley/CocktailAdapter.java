package com.example.sampleappwithvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import domain.Cocktail;

public class CocktailAdapter extends BaseAdapter {
    private Context context;
    private List<Cocktail> cocktails;


    public CocktailAdapter(Context context, List<Cocktail> cocktails){
        this.context = context;
        this.cocktails = cocktails;
    }

    @Override
    public int getCount() {
        return cocktails.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cocktails_item, parent, false);
        } else {
            view = convertView;
        }

        Cocktail cocktail = cocktails.get(position);
        TextView idDrink = view.findViewById(R.id.idDrink);
        TextView strDrink = view.findViewById(R.id.strDrink);
        TextView strCategory = view.findViewById(R.id.strCategory);
        TextView strAlcoholic = view.findViewById(R.id.strAlcoholic);
        TextView ingredients = view.findViewById(R.id.ingredients);
        TextView measures = view.findViewById(R.id.measures);

        idDrink.setText(cocktail.getIdDrink());
        strDrink.setText(cocktail.getStrDrink());
        strCategory.setText(cocktail.getStrCategory());
        strAlcoholic.setText(cocktail.getStrAlcoholic());
        ingredients.setText(cocktail.toString());

        return view;
    }
}
