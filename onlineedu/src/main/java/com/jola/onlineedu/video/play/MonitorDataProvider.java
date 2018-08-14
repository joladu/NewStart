package com.jola.onlineedu.video.play;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.jola.onlineedu.video.beans.VideoBean;
import com.jola.onlineedu.util.DataUtils;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.BundlePool;
import com.kk.taurus.playerbase.event.EventKey;
import com.kk.taurus.playerbase.provider.BaseDataProvider;

import java.util.List;

/**
 * Created by Taurus on 2018/4/15.
 */

public class MonitorDataProvider extends BaseDataProvider {

    private DataSource mDataSource;

    private List<VideoBean> mVideos;

    public MonitorDataProvider(){
        mVideos = DataUtils.getVideoList();
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    /**
     * 调用此方法 提供数据源
     * @param sourceData
     */
    @Override
    public void handleSourceData(DataSource sourceData) {
        this.mDataSource = sourceData;
        onProviderDataStart();
        mHandler.removeCallbacks(mLoadDataRunnable);
        mHandler.postDelayed(mLoadDataRunnable, 2000);
    }

    private Runnable mLoadDataRunnable = new Runnable() {
        @Override
        public void run() {
            long id = mDataSource.getId();
            int index = (int) (id%mVideos.size());
            VideoBean bean = mVideos.get(index);
            mDataSource.setData(bean.getPath());
            mDataSource.setTitle(bean.getDisplayName());
            Bundle bundle = BundlePool.obtain();
            bundle.putSerializable(EventKey.SERIALIZABLE_DATA, mDataSource);
            //真正调用此方法将数据源提供给播放器
            onProviderMediaDataSuccess(bundle);
        }
    };

    @Override
    public void cancel() {
        mHandler.removeCallbacks(mLoadDataRunnable);
    }

    @Override
    public void destroy() {
        cancel();
    }
}