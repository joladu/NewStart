package com.jola.onlineedu.ui.activity;


import android.support.annotation.NonNull;
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
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumComments;
import com.jola.onlineedu.mode.bean.response.ResForumDetailBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.ui.adapter.ForumImagesGridViewAdapter;
import com.jola.onlineedu.ui.adapter.ForumListDetailAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
    @BindView(R.id.tv_forum_is_essence)
    TextView tv_forum_is_essence;
    @BindView(R.id.tv_forum_is_hot)
    TextView tv_forum_is_hot;
    @BindView(R.id.tv_forum_is_new)
    TextView tv_forum_is_new;

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

    private List<ResForumComments.DataBean.CommentsBean> mListComments;
//    int mStartIndex = 1;

    private ForumImagesGridViewAdapter forumImagesGridViewAdapter;
    private ForumListDetailAdapter forumListDetailAdapter;
    private int id;
    private int is_essence;
    private int is_hot;
    private int is_new;
    private String author;
    private String describeContent;
    private int page = 1;
    private int pageSize = 10;

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
            dataManager.commentForum(dataManager.getUserToken(),id+"",commentContent)
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

        id = getIntent().getIntExtra("id",0);
        is_essence = getIntent().getIntExtra("is_essence",0);
        is_hot = getIntent().getIntExtra("is_hot",0);
        is_new = getIntent().getIntExtra("is_new",0);

        author = getIntent().getStringExtra("author");
        describeContent = getIntent().getStringExtra("describeContent");

        getForumDetail();

        getForumCommentsList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getForumCommentsListMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getForumCommentsList();
            }
        });


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
                                tv_forum_is_essence.setVisibility(View.VISIBLE);
                            }
                            if (is_hot == 1){
                                tv_forum_is_hot.setVisibility(View.VISIBLE);
                            }
                            if (is_new == 1){
                                tv_forum_is_new.setVisibility(View.VISIBLE);
                            }
                            tv_forum_content.setText(data.getTitle());

                            ResForumDetailBean.DataBean.UserBean user = resForumDetailBean.getData().getUser();
                            ImageLoader.load(ForumDetailActivity.this,user.getAvatar_url(),ci_head_img);
                            tv_name_author.setText(author);
                            tv_describe_content.setText(describeContent);

//                            List<ResForumDetailBean.DataBean.PostBean.ImagesBean> images1 = data.getImages();
                            List<ResForumDetailBean.DataBean.PostBean.ImagesBean> images = data.getImages();
                            if (null != images && images.size() > 0){
                                forumImagesGridViewAdapter = new ForumImagesGridViewAdapter(ForumDetailActivity.this, images);
                                gridView.setAdapter(forumImagesGridViewAdapter);
                                gridView.setVisibility(View.VISIBLE);
                            }else{
                                gridView.setVisibility(View.INVISIBLE);
                            }

//                            mListComments = data.getComments();
//                            tv_num_comments.setText(mListComments.size());

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


    private void getForumCommentsList(){
        page = 1;
        addSubscribe(dataManager.getForumComments(id+"",page,pageSize)
            .compose(RxUtil.<ResForumComments>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumComments>() {
                    @Override
                    public void accept(ResForumComments resForumComments) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        int error_code = resForumComments.getError_code();
                        if (error_code == 0){
                           mListComments = resForumComments.getData().getComments();
                            int commnetNums = resForumComments.getData().getPageCount();
                            tv_num_comments.setText(commnetNums+"");
                            forumListDetailAdapter = new ForumListDetailAdapter(ForumDetailActivity.this, mListComments,dataManager);
                            recyclerView.setAdapter(forumListDetailAdapter);
                        }else{
                            ToastUtil.toastShort("获取评论失败！");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishRefresh();
                        tipServerError();
                    }
                })
        );
    }

    private void getForumCommentsListMore(){
        page++;
        addSubscribe(dataManager.getForumComments(id+"",page,pageSize)
                .compose(RxUtil.<ResForumComments>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumComments>() {
                    @Override
                    public void accept(ResForumComments resForumComments) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        int error_code = resForumComments.getError_code();
                        if (error_code == 0){
                            List<ResForumComments.DataBean.CommentsBean> comments = resForumComments.getData().getComments();
                            if (null == comments || comments.size() == 0){
                                ToastUtil.toastShort("暂无更多评论");
                            }else{
                                mListComments.addAll(comments);
                                forumImagesGridViewAdapter.notifyDataSetChanged();
                            }
                        }else{
                            ToastUtil.toastShort("获取评论失败！");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        smartRefreshLayout.finishLoadMore();
                        tipServerError();
                    }
                })
        );
    }





}
