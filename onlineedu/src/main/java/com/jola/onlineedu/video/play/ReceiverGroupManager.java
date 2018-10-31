package com.jola.onlineedu.video.play;

import android.content.Context;

import com.jola.onlineedu.video.cover.CompleteCover;
import com.jola.onlineedu.video.cover.ControllerCover;
import com.jola.onlineedu.video.cover.ErrorCover;
import com.jola.onlineedu.video.cover.GestureCover;
import com.jola.onlineedu.video.cover.LoadingCover;
import com.kk.taurus.playerbase.receiver.GroupValue;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;

import static com.jola.onlineedu.video.play.DataInter.ReceiverKey.KEY_COMPLETE_COVER;
import static com.jola.onlineedu.video.play.DataInter.ReceiverKey.KEY_CONTROLLER_COVER;
import static com.jola.onlineedu.video.play.DataInter.ReceiverKey.KEY_ERROR_COVER;
import static com.jola.onlineedu.video.play.DataInter.ReceiverKey.KEY_GESTURE_COVER;
import static com.jola.onlineedu.video.play.DataInter.ReceiverKey.KEY_LOADING_COVER;


public class ReceiverGroupManager {

    private static volatile ReceiverGroupManager instance;

    private ReceiverGroupManager() {
    }

    public static ReceiverGroupManager getInstance(){
        if (null == instance){
            synchronized (ReceiverGroupManager.class){
                if (null == instance){
                    instance = new ReceiverGroupManager();
                }
            }
        }
        return instance;
    }

    public ReceiverGroup getReceiverGroup(Context context){
        return getReceiverGroup(context,null);
    }

    private ReceiverGroup getReceiverGroup(Context context, GroupValue groupValue) {
        ReceiverGroup receiverGroup = new ReceiverGroup(groupValue);
        receiverGroup.addReceiver(KEY_LOADING_COVER,new LoadingCover(context));
        receiverGroup.addReceiver(KEY_CONTROLLER_COVER, new ControllerCover(context));
        receiverGroup.addReceiver(KEY_GESTURE_COVER, new GestureCover(context));
        receiverGroup.addReceiver(KEY_COMPLETE_COVER, new CompleteCover(context));
        receiverGroup.addReceiver(KEY_ERROR_COVER, new ErrorCover(context));
        return receiverGroup;
    }


}
