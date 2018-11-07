package com.jola.shengfan.skills;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2018/11/2.
 */

public class is_bottom {


    public boolean isRecycleViewBottomWayOne(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
//        current view item that attached to RecycleView 可见的子项数
        int visibleItemCount = layoutManager.getChildCount();
        int itemCount = layoutManager.getItemCount();
        int scrollState = recyclerView.getScrollState();
//        if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition == itemCount - 1 && scrollState == RecyclerView.SCROLL_STATE_IDLE){
//            return true;
//        }
//        return false;
        return visibleItemCount > 0 && lastCompletelyVisibleItemPosition == itemCount - 1 && scrollState == RecyclerView.SCROLL_STATE_IDLE;
    }


    public boolean isRecycleViewBottomWayTwo(RecyclerView recyclerView){
        int verticalScrollRange = recyclerView.computeVerticalScrollRange();
        int verticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        int verticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        return verticalScrollExtent + verticalScrollOffset >= verticalScrollRange;
    }


//    equals to WayTwo above
    public boolean isRecycleViewBottomWayThree(RecyclerView recyclerView){
        return recyclerView.canScrollVertically(-1);
    }


}
