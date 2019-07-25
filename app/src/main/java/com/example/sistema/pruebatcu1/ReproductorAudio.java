package com.example.sistema.pruebatcu1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Esta clase se encarga de las operaciones relacionadas con el audio de la aplicación.
 *
 * Created by Sistema on 24/07/2019.
 */
public class ReproductorAudio {

    public ReproductorAudio()
    {

    }

    /**
     *
     * @param contexto El contexto de la aplicacion. Debe enviarse con un getApplicationContext()
     * @param recursoID El ID del audio a reproducir
     */
    public void reproducirAudio(Context contexto,int recursoID)
    {
        final MediaPlayer player = MediaPlayer.create(contexto, recursoID);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.release();
            }
        });

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

}
