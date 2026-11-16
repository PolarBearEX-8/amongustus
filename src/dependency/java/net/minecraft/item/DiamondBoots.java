package net.minecraft.item;

import client.util.DampenerCollector;
import client.util.FileHolder;
import client.util.ProviderClientManager;
import client.util.SessionEntry;
import client.util.SmoothFactory;

public class DiamondBoots {
   public final SessionEntry sessionEntry;
   public final ProviderClientManager providerClientManager;
   public final FileHolder fileHolder;
   public final DampenerCollector dampenerCollector;
   public final SmoothFactory smoothFactory;

   public DiamondBoots(SessionEntry sessionEntry2, ProviderClientManager providerClientManager2, FileHolder fileHolder2, DampenerCollector dampenerCollector2, SmoothFactory smoothFactory2) {
      this.sessionEntry = sessionEntry2;
      this.providerClientManager = providerClientManager2;
      this.fileHolder = fileHolder2;
      this.dampenerCollector = dampenerCollector2;
      this.smoothFactory = smoothFactory2;
   }
}
