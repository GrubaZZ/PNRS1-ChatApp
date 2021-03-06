package aleksandar.gruba.chatapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter {

    private Context cContext;
    private ArrayList<MessageClass> arlstMessages;

    public MessageListAdapter(Context context){
        cContext = context;
        arlstMessages = new ArrayList<MessageClass>();
    }

    public void addMessagesClass(MessageClass message){
        arlstMessages.add(message);
        notifyDataSetChanged();
    }

    public void removeMessagesClass(int position){

    arlstMessages.remove(position);
    notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return arlstMessages.size();
    }

    @Override
    public Object getItem(int position) {
        Object contact = null;
        try{
            contact = arlstMessages.get(position);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_message, null);

            MessageListAdapter.MessageHolder holder = new MessageListAdapter.MessageHolder();
            holder.tvMessage = (TextView) view.findViewById(R.id.tv_message);
            holder.tvTime = (TextView) view.findViewById(R.id.message_time);

            view.setTag(holder);
        }

        MessageClass messageclass = (MessageClass) getItem(position);
        MessageListAdapter.MessageHolder holder = (MessageListAdapter.MessageHolder) view.getTag();


        holder.tvMessage.setText(messageclass.getsMessage());
        holder.sUser = messageclass.getsUser();

        // Setting time
        holder.tvTime.setText(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));

        // Setting text and background colors and gravity based on user
        if (holder.sUser.contentEquals("User")){
            holder.tvMessage.setGravity(Gravity.RIGHT|Gravity.CENTER);
            holder.tvMessage.setTextColor(Color.rgb(255, 255,255));
            holder.tvMessage.setBackgroundColor(Color.argb(50,173,173,173));
            holder.tvTime.setGravity(Gravity.RIGHT);
            holder.tvTime.setTextColor(Color.rgb(255, 255,255));
            holder.tvTime.setBackgroundColor(Color.argb(50,173,173,173));
        }

        if (holder.sUser.equals("Bot")){
            holder.tvMessage.setGravity(Gravity.LEFT|Gravity.CENTER);
            holder.tvMessage.setTextColor(Color.rgb(234, 117,0));
            holder.tvMessage.setBackgroundColor(Color.argb(0,255,255,255));
            holder.tvTime.setGravity(Gravity.LEFT);
            holder.tvTime.setTextColor(Color.rgb(234, 117,0));
            holder.tvTime.setBackgroundColor(Color.argb(0,255,255,255));
        }

        return view;
    }

    private class MessageHolder{
        public TextView tvMessage = null;
        public String sUser = null;
        public TextView tvTime = null;
    }
}


