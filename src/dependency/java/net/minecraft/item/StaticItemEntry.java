package net.minecraft.item;

import client.event.PreRenderInterceptor;

public class StaticItemEntry {
   public int count;
   public PreRenderInterceptor preRenderInterceptor;

   public PreRenderInterceptor getPreRenderInterceptor() {
      return this.preRenderInterceptor;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public void setPreRenderInterceptor(PreRenderInterceptor preRenderInterceptor2) {
      this.preRenderInterceptor = preRenderInterceptor2;
   }

   public int getCount() {
      return this.count;
   }
}
