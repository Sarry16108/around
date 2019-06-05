package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_generic.bean.ConstValue;
import com.example.lib_generic.utils.SharePrefUtils;
import com.example.module_around.R;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.DividerTagData;
import com.example.home_around.entity.HistRecordData;
import com.example.home_around.entity.HotSearchData;
import com.example.home_around.entity.SearchBaseItemData;
import com.example.home_around.entity.SearchDividerTagData;
import com.example.home_around.entity.SearchResultData;
import com.example.home_around.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<BaseHolder> {

    private List<SearchBaseItemData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public SearchAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }


    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {

            case SearchBaseItemData.TYPE_HIS_RECORD:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_history_item, viewGroup, false);
                return new HistRecordHolder(view);

            case SearchBaseItemData.TYPE_HOT_SEARCH:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_hot_search_item, viewGroup, false);
                return new HotSearchHolder(view);

            case SearchBaseItemData.TYPE_SEARCH_RESULT:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_search_result_item, viewGroup, false);
                return new ResultHolder(view);

            case SearchBaseItemData.TYPE_DIVIDER_TAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_tag_item, viewGroup, false);
                return new DividerTagHolder(view);
            case SearchBaseItemData.TYPE_FOOTER:
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_footer_item, viewGroup, false);
                return new FooterHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder viewHolder, int i) {
        SearchBaseItemData data = mItems.get(i);

        if (viewHolder instanceof HistRecordHolder) {
            ((HistRecordHolder)viewHolder).bindViewHolder((HistRecordData) data);
        }else if (viewHolder instanceof DividerTagHolder) {
            ((DividerTagHolder)viewHolder).bindViewHolder((SearchDividerTagData) data);
            ((DividerTagHolder)viewHolder).clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.clear();
                    SharePrefUtils.putObject(ConstValue.PAD_SEARCH_HIS_RECORD, "");
                    LogUtils.d( "点击清理了");
                    notifyDataSetChanged();
                }
            });
        } else if (viewHolder instanceof FooterHolder) {
            ((FooterHolder)viewHolder).bindViewHolder();
        }

        if (mItemClickListener != null) {
            viewHolder.setOnItemClickListener(mItemClickListener, i);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<SearchBaseItemData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(SearchBaseItemData item) {
        mItems.add(item);
    }

    public void addItems(List<SearchBaseItemData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public SearchBaseItemData getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends BaseHolder {
        private ImageView image;
        private TextView  title;
        private TextView  extInfo;
        private TextView  price;
        private RelativeSizeSpan spanBig;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);

            spanBig = new RelativeSizeSpan(1.5f);
        }


        public void bindViewHolder(SearchResultData.SearchResultItem data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());
        }
    }



    public static class HotSearchHolder extends BaseHolder {
        private TextView text;

        public HotSearchHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
        }

        public void bindViewHolder(String data) {
            text.setText(data);
        }
    }


    public static class HistRecordHolder extends BaseHolder {
        private TextView text;

        public HistRecordHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
        }

        public void bindViewHolder(HistRecordData data) {
            text.setText(data.getItem());
        }
    }

    public static class DividerTagHolder extends BaseHolder {
        private TextView tag;
        private ImageView clear;

        public DividerTagHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
            clear = itemView.findViewById(R.id.clear);
        }

        public void bindViewHolder(SearchDividerTagData data) {
            tag.setText(data.getTag());
        }
    }


    public static class FooterHolder extends BaseHolder {

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindViewHolder() {

        }
    }
}
