package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoOfCloudletsVsCloudletCompletionTimeSpaceShared extends BaseCase {

  public NoOfCloudletsVsCloudletCompletionTimeSpaceShared() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 68; i <= 178; i += 10) {
      Simulator simulator = new Simulator();
      simulator.setDataCenterHostPes(i);
      simulator.setVmCount(68);
      simulator.setCloudletCount(i);
      res.put(i, simulator.run());
    }

    return res;
  }
}
