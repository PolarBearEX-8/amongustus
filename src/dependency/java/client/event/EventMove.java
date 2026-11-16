package client.event;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.setting.ArrowDodgerPhase;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventMove extends AbstractEvent {
   public boolean state5;
   public double coordOffset6;
   public static final int count = 21;
   public double value;
   public float scaleFactor7;
   public boolean state4;
   public boolean state1;
   public AuraRenderMode auraRenderMode = AuraRenderMode.NONE;
   public float scaleFactor;
   public float scaleFactor2;
   public boolean state;
   public boolean state3;
   public ArrowDodgerPhase arrowDodgerPhase;
   public double coordOffset;

   public double getCoordOffset6() {
      return this.coordOffset6;
   }

   public boolean isState1() {
      return this.state1;
   }

   public void setState1(boolean state2) {
      this.state1 = state2;
   }

   public AuraRenderMode getAuraRenderMode() {
      return this.auraRenderMode;
   }

   public void setAuraRenderMode(AuraRenderMode auraRenderMode2) {
      this.auraRenderMode = auraRenderMode2;
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public void applyMoveListener(MoveListener moveListener) {
      if (this.arrowDodgerPhase == ArrowDodgerPhase.PRE) {
         moveListener.onMove(this);
      } else {
         moveListener.updateState9();
      }
   }

   public void call(Object entry) {
      MoveListener moveListener = (MoveListener)entry;
      this.applyMoveListener(moveListener);
   }

   public void onMove(EventMove event) {
      this.scaleFactor2 = event.scaleFactor2;
      this.scaleFactor7 = event.scaleFactor7;
      this.state = event.state;
      this.state4 = event.state4;
      this.arrowDodgerPhase = event.arrowDodgerPhase;
   }

   public void setScaleFactor2(float scaleFactor3) {
      this.scaleFactor2 = scaleFactor3;
   }

   public boolean isState3() {
      return this.state3;
   }

   public float getScaleFactor() {
      return this.scaleFactor;
   }

   public void setCoordOffset6(double coordOffset2) {
      this.coordOffset6 = coordOffset2;
   }

   public void setScaleFactor(float scaleFactor3) {
      this.scaleFactor = scaleFactor3;
   }

   public void setScaleFactor7(float scaleFactor3) {
      this.scaleFactor7 = scaleFactor3;
   }

   public float getScaleFactor7() {
      return this.scaleFactor7;
   }

   public boolean isState5() {
      return this.state5;
   }

   public boolean isState4() {
      return this.state4;
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   public void setState4(boolean state2) {
      this.state4 = state2;
   }

   public float getScaleFactor2() {
      return this.scaleFactor2;
   }

   public boolean isState() {
      return this.state;
   }

   public void setCoordOffset(double coordOffset2) {
      this.coordOffset = coordOffset2;
   }

   public EventMove(float factor, float factor2, boolean flag, boolean flag2, ArrowDodgerPhase arrowDodgerPhase2) {
      this.state1 = false;
      this.coordOffset6 = 0.3;
      this.coordOffset = 0.3;
      this.value = Double.NaN;
      this.scaleFactor = Client.getClient().getViaProtocolHelper().scaleFactor1;
      this.scaleFactor2 = factor;
      this.scaleFactor7 = factor2;
      this.state5 = flag;
      this.state3 = flag2;
      this.state = flag;
      this.state4 = flag2;
      this.arrowDodgerPhase = arrowDodgerPhase2;
   }

   public EventMove(EventMove event) {
      this.state1 = false;
      this.coordOffset6 = 0.3;
      this.coordOffset = 0.3;
      this.value = Double.NaN;
      this.scaleFactor = Client.getClient().getViaProtocolHelper().scaleFactor1;
      this.scaleFactor2 = event.scaleFactor2;
      this.scaleFactor7 = event.scaleFactor7;
      this.coordOffset6 = event.coordOffset6;
      this.coordOffset = event.coordOffset;
      this.state = event.state;
      this.state4 = event.state4;
      this.state1 = event.state1;
      this.arrowDodgerPhase = event.arrowDodgerPhase;
   }
}
