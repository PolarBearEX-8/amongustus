package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class RenderRabbit extends RenderLiving {
   private static final ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown.png");
   private static final ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/white.png");
   private static final ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black.png");
   private static final ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/gold.png");
   private static final ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/salt.png");
   private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
   private static final ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/toast.png");
   private static final ResourceLocation CAERBANNOG = new ResourceLocation("textures/entity/rabbit/caerbannog.png");

   public RenderRabbit(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityRabbit)entity);
   }

   public ResourceLocation getEntityTexture(EntityRabbit entityRabbit) {
      String str = EnumChatFormatting.getTextWithoutFormattingCodes(entityRabbit.getName());
      if (str != null && str.equals("Toast")) {
         return TOAST;
      } else {
         switch (entityRabbit.getRabbitType()) {
            case 0:
            default:
               return BROWN;
            case 1:
               return WHITE;
            case 2:
               return BLACK;
            case 3:
               return WHITE_SPLOTCHED;
            case 4:
               return GOLD;
            case 5:
               return SALT;
            case 99:
               return CAERBANNOG;
         }
      }
   }
}
