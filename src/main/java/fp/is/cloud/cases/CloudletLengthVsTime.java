package fp.is.cloud.cases;

import fp.is.cloud.BaseCase;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fp.is.cloud.Simulator;

public class CloudletLengthVsTime extends BaseCase {

  public CloudletLengthVsTime() throws FileNotFoundException {
    this.writeFile(this.run());
  }

  public Map<Integer, List<Map<String, Double>>> run() {
    Map<Integer, List<Map<String, Double>>> res = new HashMap();
    for (int i = 40000; i <= 160000; i += 10000) {
      Simulator simulator = new Simulator();
      simulator.setCloudletCount(1);
      simulator.setCloudletLength(i);
      res.put(i, simulator.run());
    }

    return res;
  }
}
