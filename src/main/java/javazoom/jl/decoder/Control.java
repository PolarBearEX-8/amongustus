package javazoom.jl.decoder;

public interface Control {
   boolean isPlaying();

   boolean isRandomAccess();

   void start();

   void stop();

   void pause();

   void setPosition(double d);

   double getPosition();
}
