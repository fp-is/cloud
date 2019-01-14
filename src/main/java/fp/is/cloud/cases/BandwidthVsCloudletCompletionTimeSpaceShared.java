package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BandwidthVsCloudletCompletionTimeSpaceShared extends BaseCase {

  public BandwidthVsCloudletCompletionTimeSpaceShared() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 1000; i <= 10000; i+=1000) {
      Simulator simulator = new Simulator();
      simulator.setVmBw(i);
      simulator.setCloudletCount(68);
      simulator.setVmCount(68);
      simulator.setDataCenterHostPes(68);
      res.put(i, simulator.run());
    }

    return res;
  }
}
