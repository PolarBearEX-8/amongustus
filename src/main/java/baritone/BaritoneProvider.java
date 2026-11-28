package baritone;

import baritone.api.IBaritone;
import baritone.api.IBaritoneProvider;
import baritone.api.command.ICommandSystem;
import baritone.api.schematic.ISchematicSystem;
import baritone.cache.WorldScanner;
import baritone.command.CommandSystem;
import baritone.api.cache.IWorldScanner;
import baritone.command.ExampleBaritoneControl;
import baritone.utils.schematic.SchematicSystem;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;

public final class BaritoneProvider implements IBaritoneProvider {
   private final List all = new CopyOnWriteArrayList();
   private final List allView = Collections.unmodifiableList(this.all);

   @Override
   public ISchematicSystem getInstance() {
      return SchematicSystem.INSTANCE;
   }

   @Override
   public List getAllBaritones() {
      return this.allView;
   }

   @Override
   public synchronized boolean destroyBaritone(IBaritone baritone) {
      return baritone != this.getPrimaryBaritone() && this.all.remove(baritone);
   }

   @Override
   public ICommandSystem getInstance2() {
      return CommandSystem.INSTANCE;
   }

   @Override
   public IWorldScanner getInstance3() {
      return WorldScanner.INSTANCE;
   }

   @Override
   public IBaritone getPrimaryBaritone() {
      return (IBaritone)this.all.get(0);
   }

   public BaritoneProvider() {
      ((Baritone)this.createBaritone(Minecraft.getMinecraft())).registerBehavior2(ExampleBaritoneControl::new);
   }

   @Override
   public synchronized IBaritone createBaritone(Minecraft mc) {
      Object baritoneForMinecraft = this.getBaritoneForMinecraft(mc);
      if (baritoneForMinecraft == null) {
         this.all.add(baritoneForMinecraft = new Baritone(mc));
      }

      return (IBaritone)baritoneForMinecraft;
   }
}
