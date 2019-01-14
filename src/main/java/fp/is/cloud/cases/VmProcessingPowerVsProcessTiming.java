package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VmProcessingPowerVsProcessTiming extends BaseCase {

  public VmProcessingPowerVsProcessTiming() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 250; i <= 2500; i+=250) {
      Simulator simulator = new Simulator();
      simulator.setVmMips(i);
      simulator.setDataCenterHostMips(i);
      simulator.setCloudletCount(1);
      res.put(i, simulator.run());
    }

    return res;
  }
}
