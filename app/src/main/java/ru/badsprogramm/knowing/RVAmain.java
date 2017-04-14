package ru.badsprogramm.knowing;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class RVAmain extends RecyclerView.Adapter<RVAmain.ViewHolder> {

    List<Subject> list;
    Context context;
    int[] color = {R.color.cc_green, R.color.cc_green_d,
            R.color.cc_blue, R.color.cc_blue_d,
            R.color.cc_orange, R.color.cc_orange_d,
            R.color.cc_red, R.color.cc_red_d};

    public RVAmain(Context context, List<Subject> list){
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvContinue;
        ProgressBar progressBar;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContinue = (TextView) itemView.findViewById(R.id.tv_continue);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            int c = context.getResources().getColor(R.color.cc_progress_bar);
            progressBar.getProgressDrawable().setColorFilter(c, android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_subject, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).name);
        holder.cv.setBackgroundColor(context.getResources().getColor(color[(position*2)% color.length]));
        holder.tvContinue.setBackgroundColor(context.getResources().getColor(color[(position*2+1)% color.length]));
        holder.progressBar.setMax(list.get(position).max);
        holder.progressBar.setProgress(list.get(position).progress);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
