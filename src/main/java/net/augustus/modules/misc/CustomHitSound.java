package net.augustus.modules.misc;

import java.awt.Color;
import java.io.File;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.minecraft.entity.EntityLivingBase;

public class CustomHitSound extends Module implements AttackEntityListener {

   public CustomHitSound() {
       super("CustomHitSound", "KonfigurierbarerAngriffTon", new Color(66, 245, 191), Categorys.MISC);
      ClientFileUtil.ensureDirectory(ClientPaths.CLIENT_DIR + File.separator + "hitsounds");
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (event.getTarget() instanceof EntityLivingBase) {
         this.playHitSound();
      }
   }

   private void playHitSound() {
   }
}
