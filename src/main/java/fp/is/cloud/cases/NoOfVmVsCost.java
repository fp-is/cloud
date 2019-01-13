package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoOfVmVsCost extends BaseCase {

  public NoOfVmVsCost() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 68; i <= 178; i+=10) {
      Simulator simulator = new Simulator();
      simulator.setDataCenterHosts(10);
      simulator.setVmCount(i);
      res.put(i, simulator.run());
    }

    return res;
  }
}
