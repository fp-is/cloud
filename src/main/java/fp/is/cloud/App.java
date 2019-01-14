package fp.is.cloud;

import fp.is.cloud.cases.CloudletLengthVsTime;
import fp.is.cloud.cases.NoOfCloudletsVsCloudletCompletionTime;
import fp.is.cloud.cases.NoOfVmVsCost;
import fp.is.cloud.cases.VmImageSizeVsCost;

public class App {

  public static void main(String[] args) {
    try {
//      new CloudletLengthVsTime();
//      new NoOfVmVsCost();
//      new VmImageSizeVsCost();
      new NoOfCloudletsVsCloudletCompletionTime();
    } catch (Exception e) {

    }
  }
}
