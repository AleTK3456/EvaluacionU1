package Alexander.taskmaster.noseloco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Alexander.taskmaster.R;

public class Adapter extends RecyclerView.Adapter<Adapter.NotaViewHolder> {

    private ArrayList<Modelo> listaNotas;

    public Adapter(ArrayList<Modelo> listaNotas) {
        this.listaNotas = listaNotas;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notas, parent, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Modelo nota = listaNotas.get(position);
        holder.tituloNota.setText(nota.getNombre());
        holder.descripcionNota.setText(nota.getDescripcion());
        holder.imagen.setImageResource(nota.getImagen());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    static class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloNota;
        TextView descripcionNota;
        ImageView imagen;

        NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloNota = itemView.findViewById(R.id.card_titulo);
            descripcionNota = itemView.findViewById(R.id.card_descripcion);
            imagen = itemView.findViewById(R.id.card_image);
        }
    }
}