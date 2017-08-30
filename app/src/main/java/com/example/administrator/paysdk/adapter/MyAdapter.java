package com.example.administrator.paysdk.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.paysdk.R;
import com.example.administrator.paysdk.javabean.GastStation;
import com.example.administrator.paysdk.javabean.Gatstation;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28/028.
 * recycleView的适配器，数据源来自于gatStationList列表数组
 */

/*
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Gatstation> gatStations;
    */
/**
     * 内部类ViewHolder
     *//*

    public class ViewHolder extends RecyclerView.ViewHolder {
         //private ImageView iv_img;
        private TextView tv_text;

        public ViewHolder(View itemView) {
            super(itemView);
           // iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_text = (TextView) itemView.findViewById(R.id.item_petrol);
        }

        public void setData(int position) {
           // iv_img.setImageResource(imgList[position % imgList.length]);
            tv_text.setText((CharSequence) gatStations);
            tv_text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

    }

    */
/**
     * 构造函数
     *
     * @param context
     * @param gatstationList
     *//*

    public MyAdapter(Context context, List<Gatstation> gatstationList) {
        this.context = context;
    }

    */
/**
     * 重写onCreateViewHolder方法，创建ViewHolder实例，并返回
     *
     * @param parent
     * @param viewType
     * @return
     *//*

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(v.getContext(), "您点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    */
/**
     * 给子布局填充数据
     *
     * @param holder
     * @param position
     *//*

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    */
/**
     * 子项个数
     *
     * @return
     *//*

    @Override
    public int getItemCount() {
        return gatStations.size();
    }
}
*/

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private Context context;
    private List<GastStation> gastStations;
    private OnItemClickListener listener;

    public MyAdapter(Context context, List<GastStation> gastStations) {
        this.context = context;
        this.gastStations = gastStations;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);

        view.setOnClickListener(this);

        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder)holder;

        viewHolder.itemView.setTag(position);

        GastStation gastStation = gastStations.get(position);

        viewHolder.tvContent.setText(gastStation.getName()+"\n"+gastStation.getAreaName()+"\n"+gastStation.getAddress());
    }

    @Override
    public int getItemCount() {
        return gastStations.size();
    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;


        public ItemViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView)itemView.findViewById(R.id.item_petrol);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public interface OnItemClickListener{

        void onItemClick(View v, int position);
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(v, (int)v.getTag());
    }
}
