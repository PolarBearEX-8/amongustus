package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondOrb extends PassiveFloatEntry {
   public BoxesManager boxesManager3 = new BoxesManager(this, 0, 0);
   public BoxesManager boxesManager6;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager4;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager5.applyFloat3(posZ);
      this.boxesManager.applyFloat3(posZ);
      this.boxesManager7.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager3.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager3.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
      this.boxesManager7.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager2.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager4.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
   }

   public DiamondOrb(float factor) {
      this.boxesManager3.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor);
      this.boxesManager3.applyFloat(0.0F, (float)6, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 32, 0);
      this.boxesManager6.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor + 0.5F);
      this.boxesManager6.applyFloat(0.0F, (float)6, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 16, 16);
      this.boxesManager5.applyFloatByFloat(-4.0F, 0.0F, -2.0F, 8, 12, 4, factor);
      this.boxesManager5.applyFloat(0.0F, (float)6, 0.0F);
      this.boxesManager = new BoxesManager(this, 0, 16);
      this.boxesManager.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager.applyFloat(-2.0F, (float)18, 4.0F);
      this.boxesManager7 = new BoxesManager(this, 0, 16);
      this.boxesManager7.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager7.applyFloat(2.0F, (float)18, 4.0F);
      this.boxesManager2 = new BoxesManager(this, 0, 16);
      this.boxesManager2.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager2.applyFloat(-2.0F, (float)18, -4.0F);
      this.boxesManager4 = new BoxesManager(this, 0, 16);
      this.boxesManager4.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 6, 4, factor);
      this.boxesManager4.applyFloat(2.0F, (float)18, -4.0F);
   }

   public DiamondOrb() {
      this(0.0F);
   }
}
