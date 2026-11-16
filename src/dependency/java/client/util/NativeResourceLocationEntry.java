package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import client.setting.ClientSettingsBlackType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.util.ResourceLocation;

public class NativeResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/entity/rabbit/brown.png");
   public static final ResourceLocation resourceLocation10 = new ResourceLocation("textures/entity/rabbit/white.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/rabbit/black.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/rabbit/gold.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/rabbit/salt.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
   public static final ResourceLocation resourceLocation8 = new ResourceLocation("textures/entity/rabbit/toast.png");
   public static final ResourceLocation resourceLocation9 = new ResourceLocation("textures/entity/rabbit/caerbannog.png");

   public ResourceLocation resolveResourceLocationByEntityRabbit(EntityRabbit entityRabbit) {
      String text = ClientSettingsBlackType.resolveText(entityRabbit.getText());
      if (text != null && text.equals("Toast")) {
         return resourceLocation8;
      } else {
         switch (entityRabbit.getCount11()) {
            case 0:
            default:
               return resourceLocation7;
            case 1:
               return resourceLocation10;
            case 2:
               return resourceLocation4;
            case 3:
               return resourceLocation5;
            case 4:
               return resourceLocation6;
            case 5:
               return resourceLocation3;
            case 99:
               return resourceLocation9;
         }
      }
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityRabbit((EntityRabbit)entity);
   }

   public NativeResourceLocationEntry(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
