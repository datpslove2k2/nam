package com.example.apptestfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {
        holder.nameKH.setText(model.getNameKH());
        holder.nameTG.setText(model.getNameTG());
        holder.dacTinh.setText(model.getDacTinh());
        holder.mauLa.setText(model.getMauLa());
        holder.congDung.setText(model.getCongDung());
        holder.duocTinh.setText(model.getDuocTinh());
        holder.chuY.setText(model.getChuY());

        Glide.with(holder.img.getContext())
                .load(model.getTurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        // detail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.detail_popup))
                        .setExpanded(true, 2200)
                        .create();
                View viewDialog = dialogPlus.getHolderView();
                TextView namekh = (TextView) viewDialog.findViewById(R.id.txtView);
                TextView nametg = (TextView) viewDialog.findViewById(R.id.txtDicription);
                TextView dactinh = (TextView) viewDialog.findViewById(R.id.txtnn);
                TextView maula = (TextView) viewDialog.findViewById(R.id.txtnm);
                CircleImageView imgDetail = (CircleImageView) viewDialog.findViewById(R.id.img_detail);

                namekh.setText(model.getNameKH());
                nametg.setText(model.getNameTG());
                dactinh.setText(model.getDacTinh());
                maula.setText(model.getMauLa());
                Glide.with(holder.img.getContext()).load(model.getTurl()).into(imgDetail);
                dialogPlus.show();

                ImageView btnBackMenu = (ImageView) viewDialog.findViewById(R.id.img_Back);
                btnBackMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
                    }
                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nameKH.getContext());
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data can't be Undo!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("medicinal")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nameKH.getContext(), "Cancelled!!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView img;
        private TextView nameKH, nameTG, dacTinh, mauLa, congDung, duocTinh, chuY;
        private Button btnDelete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            nameKH = (TextView) itemView.findViewById(R.id.txt_NameKH);
            nameTG = (TextView) itemView.findViewById(R.id.txt_NameTG);
            dacTinh = (TextView) itemView.findViewById(R.id.txt_DacTinh);
            mauLa = (TextView) itemView.findViewById(R.id.txt_MauLa);
            congDung = (TextView) itemView.findViewById(R.id.txt_CongDung);
            duocTinh = (TextView) itemView.findViewById(R.id.txt_CongDung);
            chuY = (TextView) itemView.findViewById(R.id.txt_ChuY);
            btnDelete = (Button) itemView.findViewById(R.id.btn_Delete);
        }
    }
}
