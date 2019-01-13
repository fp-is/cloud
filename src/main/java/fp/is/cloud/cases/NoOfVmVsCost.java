package fp.is.cloud.cases;

import fp.is.cloud.Simulator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoOfVmVsCost {

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 68; i <= 178; i+=10) {
      Simulator simulator = new Simulator();
      simulator.setVmCount(i);
      res.put(i, simulator.run());
    }

    return res;
  }
}
