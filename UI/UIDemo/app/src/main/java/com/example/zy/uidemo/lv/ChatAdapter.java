package com.example.zy.uidemo.lv;

import android.content.Context;

import com.example.zy.uidemo.ChatMessage;
import com.zhy.adapter.abslistview.MultiItemTypeAdapter;


import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage> {
    public ChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }

}
