package app.forrozapp.meuforroapp.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.plugmusica.application1.R;
import java.util.List;

public class AdapterListView_Musicas extends BaseAdapter {
    private List<ItemListView_Musicas> itens;
    private LayoutInflater mInflater;

    public AdapterListView_Musicas(Context context, List<ItemListView_Musicas> itens2) {
        this.itens = itens2;
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.itens.size();
    }

    public ItemListView_Musicas getItem(int position) {
        return this.itens.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_list_musicas, (ViewGroup) null);
            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = (TextView) view.findViewById(R.id.text);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        itemHolder.txtTitle.setText(this.itens.get(position).getTexto());
        return view;
    }

    private class ItemSuporte {
        TextView txtTitle;

        private ItemSuporte() {
        }
    }
}
