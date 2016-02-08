package com.joseluis.spotifylayout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joseluis.spotifylayout.R;
import com.joseluis.spotifylayout.model.Song;

import java.util.List;

/**
 * Created by jlsh on 31/1/16.
 */
public class SptfAdapter extends BaseAdapter {

    /**
     * Listado de objetos (model)
     */
    private List<Song> items;

    /**
     * objeto con el que traeremos la vista a la clase
     */
    private LayoutInflater inflater;


    /**
     * Constructor del adaptador
     * @param items
     * @param context
     */
    public SptfAdapter(List<Song> items, Context context){
        super();
        this.items = items;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //get model
        Song song = items.get(i);

        //get view
        View rowView = view;

        //if null, inflate
        if(rowView == null) {
            rowView = inflater.inflate(R.layout.lay_song_row, viewGroup, false);
        }

        //configuramos el titulo
        TextView tvSong = (TextView) rowView.findViewById(R.id.tvSong);
        tvSong.setText(song.getName());

        //configuramos la fecha
        TextView tvAuthor = (TextView) rowView.findViewById(R.id.tvAuthor);
        tvAuthor.setText(song.getAuthor());

        //seteamos el objeto
        rowView.setTag(song);

        return rowView;
    }
}
