package vincentzw.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
    public ImageView _jkImageView;
    public TextView _jkTextView;
    public TextView _jpTextView;
    public TextView _namaTextView;
    public TextView _nimTextView;

    public MahasiswaViewHolder(@NonNull View itemView)
    {
        super(itemView);
        _jkImageView = itemView.findViewById(R.id.jkImageView);
        _jkTextView = itemView.findViewById(R.id.jkTextView);
        _jpTextView = itemView.findViewById(R.id.jpTextView);
        _namaTextView = itemView.findViewById(R.id.namaTextView);
        _nimTextView = itemView.findViewById(R.id.nimTextView);
    }
}