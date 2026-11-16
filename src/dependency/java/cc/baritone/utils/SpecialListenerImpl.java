package cc.baritone.utils;

import java.util.List;

public interface SpecialListenerImpl extends SpecialListener {
   List getList();

   String resolveTextByText(String text);

   int computeCount(String text);
}
