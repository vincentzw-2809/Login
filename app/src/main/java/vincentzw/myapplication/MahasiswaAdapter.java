package vincentzw.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaViewHolder> {

    private List<MahasiswaModel> _mahasiswaModelList;

    public MahasiswaAdapter(List<MahasiswaModel> mahasiswaModelList)
    {
        this._mahasiswaModelList = mahasiswaModelList;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_list_mahasiswa, parent, false);
        return new MahasiswaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position)
    {
        MahasiswaModel mm = _mahasiswaModelList.get(position);

        holder._jkImageView.setImageResource(R.drawable.boy);

        if (mm.getJenisKelamin().toLowerCase().equals("perempuan"))
        {
            holder._jkImageView.setImageResource(R.drawable.girl);
        }

        holder._nimTextView.setText(mm.getNIM());
        holder._namaTextView.setText(mm.getNama());
        holder._jkTextView.setText(mm.getJenisKelamin());

        String jp = mm.getJP();
        jp = jp.substring(0, 2); // Hanya Mengambil 2 Karakter Depan
        holder._jpTextView.setText(jp);

    }

    @Override
    public int getItemCount()
    {
        int count = 0;

        if (_mahasiswaModelList != null)
        {
            count = _mahasiswaModelList.size();
        }

        return count;
    }
}
