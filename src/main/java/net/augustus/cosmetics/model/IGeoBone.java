package net.augustus.cosmetics.model;

public interface IGeoBone {

   float getRotationX();

   void setPivotX(float f);

   default GeoBoneSnapshot saveSnapshot() {
      return new GeoBoneSnapshot(this);
   }

   void setHidden(boolean flag);

   void setName(String str);

   float getPivotY();

   void setRotationX(float f);

   float getPositionZ();

   float getScaleZ();

   float getScaleX();

   void setPositionY(float f);

   void setScaleY(float f);

   float getScaleY();

   float getRotationZ();

   float getPositionY();

   float getRotationY();

   float getPivotX();

   void setPositionX(float f);

   float getPivotZ();

   void setScaleX(float f);

   void setPivotZ(float f);

   float getPositionX();

   String getName();

   void setPositionZ(float f);

   void setScaleZ(float f);

   void saveInitialSnapshot();

   GeoBoneSnapshot getInitialSnapshot();

   void setRotationZ(float f);

   boolean isHidden();

   void setPivotY(float f);

   void setRotationY(float f);
}
