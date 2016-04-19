package razerdp.friendcircle.ui.adapteritem;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import razerdp.friendcircle.R;
import razerdp.friendcircle.app.adapter.base.viewholder.BaseItemDelegate;
import razerdp.friendcircle.app.config.DynamicType;
import razerdp.friendcircle.app.mvp.model.entity.MomentsInfo;
import razerdp.friendcircle.app.adapter.GridViewAdapter;
import razerdp.friendcircle.widget.NoScrollGridView;

/**
 * Created by 大灯泡 on 2016/2/25.
 * 图文的朋友圈item
 * type=13{@link DynamicType}
 */
public class ItemWithImg extends BaseItemDelegate implements AdapterView.OnItemClickListener {
    private static final String TAG = "ItemWithImg";

    private NoScrollGridView mNoScrollGridView;
    private GridViewAdapter mGridViewAdapter;

    private ArrayList<String> mUrls = new ArrayList<>();
    private ArrayList<Rect> mRects = new ArrayList<>();

    public ItemWithImg() {}

    @Override
    public int getViewRes() {
        return R.layout.dynamic_item_with_img;
    }

    @Override
    public void onFindView(@NonNull View parent) {
        if (mNoScrollGridView == null) mNoScrollGridView = (NoScrollGridView) parent.findViewById(R.id.item_grid);
        if (mNoScrollGridView.getOnItemClickListener() == null) {
            mNoScrollGridView.setOnItemClickListener(this);
        }
    }

    @Override
    protected void bindData(int position, @NonNull View v, @NonNull MomentsInfo data, int dynamicType) {
        if (data.content.imgurl == null || data.content.imgurl.size() == 0 || mNoScrollGridView == null) return;
        mUrls.clear();
        mUrls.addAll(data.content.imgurl);
        if (mNoScrollGridView.getAdapter() == null) {
            mGridViewAdapter = new GridViewAdapter(context, data.content.imgurl);
            mNoScrollGridView.setAdapter(mGridViewAdapter);
        }
        if (data.content.imgurl.size() == 4) {
            mNoScrollGridView.setNumColumns(2);
        }
        else {
            mNoScrollGridView.setNumColumns(3);
        }
        mGridViewAdapter.reSetData(data.content.imgurl);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final int childCount = parent.getChildCount();
        mRects.clear();
        try {
            if (childCount >= 0) {
                for (int i = 0; i < childCount; i++) {
                    View v = parent.getChildAt(i);
                    Rect bound = new Rect();
                    v.getGlobalVisibleRect(bound);
                    mRects.add(bound);
                }
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "view可能为空哦");
        }
        getPresenter().shoPhoto(mUrls, mRects, position);
    }
}
