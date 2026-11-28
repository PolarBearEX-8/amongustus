package javazoom.jl.player.advanced;

public final class AdvancedPlayerThread extends Thread {
   public final AdvancedPlayer player;
   public final int start;
   public final int end;

   public AdvancedPlayerThread(AdvancedPlayer advancedPlayer, int n, int n2) {
      this.player = advancedPlayer;
      this.start = n;
      this.end = n2;
   }

   @Override
   public void run() {
      try {
         this.player.play(this.start, this.end);
      } catch (Exception e) {
         throw new RuntimeException(e.getMessage());
      }
   }
}
