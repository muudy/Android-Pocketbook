package cn.jerryshell.pocketbook.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.modle.Item;


public class ItemRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Item> mItemList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public ItemRecyclerAdapter(List<Item> itemList) {
        mItemList = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.bindItem(item);
        holder.setOnClickListener(mOnItemClickListener);
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout mItemRoot;
    private TextView mTitleTextView;
    private TextView mMoneyTextView;
    private TextView mDateTextView;

    ViewHolder(View itemView) {
        super(itemView);
        mItemRoot = (LinearLayout) itemView.findViewById(R.id.item_root);
        mTitleTextView = (TextView) itemView.findViewById(R.id.tv_title);
        mMoneyTextView = (TextView) itemView.findViewById(R.id.tv_money);
        mDateTextView = (TextView) itemView.findViewById(R.id.tv_date);
    }

    void bindItem(Item item) {
        mTitleTextView.setText(item.getTitle());
        mMoneyTextView.setText(item.getMoney());
        mDateTextView.setText(item.getDate());
    }

    void setOnClickListener(final ItemRecyclerAdapter.OnItemClickListener onClickListener) {
        mItemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(v, getAdapterPosition());
            }
        });
        mItemRoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

}