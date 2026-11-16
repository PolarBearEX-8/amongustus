package client.util;

import java.util.Iterator;
import java.util.List;

public interface IteratorBridge extends Iterator {
   void applyList(List list);
}
