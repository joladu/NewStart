package com.jola.onlineedu.ui.activity;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.ui.adapter.ForumImagesGridViewAdapter;
import com.jola.onlineedu.ui.adapter.ForumListDetailAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class ForumDetailActivity extends SimpleActivity {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;

    @BindView(R.id.tv_title_forum)
    TextView tv_title_forum;
    @BindView(R.id.tv_forum_tag)
    TextView tv_forum_tag;
    @BindView(R.id.tv_forum_content)
    TextView tv_forum_content;
    @BindView(R.id.gv_forum_imgs)
    GridView gridView;
    @BindView(R.id.ci_head_img)
    CircleImageView ci_head_img;
    @BindView(R.id.tv_name_author)
    TextView tv_name_author;
    @BindView(R.id.tv_describe_content)
    TextView tv_describe_content;
    @BindView(R.id.tv_num_comments)
    TextView tv_num_comments;
    @BindView(R.id.et_input_comment)
    EditText et_input_comment;

    @BindView(R.id.srl_forum_comment_list)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    private List<ResForumDetailBean.DataBean.PostBean.CommentsBean> mListComments;
//    int mStartIndex = 1;

    private ForumImagesGridViewAdapter forumImagesGridViewAdapter;
    private ForumListDetailAdapter forumListDetailAdapter;
    private int id;
    private int is_essence;
    private String author;
    private String describeContent;

    @Override
    protected int getLayout() {
        return R.layout.activity_forum_detail;
    }


    @OnClick({R.id.tv_send_comment})
    public void doClick(View view){
        String commentContent = et_input_comment.getText().toString();
        if (commentContent.length() == 0){
            ToastUtil.toastShort(getString(R.string.tip_input_comment));
        }else{
            showLoadingDialog();
            dataManager.commentForum(id+"",commentContent)
                    .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                    .subscribe(new Consumer<ResponseSimpleResult>() {
                        @Override
                        public void accept(ResponseSimpleResult responseSimpleResult) throws Exception {
                            hideLoadingDialog();
                            if (responseSimpleResult.getError_code() == 0){
                                ToastUtil.toastShort(getString(R.string.tip_comment_success));
                            }else{
                                ToastUtil.toastShort(responseSimpleResult.getError_msg());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            hideLoadingDialog();
                            tipServerError();
                        }
                    });
        }
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar,getString(R.string.forum_detail));
        getActivityComponent().inject(this);
        tv_forum_tag.setVisibility(View.INVISIBLE);

        id = getIntent().getIntExtra("id",0);
        is_essence = getIntent().getIntExtra("is_essence",0);
        author = getIntent().getStringExtra("author");
        describeContent = getIntent().getStringExtra("describeContent");

        getForumDetail();


//        模拟帖子有图片数据
//        ArrayList<String> imageList = new ArrayList<>();
//        imageList.add("url001");
//        imageList.add("url001");
//        imageList.add("url001");
//        forumImagesGridViewAdapter = new ForumImagesGridViewAdapter(this, imageList);
//        gridView.setAdapter(forumImagesGridViewAdapter);
//        gridView.setVisibility(View.VISIBLE);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

//        模拟评论数据
//        mListComments = new ArrayList<>();
//        for (int i= mStartIndex;i < mStartIndex + 10;i++){
//            mListComments.add("第"+i+"条测试：薛定谔的猫，到底藏在哪里，课程讲的非常好支持支持支持，支持");
//        }
//        forumListDetailAdapter = new ForumListDetailAdapter(this, mListComments);
//        recyclerView.setAdapter(forumListDetailAdapter);

//        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                for (int i= mStartIndex;i < mStartIndex + 10;i++){
//                    mListComments.add("第"+i+"条测试：薛定谔的猫，到底藏在哪里，课程讲的非常好支持支持支持，支持");
//                }
//                mStartIndex += 10;
//                smartRefreshLayout.finishLoadMore(2000);
//                forumListDetailAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mListComments.clear();
//                mStartIndex = 1;
//                for (int i= mStartIndex;i < mStartIndex + 10;i++){
//                    mListComments.add("每天观看直播节目，你们一般会评论什么，内容呢？：第"+i+"条");
//                }
//                mStartIndex += 10;
//
//                smartRefreshLayout.finishRefresh(2000);
//                forumListDetailAdapter.notifyDataSetChanged();
//
//            }
//        });


    }

    private void getForumDetail() {
        showLoadingDialog();
         addSubscribe(dataManager.getForumDetail(id+"")
            .compose(RxUtil.<ResForumDetailBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumDetailBean>() {
                    @Override
                    public void accept(ResForumDetailBean resForumDetailBean) throws Exception {
                        hideLoadingDialog();
                        if (resForumDetailBean.getError_code() == 0){
                            ResForumDetailBean.DataBean.PostBean data = resForumDetailBean.getData().getPost();
                            tv_title_forum.setText(data.getTitle());
                            if (is_essence == 1){
                                tv_forum_tag.setText(getString(R.string.forum_essence));
                                tv_forum_tag.setVisibility(View.VISIBLE);
                            }
                            tv_forum_content.setText(data.getTitle());
                            List<String> images = data.getImages();
                            if (null != images && images.size() > 0){
                                forumImagesGridViewAdapter = new ForumImagesGridViewAdapter(ForumDetailActivity.this, images);
                                gridView.setAdapter(forumImagesGridViewAdapter);
                                gridView.setVisibility(View.VISIBLE);
                            }else{
                                gridView.setVisibility(View.INVISIBLE);
                            }
                            tv_name_author.setText(author);
                            tv_describe_content.setText(describeContent);
                            mListComments = data.getComments();
                            tv_num_comments.setText(mListComments.size());



                            forumListDetailAdapter = new ForumListDetailAdapter(ForumDetailActivity.this, mListComments,dataManager);
                            recyclerView.setAdapter(forumListDetailAdapter);


                        }else{
                            ToastUtil.toastLong(resForumDetailBean.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                })
        );

    }

}
