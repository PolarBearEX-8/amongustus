package net.augustus.modules.movement;

import java.awt.Color;
import java.io.File;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.modes.movementrecorder.MovementReplayMode;
import net.augustus.modules.modes.movementrecorder.MovementRecordMode;
import net.augustus.settings.SettingGroup;

public class MovementRecorder extends Module {
   public static final String RECORDINGS_DIR = ClientPaths.CLIENT_DIR + File.separator + "movement_recordings";
   private final ModeSetting mode;

   public MovementRecorder() {
       super("MovementRecorder", "MovementAufzeichner", Color.GREEN, Categorys.MOVEMENT);
      BiFunction[] biFunctions = new BiFunction[]{
         (obj, obj2) -> new MovementRecordMode((MovementRecorder)obj, (SettingGroup)obj2),
         (obj3, obj4) -> new MovementReplayMode((MovementRecorder)obj3, (SettingGroup)obj4)
      };
      this.mode = new ModeSetting(this, "Mode", "Record", biFunctions);
   }

   @Override
   public void onEnable() {
      ((net.minecraft.util.Timer)null).timerSpeed = 1.0F;
   }

   @Override
   public void onDisable() {
      ((net.minecraft.util.Timer)null).timerSpeed = 1.0F;
   }

   @Override
   public void updateEventSubscriptions() {
   }

}
