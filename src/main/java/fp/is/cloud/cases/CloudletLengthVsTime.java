package fp.is.cloud.cases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fp.is.cloud.Simulator;

public class CloudletLengthVsTime {

  public CloudletLengthVsTime() throws FileNotFoundException {
    Map<Integer, List<Map<String, Double>>> list = this.run();
    String fileName = this.getClass().getSimpleName() + ".csv";
    try (PrintWriter pw = new PrintWriter(new File(fileName))) {
      StringBuilder sb = new StringBuilder();
      sb.append("x,cloudletId,startTime,finishTime,cost\n");
      list.forEach((k, v) -> v.forEach(stringDoubleMap -> {
        sb.append(k).append(",").append(stringDoubleMap.get("cloudletId")).append(",")
            .append(stringDoubleMap.get("startTime")).append(",")
            .append(stringDoubleMap.get("finishTime")).append(",")
            .append(stringDoubleMap.get("cost")).append("\n");
      }));

      pw.write(sb.toString());
    }
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
