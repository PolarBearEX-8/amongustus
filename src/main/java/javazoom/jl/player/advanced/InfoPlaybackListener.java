package javazoom.jl.player.advanced;


public class InfoPlaybackListener extends PlaybackListener {
   public final Jlap jlap;

   @Override
   public void playbackStarted(PlaybackEvent playbackEvent) {
      System.out.println("Play started from frame " + playbackEvent.getFrame());
   }

   public InfoPlaybackListener(Jlap jlap2) {
      this.jlap = jlap2;
   }

   @Override
   public void playbackFinished(PlaybackEvent playbackEvent) {
      System.out.println("Play completed at frame " + playbackEvent.getFrame());
      System.exit(0);
   }
}
