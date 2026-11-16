package client.util;

import java.util.Map;

public class ThrowableCallbackThread extends Thread {
   public String labelText;
   public Map map;
   public byte[] byteFlagArray;
   public ThrowableCallback throwableCallback;

   public String getLabelText() {
      return this.labelText;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void run() {
      try {
         PlayerModelsLocalManager.resolveText(this.labelText, this.map, this.byteFlagArray);
         this.throwableCallback.applyText(this.labelText, this.byteFlagArray, (Throwable)null);
      } catch (Exception exception) {
         this.throwableCallback.applyText(this.labelText, this.byteFlagArray, exception);
      }
   }

   public ThrowableCallback getThrowableCallback() {
      return this.throwableCallback;
   }

   public ThrowableCallbackThread(String text, Map map2, byte[] byteValueArray, ThrowableCallback throwableCallback2) {
      this.labelText = text;
      this.map = map2;
      this.byteFlagArray = byteValueArray;
      this.throwableCallback = throwableCallback2;
   }
}
