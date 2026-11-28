package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class CameraNoClip extends Module {

   public CameraNoClip() {
       super("CameraNoClip", "KameraNichtKlip", new Color(179, 157, 80), Categorys.RENDER);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }
}
