package javazoom.jl.player.advanced;

public class PlaybackEvent {
   public static int STOPPED = 1;
   public static int STARTED = 2;
   private AdvancedPlayer source;
   private int frame;
   private int id;

   public PlaybackEvent(AdvancedPlayer advancedPlayer, int n, int n2) {
      this.id = n;
      this.source = advancedPlayer;
      this.frame = n2;
   }

   public void setFrame(int n) {
      this.frame = n;
   }

   public AdvancedPlayer getSource() {
      return this.source;
   }

   public void setSource(AdvancedPlayer advancedPlayer) {
      this.source = advancedPlayer;
   }

   public int getFrame() {
      return this.frame;
   }

   public void setId(int n) {
      this.id = n;
   }

   public int getId() {
      return this.id;
   }
}
