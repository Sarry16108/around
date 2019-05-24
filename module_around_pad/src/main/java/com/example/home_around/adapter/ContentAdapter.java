package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_around.R;
import com.example.lib_generic.utils.GlideUtils;
import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.BrandSupplyData;
import com.example.home_around.entity.CategoryData;
import com.example.home_around.entity.DividerTagData;
import com.example.home_around.entity.HeaderData;
import com.example.home_around.entity.HomeBaseItemData;
import com.example.home_around.entity.RecommendData;
import com.example.home_around.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class ContentAdapter extends RecyclerView.Adapter<BaseHolder> {

    private List<HomeBaseItemData> mItems;
    private Context mContext;
    private HomeSpanSizeLookup  mSpanSizeLookup;
    private OnItemClickListener mItemClickListener;


    public ContentAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
        mSpanSizeLookup = new HomeSpanSizeLookup();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }


    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {
            case HomeBaseItemData.TYPE_HEADER:
//                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_header, viewGroup, false);
//                return new Hea
                break;

            case HomeBaseItemData.TYPE_CATEGORY:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_category_item, viewGroup, false);
                return new CategoryHolder(view);

            case HomeBaseItemData.TYPE_BRAND_SUPPLY:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_brand_supply_item, viewGroup, false);
                return new BrandSupplyHolder(view);

            case HomeBaseItemData.TYPE_RECOMMEND:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_recommend_item, viewGroup, false);
                return new RecommendHolder(view);

            case HomeBaseItemData.TYPE_DIVIDER_TAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_divider_tag_item, viewGroup, false);
                return new DividerTagHolder(view);

            case HomeBaseItemData.TYPE_FOOTER:
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_footer_item, viewGroup, false);
                return new FooterHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder viewHolder, int i) {
        HomeBaseItemData data = mItems.get(i);

        if (viewHolder instanceof DividerTagHolder) {
            ((DividerTagHolder)viewHolder).bindViewHolder((DividerTagData) data);
        } else if (viewHolder instanceof CategoryHolder) {
            ((CategoryHolder)viewHolder).bindViewHolder((CategoryData) data);
        } else if (viewHolder instanceof BrandSupplyHolder) {
            ((BrandSupplyHolder)viewHolder).bindViewHolder((BrandSupplyData) data);
        } else if (viewHolder instanceof RecommendHolder) {
            ((RecommendHolder)viewHolder).bindViewHolder((RecommendData) data);
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

    public void addItem(HomeBaseItemData item) {
        mItems.add(item);
    }

    public void addItems(List<HomeBaseItemData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public HomeBaseItemData getItem(int index) {
        return mItems.get(index);
    }




    private class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            return mItems.get(position).getSpanCount();
        }
    }


    public static class HeaderHolder extends BaseHolder {

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindViewHolder(HeaderData data) {

        }
    }


    public static class RecommendHolder extends BaseHolder {
        private ImageView image;
        private TextView  title;
        private TextView  extInfo;
        private TextView  price;
        private RelativeSizeSpan spanBig;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);

            spanBig = new RelativeSizeSpan(1.5f);
        }


        public void bindViewHolder(RecommendData data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());

            SpannableStringBuilder builder = new SpannableStringBuilder().
                    append("¥").append(data.getPrice(), spanBig, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                    .append("起");
            price.setText(builder);
        }
    }



    public static class BrandSupplyHolder extends BaseHolder {
        private ImageView image;

        public BrandSupplyHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
        }

        public void bindViewHolder(BrandSupplyData data) {
            GlideUtils.showImage(image, data.getImgUrl());
        }
    }


    public static class CategoryHolder extends BaseHolder {
        private ImageView image;
        private TextView title;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }

        public void bindViewHolder(CategoryData data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
        }
    }

    public static class DividerTagHolder extends BaseHolder {
        private TextView tag;

        public DividerTagHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
        }

        public void bindViewHolder(DividerTagData data) {
            tag.setText(data.getTag());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tag.getLayoutParams();
            switch (data.getPosition()) {
                case 0:
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT);
                    break;
                case 1:
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    break;
                case 2:
                    layoutParams.addRule(RelativeLayout.ALIGN_RIGHT);
                    break;
            }
            tag.setLayoutParams(layoutParams);
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
