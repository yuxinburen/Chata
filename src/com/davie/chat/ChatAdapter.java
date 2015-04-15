package com.davie.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.davie.message.ChatMessage;

import java.util.List;

/**
 * User: davie
 * Date: 15-4-15
 */
public class ChatAdapter extends BaseAdapter {

    private List<ChatMessage> messages;
    private Context context;
    private final LayoutInflater inflater;

    public ChatAdapter(Context context, List<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        ret = messages.size();
        return ret;
    }

    @Override
    public Object getItem(int i) {
        Object ret = null;
        if (messages != null) {
            ret = messages.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = messages.get(position);
        int ret = 0;
        String to = message.getTo();
        if(to.equals("Me")){
            ret = 0;//代表左侧
        }else{
            ret = 1;//代表右侧
        }
        //
        Log.i("ChatAdapter",position+",type -> "+ret);
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        //TODO 需要展现界面, 实现两个布局共存的ListView

        Log.i("ChatAdapter","getView "+position+",cv : "+convertView);

        ChatMessage message = messages.get(position);
        String to = message.getTo();
        if(to.equals("Me")){
            //左侧
            if(convertView !=null){
                //如果复用的布局有,那么直接使用
                ret = convertView;
//                if(convertView instanceof LinearLayout){
//                    Log.i("ChatAdapter","Left convertView is LinearLayout");
//                }else {
//                    throw new RuntimeException("Type Error");
//                }
            }else {
                //没有复用,创建
                ret = inflater.inflate(R.layout.item_chat_left, parent,false);

            }

            //TODO 处理左侧
            LeftHolder holder = (LeftHolder)ret.getTag();
            if(holder==null){
                holder = new LeftHolder();
                holder.txtLeft  = (TextView) ret.findViewById(R.id.content_left);
                holder.txtNameLeft = (TextView) ret.findViewById(R.id.usrename_left);
                ret.setTag(holder);
            }

            //TODO 显示内容
            holder.txtLeft.setText(messages.get(position).getContent());
            holder.txtNameLeft.setText(messages.get(position).getFrom());

        }else {
            //右侧
            if(convertView !=null){
//                如果复用的布局有,则直接复用
                ret = convertView;
//                if(convertView instanceof RelativeLayout){
//                    Log.i("ChatAdapter","Right convertView is RelativeLayout");
//                }else {
//                    throw new RuntimeException("Type Error");
//                }
            }else {
                //没有复用,创建
                ret = inflater.inflate(R.layout.item_chat_right, parent,false);
            }

            //TODO处理右侧
            //右侧就用右侧的 RightHolder
            RightHolder holder = (RightHolder)ret.getTag();
            if (holder == null) {
                holder = new RightHolder();
                holder.txtRight = (TextView) ret.findViewById(R.id.content_right);
                holder.txtNameRight = (TextView) ret.findViewById(R.id.usrename_right);
                ret.setTag(holder);
            }

            //TODO 显示内容
            holder.txtRight.setText(messages.get(position).getContent());
            holder.txtNameRight.setText(messages.get(position).getFrom());

        }
        return ret;
    }


    private static class LeftHolder{
        public TextView txtNameLeft;
        public TextView txtLeft;
    }

    public static class RightHolder{
        public TextView txtNameRight;
        public TextView txtRight;
    }
}
