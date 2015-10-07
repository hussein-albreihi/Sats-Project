package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;


import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import se.tuppload.android.satstrainingapp.Adapter.ColoumnAdapter;
import se.tuppload.android.satstrainingapp.Adapter.TrainingListAdapter;

import static se.tuppload.android.satstrainingapp.R.layout.my_training_listview;

public class MainActivity extends ActionBarActivity
{
    public static boolean temp = false;
    long startEndDate = 4;
    final DateTime currentWeek = new DateTime();
    public int currentPage = currentWeek.getWeekOfWeekyear();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(my_training_listview);

        final ViewPager graph = (ViewPager) findViewById(R.id.graph);;
        final TextView txtStatus = (TextView) findViewById(R.id.activity_status);
        final ImageView im = (ImageView) findViewById(R.id.refresh);
        final Animation animRot = AnimationUtils.loadAnimation(this, R.anim.refresh);
        final StickyListHeadersListView listView = (StickyListHeadersListView) findViewById(R.id.listan);
        final ImageView left = (ImageView) this.findViewById(R.id.back_to_now_left);
        final ImageView right = (ImageView) this.findViewById(R.id.back_to_now_right);

        RequestJson.getJsonData(listView, this);
        graph.setAdapter(new ColoumnAdapter());

        //Hide Back To Now Markers
        left.setVisibility(View.GONE);
        right.setVisibility(View.GONE);

        graph.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                currentPage = position + 2;

                if(currentPage >= currentWeek.getWeekOfWeekyear() + 3){
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                } else if(!(currentPage >= currentWeek.getWeekOfWeekyear() + 3)) {
                    left.setVisibility(View.GONE);
                }

                if(currentPage <= currentWeek.getWeekOfWeekyear() - 3) {
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.GONE);
                } else if(!(currentPage <= currentWeek.getWeekOfWeekyear() - 3)) {
                    right.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestJson.getJsonData(listView, MainActivity.this);
                im.startAnimation(animRot);
                graph.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        graph.setAdapter(new ColoumnAdapter());
                        graph.setCurrentItem(currentWeek.getWeekOfWeekyear() - 2);
                    }
                }, 1500);
            }
        });

        listView.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View header, int i, long l) {
                TextView txt = (TextView) findViewById(R.id.date_header);
                DateTime d = new DateTime(TrainingListAdapter.getList().get(i).date);
                startEndDate = d.getMillis();
                if (d.isBeforeNow()) {
                    txtStatus.setText("TIDIGARE TRÄNING");
                } else {
                    txtStatus.setText("KOMMANDE TRÄNING");
                }
            }
        });

        graph.postDelayed(new Runnable() {
            @Override
            public void run() {
                graph.setCurrentItem(currentWeek.getWeekOfWeekyear() - 2);
            }
        }, 1500);

    }
}



