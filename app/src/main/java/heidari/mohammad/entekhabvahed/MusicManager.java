package heidari.mohammad.entekhabvahed;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import javaherian.yousef.entekhabvahed.R;

public class MusicManager extends Service {
    MediaPlayer player;
    private String TAG = null;

    @Override
    public void onCreate() {
        super.onCreate();
          player = MediaPlayer.create(this,R.raw.background_music_interlude_for_piano) ;
          player.setLooping(true); // Set looping
          player.setVolume(100,100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
