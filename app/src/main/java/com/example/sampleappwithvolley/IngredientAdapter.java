package com.example.sampleappwithvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import domain.Cocktail;
import domain.Ingredient;

public class IngredientAdapter  extends BaseAdapter {
    private Context context;
    private List<Ingredient> ingredients;


    public IngredientAdapter(Context context, List<Ingredient> ingredients){
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
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

        Ingredient ingredient = ingredients.get(position);
        TextView idDrink = view.findViewById(R.id.idDrink);
        TextView strDrink = view.findViewById(R.id.strDrink);
        TextView strCategory = view.findViewById(R.id.strCategory);
        TextView strAlcoholic = view.findViewById(R.id.strAlcoholic);
        TextView ingredients = view.findViewById(R.id.ingredients);
        TextView measures = view.findViewById(R.id.measures);

        //idDrink.setText(ingredient.getIdIngredient());
        //strDrink.setText(ingredient.getStrDescription());
       // strCategory.setText(ingredient.getStrType());
        //strAlcoholic.setText(ingredient.getStrAlcohol());
        ingredients.setText(ingredient.toString());

        return view;
    }
}
