package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.render.ObjModelLoader;
import net.augustus.settings.options.ModelRendererTargetOption;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelRenderer extends Module implements TickListener {
   public final BooleanValue renderArmor;
   private final MultiOptionSetting targets;
   private boolean modelLoadPending;

   public ModelRenderer() {
       super("ModelRenderer", "ModellZeichner", new Color(255, 234, 140), Categorys.RENDER);
      this.renderArmor = new BooleanValue(this, "RenderArmor", false);
      this.targets = new MultiOptionSetting(this, "Targets", ModelRendererTargetOption.values());
      this.modelLoadPending = false;
   }

   public void onEnable() {
      GlStateManager.bindTexture(GL11.glGenTextures());
      String str = "amongus.mtl";
      ObjModelLoader.loadObjModel("amongus.obj", str);
      this.modelLoadPending = true;
   }

   public void onDisable() {
      this.modelLoadPending = false;
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      if (this.modelLoadPending) {
         this.modelLoadPending = false;
         ObjModelLoader.compileDisplayLists();
      }
   }

   public boolean shouldRenderCustomModel(Entity entity) {
      if (!this.isToggled()) {
         return false;
      } else if (!(entity instanceof EntityPlayer)) {
         return false;
      } else {
         return entity instanceof EntityPlayerSP && ModelRendererTargetOption.ME.isSelected() ? true : ModelRendererTargetOption.PLAYERS.isSelected();
      }
   }

}
