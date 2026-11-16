package client.event;

import client.util.EnchantmentManager;

public interface PreUpdateNotifier {
   void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count);
}
