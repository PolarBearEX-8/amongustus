package client.event;

public interface PassiveListener {
   void applyFloat(float factor);

   void applyFloat(float posX, float posY, float posZ);

   void updateState();

   void applyFloat2(float posX, float posY, float posZ);

   void applyFloat2(float factor);

   default Object getTarget() {
      return null;
   }

   void updateState2();

   void applyFloatByFloat(float factor);

   void applyFloatByFloat(float posX, float posY, float posZ);
}
