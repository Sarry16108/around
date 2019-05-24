package com.example.home_around.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.home_around.adapter.SearchAdapter;
import com.example.home_around.adapter.SearchResultAdapter;
import com.example.home_around.entity.SearchResultData;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.bean.ConstValue;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.SharePrefUtils;
import com.example.lib_generic.widgets.OnKeyOkListener;
import com.example.lib_generic.widgets.SearchView;
import com.example.home_around.adapter.SearchTipAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.HistRecordData;
import com.example.home_around.entity.HotSearchData;
import com.example.home_around.entity.SearchBaseItemData;
import com.example.home_around.entity.SearchDividerTagData;
import com.example.home_around.entity.SearchTip;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private SearchView mSearchView;
    private TextView    mClear;

    private RecyclerView    mSearchTip;
    private SearchTipAdapter    mSearchTipAdapter;

    //搜索历史和搜索结果
    private RecyclerView    mSearch;
    private SearchAdapter mSearchAdapter;


    //搜索历史和搜索结果
    private RecyclerView    mSearchResult;
    private SearchResultAdapter mSearchResultAdapter;




    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mSearchView = findViewById(R.id.search_bar);
        mClear = findViewById(R.id.clear);
        mSearchTip = findViewById(R.id.search_tip);
        mSearch = findViewById(R.id.search_history);
        mSearchResult = findViewById(R.id.search_result);


        mBack.setOnClickListener(this);
        mClear.setOnClickListener(this);
        mSearchView.enableEdit(true);
        mSearchView.addTextChanged(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                //提示内容和按钮显示隐藏
                if (!TextUtils.isEmpty(s.toString()) && mClear.getVisibility() != View.VISIBLE) {
                    mClear.setVisibility(View.VISIBLE);
                    mSearchTip.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s.toString())) {
                    mClear.setVisibility(View.GONE);
                    mSearchTip.setVisibility(View.GONE);
                }
            }
        });
        mSearchView.onKeyOk(new OnKeyOkListener() {
            @Override
            public void onClick() {
                mSearch.setVisibility(View.GONE);
                mSearchTip.setVisibility(View.GONE);
                mSearchResult.setVisibility(View.VISIBLE);

                List<String> records = SharePrefUtils.getObjList(ConstValue.PAD_SEARCH_HIS_RECORD, String.class);
                records.add(mSearchView.getText());
                SharePrefUtils.putObject(ConstValue.PAD_SEARCH_HIS_RECORD, records);

                //热搜
                List<SearchResultData.SearchResultItem> hots = new ArrayList<>();

                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));
                hots.add(new SearchResultData.SearchResultItem("0", "", "什么啊", "lasdfjsdf"));

                mSearchResultAdapter.setItems(hots);
            }
        });

        //列表设置
        mSearchTipAdapter = new SearchTipAdapter(this);
        LinearLayoutManager tipLinearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearchTip.setLayoutManager(tipLinearLayout);
        mSearchTip.setAdapter(mSearchTipAdapter);

        mSearchAdapter = new SearchAdapter(this);
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearch.setLayoutManager(manager2);
        mSearch.setAdapter(mSearchAdapter);


        mSearchResultAdapter = new SearchResultAdapter(this);
        LinearLayoutManager manager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearchResult.setLayoutManager(manager3);
        mSearchResult.setAdapter(mSearchResultAdapter);

        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.clear:
                mSearchView.clearText();

                break;
        }
    }

    public void initData() {
        List<SearchTip.SearchTipItem> items = new ArrayList<>(6);
        items.add(new SearchTip.SearchTipItem("香奈儿", 1));
        items.add(new SearchTip.SearchTipItem("香的", 1));
        items.add(new SearchTip.SearchTipItem("香香香的", 1));
        items.add(new SearchTip.SearchTipItem("香香香的香香的", 1));
        items.add(new SearchTip.SearchTipItem("香香的", 1));

        mSearchTipAdapter.setItems(items);


        List<SearchBaseItemData> searchs = new ArrayList<>(20);
        searchs.add(new SearchDividerTagData("搜索历史"));

        //搜索记录
        List<String> records = SharePrefUtils.getObjList(ConstValue.PAD_SEARCH_HIS_RECORD, String.class);
        for (String item : records) {
            searchs.add(new HistRecordData(item));
        }


        if (records.size() > 0) {
            mSearchAdapter.setItems(searchs);
            mSearch.setVisibility(View.VISIBLE);
        }
    }
}
