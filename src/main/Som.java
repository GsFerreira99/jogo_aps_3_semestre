package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;

public class Som {

    File somUrl[] = new File[10];
    AudioInputStream audioStream = null;
    Clip clip;


    public Som() {
        somUrl[0] = new File("res/sound/menu.wav");
        somUrl[1] = new File("res/sound/musica_tema.wav");
        somUrl[2] = new File("res/sound/gameplay.wav");
        somUrl[3] = new File("res/sound/passos.wav");
        somUrl[4] = new File("res/sound/pegar_item.wav");
        somUrl[5] = new File("res/sound/PegarCarro.wav");
        somUrl[6] = new File("res/sound/SomCarro.wav");
        somUrl[7] = new File("res/sound/vilao.wav");
    }

    public void setSom(int i) {
        try{
            audioStream = AudioSystem.getAudioInputStream(somUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
        clip.flush();
        clip.close();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setVolume(Float volume){
        try{FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
