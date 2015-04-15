package com.davie.chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.davie.message.ChatMessage;

import java.util.LinkedList;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private EditText chat_edt;
    private ListView chatListView;
    private LinkedList<ChatMessage> messages;
    private ChatAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        chat_edt = (EditText) findViewById(R.id.chat_edt);

        chatListView = (ListView) findViewById(R.id.chat_list);
        messages = new LinkedList<ChatMessage>();

//        for (int i = 0; i <100 ; i++) {
//            ChatMessage message  = new ChatMessage();
//            message.setContent("你好"+i);
//            message.setSendTime(System.currentTimeMillis());
//            if(i%3==0){
//                message.setFrom("Me");
//                message.setTo("Tom");
//            }else {
//                message.setFrom("Tom");
//                message.setTo("Me");
//            }
//            messages.add(message);
//        }
        adapter = new ChatAdapter(this, messages);
        chatListView.setAdapter(adapter);
    }

    public void btnSendMessage(View view){
        String message = chat_edt.getText().toString().trim();
        if(message.length()>0){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setFrom("Me");
            chatMessage.setTo("Tom");
            chatMessage.setContent(message);
            messages.add(chatMessage);
            adapter.notifyDataSetChanged();
            chat_edt.setText("");
            addMessage(message);
        }
    }

    public void addMessage(String message){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFrom("Tom");
        chatMessage.setTo("Me");
        if(message.equals("你好")){
            chatMessage.setContent("en,你好,最近好吗?");
        }else if(message.equals("你真漂亮")){
            chatMessage.setContent("别这样,伦家都不好意思了...");
        }else if(message.equals("我喜欢你")){
            chatMessage.setContent("Oh,真的吗.我好激动");
        }else if(message.equals("一起吃饭好吗")){
            chatMessage.setContent("恩恩,周末好吗,顺便一起看电影");
        }else {
            chatMessage.setContent("so sorry, 我还没有进化到那么智能,我会努力的...");
        }
        messages.add(chatMessage);
        adapter.notifyDataSetChanged();
    }
}
