package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VmImageSizeVsCost extends BaseCase {

  public VmImageSizeVsCost() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 256; i <= 2048; i+=256) {
      Simulator simulator = new Simulator();
      simulator.setVmSize(i);
      simulator.setCloudletCount(1);
      res.put(i, simulator.run());
    }

    return res;
  }
}
