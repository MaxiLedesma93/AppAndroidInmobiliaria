package com.example.appinmobiliaria.ui.contrato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinmobiliaria.R;
import com.example.appinmobiliaria.models.Pago;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private List<Pago> lista;
    private View root;
    private LayoutInflater inflater;

    public PagoAdapter(List<Pago> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolder holder, int position) {
        Pago p = lista.get(position);
        holder.cod.setText(p.getId()+"");
        holder.num.setText(p.getNumPago()+"");
        holder.codCon.setText(p.getContrato().getId()+"");
        holder.Imp.setText(p.getImporte()+"");
        DateTimeFormatter dTimeForm = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate fechaPago = LocalDate.parse(p.getFechaPago().toString(), dTimeForm);
        holder.fecha.setText(fechaPago+"");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText cod, num, codCon, Imp, fecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cod = itemView.findViewById(R.id.etPagosCod);
            num = itemView.findViewById(R.id.etPagosNumPago);
            codCon = itemView.findViewById(R.id.etPagosCodCon);
            Imp = itemView.findViewById(R.id.etPagosImp);
            fecha = itemView.findViewById(R.id.etPagosFecha);
        }
    }
}
