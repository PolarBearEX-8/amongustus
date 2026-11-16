package augustus.anticheat.checks;

import client.Client;
import client.rotation.Rotation;
import client.util.ClickHelper;
import client.util.InternalStringEntryHandler;
import client.util.Packet;

public class TrackAimCheck extends InternalStringEntryHandler {
   public final ClickHelper clickHelper2;
   public final ClickHelper clickHelper10;
   public final ClickHelper clickHelper8;
   public final ClickHelper clickHelper3 = new ClickHelper(40);
   public final ClickHelper clickHelper9;
   public final ClickHelper clickHelper5 = new ClickHelper(40);
   public final ClickHelper clickHelper7;
   public final ClickHelper clickHelper6;
   public final ClickHelper clickHelper;
   public final ClickHelper clickHelper4;

   @Override
   public void handlePacket(Packet packet) {
   }

   @Override
   public void applyRotation(
      Rotation rotation, Rotation rotation2, Rotation rotation3, Rotation rotation4, Rotation rotation5, Rotation rotation6, Rotation rotation7, Rotation rotation8, Rotation rotation9
   ) {
      Client.getClient().getClass();
   }

   public TrackAimCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.clickHelper9 = new ClickHelper(40);
      this.clickHelper4 = new ClickHelper(40);
      this.clickHelper2 = new ClickHelper(40);
      this.clickHelper7 = new ClickHelper(40);
      this.clickHelper6 = new ClickHelper(40);
      this.clickHelper10 = new ClickHelper(40);
      this.clickHelper = new ClickHelper(40);
      this.clickHelper8 = new ClickHelper(40);
   }
}
