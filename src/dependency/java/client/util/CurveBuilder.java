package client.util;

import client.event.PostUpdateInterceptor;

public class CurveBuilder {
   public VectorCounter vectorCounter;
   public VectorCounter vectorCounter9;
   public VectorCounter vectorCounter2;
   public VectorCounter vectorCounter4;
   public VectorCounter vectorCounter3;
   public VectorCounter vectorCounter5;
   public VectorCounter vectorCounter8;
   public VectorCounter vectorCounter7;
   public final PostUpdateInterceptor postUpdateInterceptor;
   public VectorCounter vectorCounter6 = new VectorCounter();

   public CurveBuilder(PostUpdateInterceptor postUpdateInterceptor2) {
      this.vectorCounter3 = new VectorCounter();
      this.vectorCounter2 = new VectorCounter();
      this.vectorCounter5 = new VectorCounter();
      this.vectorCounter8 = new VectorCounter();
      this.vectorCounter = new VectorCounter();
      this.vectorCounter4 = new VectorCounter();
      this.vectorCounter9 = new VectorCounter();
      this.vectorCounter7 = new VectorCounter();
      this.postUpdateInterceptor = postUpdateInterceptor2;
   }
}
