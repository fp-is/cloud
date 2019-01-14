package fp.is.cloud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public abstract class BaseCase {

  protected void writeFile(Map<Integer, List<Map<String, Double>>> list) throws FileNotFoundException {
    String fileName = "output/" + this.getClass().getSimpleName() + ".csv";
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
}
