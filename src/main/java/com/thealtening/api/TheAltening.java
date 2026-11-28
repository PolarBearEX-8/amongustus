package com.thealtening.api;


import com.thealtening.api.retriever.AsynchronousDataRetriever;
import com.thealtening.api.retriever.BasicDataRetriever;
public final class TheAltening {
   public static BasicDataRetriever newBasicRetriever(String str) {
      return new BasicDataRetriever(str);
   }

   public static AsynchronousDataRetriever newAsyncRetriever(String str) {
      return new AsynchronousDataRetriever(str);
   }
}
