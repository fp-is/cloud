package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import fp.is.cloud.Simulator;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VmRamVsCloudletCompletionTimeTimeShared extends BaseCase {

  public VmRamVsCloudletCompletionTimeTimeShared() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 512; i <= 5120; i+=512) {
      Simulator simulator = new Simulator();
      simulator.setVmRam(i);
      simulator.setCloudletCount(68);
      simulator.setVmCount(68);
      simulator.setDataCenterHostPes(68);
      simulator.setCloudletScheduler("timeShared");
      res.put(i, simulator.run());
    }

    return res;
  }
}
