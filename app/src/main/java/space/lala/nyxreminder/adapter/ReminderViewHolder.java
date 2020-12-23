package space.lala.nyxreminder.adapter;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Date;

import space.lala.nyxreminder.OnReminderListener;
import space.lala.nyxreminder.R;
import space.lala.nyxreminder.model.ReminderModel;


public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private final TextView title;
    private final TextView description;
    private TextView date;
    private TextView time;
    private OnReminderListener onReminderListener;
    private int reminderId;

    public ReminderViewHolder(@NonNull View itemView, OnReminderListener onReminderListener) {
        super(itemView);
        title = itemView.findViewById(R.id.view_title);
        description = itemView.findViewById(R.id.view_description);
        date = itemView.findViewById(R.id.view_date);
        time = itemView.findViewById(R.id.view_time);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.onReminderListener = onReminderListener;
    }

    public void setItemContent(ReminderModel model) {
        this.reminderId = model.getId();

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String dateString = dateFormatter.format(model.getDateTime());
        String timeString = timeFormatter.format(model.getDateTime());

        title.setText(model.getTitle());
        description.setText(model.getDescription());
        date.setText(dateString);
        time.setText(timeString);

        if (model.isSelected()) {
            itemView.setBackgroundColor(itemView.getResources().getColor(R.color.light_blue));
        } else {
            itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void onClick(View view) {
        onReminderListener.onReminderClick(reminderId);
    }


    @Override
    public boolean onLongClick(View view) {
        onReminderListener.onReminderLongClick(reminderId);
        return true;
    }
}
