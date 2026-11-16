package client.util;

import client.event.IndirectListener;

public interface Listener61Extension extends IndirectListener {
   BoxesManager resolveBoxesManager(String text);

   AngleAggregator resolveAngleAggregator(String text);
}
