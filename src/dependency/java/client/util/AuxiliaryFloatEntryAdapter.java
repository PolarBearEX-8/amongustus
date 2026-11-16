package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBat;
import net.minecraft.util.MathHelper;

public class AuxiliaryFloatEntryAdapter extends PassiveFloatEntry {
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager5.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      if (((EntityBat)entity).isActive29()) {
         this.boxesManager3.factor = posY / (180.0F / (float)Math.PI);
         this.boxesManager3.factor9 = (float) Math.PI - posX4 / (180.0F / (float)Math.PI);
         this.boxesManager3.factor8 = (float) Math.PI;
         this.boxesManager3.applyFloat(0.0F, -2.0F, 0.0F);
         this.boxesManager.applyFloat(-3.0F, 0.0F, 3.0F);
         this.boxesManager4.applyFloat(3.0F, 0.0F, 3.0F);
         this.boxesManager5.factor = (float) Math.PI;
         this.boxesManager.factor = (float) (-Math.PI / 20);
         this.boxesManager.factor9 = (float) (-Math.PI * 2.0 / 5.0);
         this.boxesManager6.factor9 = -1.7278761F;
         this.boxesManager4.factor = this.boxesManager.factor;
         this.boxesManager4.factor9 = -this.boxesManager.factor9;
         this.boxesManager2.factor9 = -this.boxesManager6.factor9;
      } else {
         this.boxesManager3.factor = posY / (180.0F / (float)Math.PI);
         this.boxesManager3.factor9 = posX4 / (180.0F / (float)Math.PI);
         this.boxesManager3.factor8 = 0.0F;
         this.boxesManager3.applyFloat(0.0F, 0.0F, 0.0F);
         this.boxesManager.applyFloat(0.0F, 0.0F, 0.0F);
         this.boxesManager4.applyFloat(0.0F, 0.0F, 0.0F);
         this.boxesManager5.factor = (float) (Math.PI / 4) + MathHelper.cos(posX3 * 0.1F) * 0.15F;
         this.boxesManager5.factor9 = 0.0F;
         this.boxesManager.factor9 = MathHelper.cos(posX3 * 1.3F) * (float) Math.PI * 0.25F;
         this.boxesManager4.factor9 = -this.boxesManager.factor9;
         this.boxesManager6.factor9 = this.boxesManager.factor9 * 0.5F;
         this.boxesManager2.factor9 = -this.boxesManager.factor9 * 0.5F;
      }
   }

   public AuxiliaryFloatEntryAdapter() {
      this.count2 = 64;
      this.count = 64;
      this.boxesManager3 = new BoxesManager(this, 0, 0);
      this.boxesManager3.resolveBoxesManager(-3.0F, -3.0F, -3.0F, 6, 6, 6);
      BoxesManager boxesManager7 = new BoxesManager(this, 24, 0);
      boxesManager7.resolveBoxesManager(-4.0F, -6.0F, -2.0F, 3, 4, 1);
      this.boxesManager3.applyBoxesManager(boxesManager7);
      BoxesManager boxesManager8 = new BoxesManager(this, 24, 0);
      boxesManager8.enabled = true;
      boxesManager8.resolveBoxesManager(1.0F, -6.0F, -2.0F, 3, 4, 1);
      this.boxesManager3.applyBoxesManager(boxesManager8);
      this.boxesManager5 = new BoxesManager(this, 0, 16);
      this.boxesManager5.resolveBoxesManager(-3.0F, 4.0F, -3.0F, 6, 12, 6);
      this.boxesManager5.resolveBoxesManagerByIndex(0, 34).resolveBoxesManager(-5.0F, 16.0F, 0.0F, 10, 6, 1);
      this.boxesManager = new BoxesManager(this, 42, 0);
      this.boxesManager.resolveBoxesManager(-12.0F, 1.0F, 1.5F, 10, 16, 1);
      this.boxesManager6 = new BoxesManager(this, 24, 16);
      this.boxesManager6.applyFloat(-12.0F, 1.0F, 1.5F);
      this.boxesManager6.resolveBoxesManager(-8.0F, 1.0F, 0.0F, 8, 12, 1);
      this.boxesManager4 = new BoxesManager(this, 42, 0);
      this.boxesManager4.enabled = true;
      this.boxesManager4.resolveBoxesManager(2.0F, 1.0F, 1.5F, 10, 16, 1);
      this.boxesManager2 = new BoxesManager(this, 24, 16);
      this.boxesManager2.enabled = true;
      this.boxesManager2.applyFloat(12.0F, 1.0F, 1.5F);
      this.boxesManager2.resolveBoxesManager(0.0F, 1.0F, 0.0F, 8, 12, 1);
      this.boxesManager5.applyBoxesManager(this.boxesManager);
      this.boxesManager5.applyBoxesManager(this.boxesManager4);
      this.boxesManager.applyBoxesManager(this.boxesManager6);
      this.boxesManager4.applyBoxesManager(this.boxesManager2);
   }
}
