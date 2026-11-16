package client.util;

import net.minecraft.world.World;
import net.minecraft.world.WorldCustomProperty;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerAdapter extends WorldServer {
   public WorldServer worldServer;

   public WorldServerAdapter(VanillaRunnable vanillaRunnable, ISaveHandler iSaveHandler, int count, WorldServer worldServer2, UNKNOWNManager uNKNOWNManager) {
      super(vanillaRunnable, iSaveHandler, new RandomSeedManagerAdapter(worldServer2.getRandomSeedManager()), count, uNKNOWNManager);
      this.worldServer = worldServer2;
      worldServer2.getInternalListEntry().add(new AuxiliaryListenerAdapter5(this));
   }

   @Override
   public void updateState12() throws ExceptionAdapter {
   }

   @Override
   public World getWorld() {
      this.dataManager = this.worldServer.getDataManager();
      this.thePlayerNameManager = this.worldServer.getThePlayerNameManager();
      String text = WorldCustomProperty.resolveText(this.secondaryFloatArrayEntry);
      WorldCustomProperty worldCustomProperty = (WorldCustomProperty)this.dataManager.get(WorldCustomProperty.class, text);
      if (worldCustomProperty == null) {
         this.worldCustomProperty = new WorldCustomProperty(this);
         this.dataManager.put(text, this.worldCustomProperty);
      } else {
         this.worldCustomProperty = worldCustomProperty;
         this.worldCustomProperty.applyWorldByWorld(this);
      }

      return this;
   }
}
