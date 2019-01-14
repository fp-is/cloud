package fp.is.cloud;

import fp.is.cloud.cases.BandwidthVsCloudletCompletionTimeSpaceShared;
import fp.is.cloud.cases.BandwidthVsCloudletCompletionTimeTimeShared;
import fp.is.cloud.cases.CloudletLengthVsTime;
import fp.is.cloud.cases.NoOfCloudletsVsCloudletCompletionTimeSpaceShared;
import fp.is.cloud.cases.NoOfCloudletsVsCloudletCompletionTimeTimeShared;
import fp.is.cloud.cases.VmProcessingPowerVsProcessTiming;
import fp.is.cloud.cases.VmRamVsCloudletCompletionTimeSpaceShared;
import fp.is.cloud.cases.VmRamVsCloudletCompletionTimeTimeShared;

public class App {

  public static void main(String[] args) {
    try {
      new NoOfCloudletsVsCloudletCompletionTimeTimeShared();
      new NoOfCloudletsVsCloudletCompletionTimeSpaceShared();
      new CloudletLengthVsTime();
      new VmRamVsCloudletCompletionTimeSpaceShared();
      new VmRamVsCloudletCompletionTimeTimeShared();
      new BandwidthVsCloudletCompletionTimeSpaceShared();
      new BandwidthVsCloudletCompletionTimeTimeShared();
      new VmProcessingPowerVsProcessTiming();
    } catch (Exception e) {

    }
  }
}
