package app.forrozapp.meuforroapp.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.plugmusica.application1.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AdapterListView extends BaseAdapter {
    Context context;
    private List<ItemListView> itens;
    private LayoutInflater mInflater;

    public AdapterListView(Context context2, List<ItemListView> itens2) {
        this.itens = itens2;
        this.mInflater = LayoutInflater.from(context2);
        this.context = context2;
    }

    public int getCount() {
        return this.itens.size();
    }

    public ItemListView getItem(int position) {
        return this.itens.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_list, (ViewGroup) null);
            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = (TextView) view.findViewById(R.id.text);
            itemHolder.imgIcon = (ImageView) view.findViewById(R.id.imagemview);
            itemHolder.subtitulo = (TextView) view.findViewById(R.id.subtext);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        ItemListView item = this.itens.get(position);
        itemHolder.txtTitle.setText(item.getTexto());
        if (item.getCd_cover() == null) {
            Picasso.with(this.context).load(item.getCd_cover().replace(" ", "%20")).into(itemHolder.imgIcon);
        } else {
            Picasso.with(this.context).load((item.getUrl() + "/cd_cover.jpg").replace(" ", "%20")).into(itemHolder.imgIcon);
        }
        itemHolder.subtitulo.setText("Postado em " + item.getData() + "\n" + item.getViews() + " views &" + item.getDowns() + " downloads");
        return view;
    }

    private class ItemSuporte {
        ImageView imgIcon;
        public TextView subtitulo;
        TextView txtTitle;

        private ItemSuporte() {
        }
    }
}
