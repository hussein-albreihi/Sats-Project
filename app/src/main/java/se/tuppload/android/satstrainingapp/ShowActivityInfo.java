package se.tuppload.android.satstrainingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.google.android.youtube.player.YouTubePlayer.*;

public class ShowActivityInfo extends YouTubeBaseActivity implements OnInitializedListener {

    public YouTubePlayer player;
    public static final String GOOGLE_API_KEY = "AIzaSyDOdUDNDMIYt1Br8g-T4_hzU2YMcNfPQok";
    public static final String VIDEO_ID = "4GBrCy1Uolo";
    public static String youTubeUrl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_view);
        Bundle extras = getIntent().getExtras();

        YouTubePlayerView YouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        YouTubePlayerView.initialize(GOOGLE_API_KEY, this);

        int min = 0;
        int max = 5;
        int randNumber = min + new Random().nextInt(max - min + 1);


        TextView className = (TextView) findViewById(R.id.class_name);
        TextView duration = (TextView) findViewById(R.id.class_duration_time);
        TextView center = (TextView) findViewById(R.id.center_name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView instructor = (TextView) findViewById(R.id.class_view_instructor);
        TextView description = (TextView) findViewById(R.id.class_information);
        TextView positionInQue = (TextView) findViewById(R.id.number_participants_class_info);
        TextView bookedPersonCount = (TextView) findViewById(R.id.bookedPersonCount);
        ImageView positionQueImage = (ImageView) findViewById(R.id.img_participants_class_info);

        ProgressBar cardio = (ProgressBar) findViewById(R.id.fitness_bar_cardio);
        ProgressBar strength = (ProgressBar) findViewById(R.id.fitness_bar_strength);
        ProgressBar flexibility = (ProgressBar) findViewById(R.id.fitness_bar_flexibility);
        ProgressBar balance = (ProgressBar) findViewById(R.id.fitness_bar_balance);
        ProgressBar agility = (ProgressBar) findViewById(R.id.fitness_bar_agility);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.sats_rating);

        className.setText(extras.getString("CLASS_TYPE"));
        duration.setText(extras.getString("DURATION"));
        center.setText(extras.getString("CENTER"));
        date.setText(extras.getString("DATE"));
        instructor.setText(extras.getString("INSTRUCTOR"));
        description.setText(extras.getString("DESCRIPTION"));
        positionInQue.setText(extras.getString("POSITIONQUE"));
        bookedPersonCount.setText("" + extras.getInt("PARTICIPANTS") + " deltagare av max " + extras.getInt("MAXPARTICIPANTS"));

        if (positionInQue.getText().equals("0")) {
            positionInQue.setVisibility(View.GONE);
            positionQueImage.setVisibility(View.GONE);
        }

        cardio.setProgress(extras.getInt("CARDIO"));
        strength.setProgress(extras.getInt("STRENGTH"));
        flexibility.setProgress(extras.getInt("FLEXIBILITY"));
        balance.setProgress(extras.getInt("BALANCE"));
        agility.setProgress(extras.getInt("AGILITY"));


        youTubeUrl = extras.getString("VIDEO_URL");

        ratingBar.setRating(randNumber);

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failed to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        Log.d("VIDEO ID: ", extractYouTubeId(youTubeUrl));
        if (!wasRestored) {
            player.cueVideo(extractYouTubeId(youTubeUrl));
        }
    }

    @Override //reconfigure display properties on screen rotation
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        //Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            player.setFullscreen(false);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            player.setFullscreen(true);
        }
    }


    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };

    public static String extractYouTubeId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(".*(?:youtu.be\\/|v\\/|e\\/|u\\/\\w\\/|embed\\/|watch\\?v=|\\?v=|v=)([\\w\\-]{11,}).*");
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;
    }
}
