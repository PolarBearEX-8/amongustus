package baritone.api;

import baritone.api.command.ICommandSystem;
import baritone.api.schematic.ISchematicSystem;
import baritone.api.cache.IWorldScanner;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;

public interface IBaritoneProvider {
   default IBaritone getBaritoneForMinecraft(Minecraft mc) {
      for (IBaritone baritone : (Iterable<IBaritone>)(this.getAllBaritones())) {
         if (Objects.equals(mc, baritone.getPlayerContext().getMc())) {
            return baritone;
         }
      }

      return null;
   }

   boolean destroyBaritone(IBaritone baritone);

   IWorldScanner getInstance3();

   default IBaritone getBaritoneForPlayer(EntityPlayerSP player) {
      for (IBaritone baritone : (Iterable<IBaritone>)(this.getAllBaritones())) {
         if (Objects.equals(player, baritone.getPlayerContext().player())) {
            return baritone;
         }
      }

      return null;
   }

   ICommandSystem getInstance2();

   IBaritone getPrimaryBaritone();

   List getAllBaritones();

   ISchematicSystem getInstance();

   default IBaritone getBaritoneForConnection(NetHandlerPlayClient netHandlerPlayClient) {
      for (IBaritone baritone : (Iterable<IBaritone>)(this.getAllBaritones())) {
         EntityPlayerSP player = baritone.getPlayerContext().player();
         if (player != null && player.sendQueue == netHandlerPlayClient) {
            return baritone;
         }
      }

      return null;
   }

   IBaritone createBaritone(Minecraft mc);
}
