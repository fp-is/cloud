package fp.is.cloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterCharacteristics;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisioner;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.ResourceProvisionerSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelFull;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;

public class Simulator {

  private int dataCenterHosts;
  private int dataCenterHostPes;
  private int dataCenterHostMips;
  private long dataCenterRam;
  private long dataCenterBw;
  private long dataCenterStorage;
  private int vmCount;
  private int vmMips;
  private int vmPes;
  private long vmRam;
  private long vmBw;
  private long vmSize;
  private int cloudletCount;
  private long cloudletLength;
  private int cloudletPes;

  public Simulator() {
    this.dataCenterHosts = 2;
    this.dataCenterHostPes = 4;
    this.dataCenterHostMips = 250;
    this.dataCenterRam = (long) (44.5 * 1024);
    this.dataCenterBw = (long) (100 * 1024);
    this.dataCenterStorage = (long) (10000 * 1024);
    this.vmCount = 8;
    this.vmMips = 250;
    this.vmPes = 1;
    this.vmRam = 512;
    this.vmBw = 1024;
    this.vmSize = 256;
    this.cloudletCount = 16;
    this.cloudletLength = 40000;
    this.cloudletPes = 1;
  }

  public Simulator(int dataCenterHosts, int dataCenterHostPes, int dataCenterHostMips,
      long dataCenterRam, long dataCenterBw, long dataCenterStorage, int vmCount, int vmMips,
      int vmPes, long vmRam, long vmBw, long vmSize, int cloudletCount, long cloudletLength,
      int cloudletPes) {
    this.dataCenterHosts = dataCenterHosts;
    this.dataCenterHostPes = dataCenterHostPes;
    this.dataCenterHostMips = dataCenterHostMips;
    this.dataCenterRam = dataCenterRam;
    this.dataCenterBw = dataCenterBw;
    this.dataCenterStorage = dataCenterStorage;
    this.vmCount = vmCount;
    this.vmMips = vmMips;
    this.vmPes = vmPes;
    this.vmRam = vmRam;
    this.vmBw = vmBw;
    this.vmSize = vmSize;
    this.cloudletCount = cloudletCount;
    this.cloudletLength = cloudletLength;
    this.cloudletPes = cloudletPes;
  }

  public List<Map<String, Double>> run() {
    CloudSim simulation = new CloudSim();
    DatacenterBroker broker0 = new DatacenterBrokerSimple(simulation);
    List<Map<String, Double>> resultList = new ArrayList<>();
    Datacenter datacenter = createDataCenter(simulation, dataCenterHosts, dataCenterHostPes, dataCenterHostMips,
        dataCenterRam, dataCenterBw, dataCenterStorage);

    List<Vm> vmList = createVms(vmCount, vmMips, vmPes, vmRam, vmBw, vmSize);
    List<Cloudlet> cloudletList = createCloudlets(cloudletCount, cloudletLength, cloudletPes);
    broker0.submitVmList(vmList);
    broker0.submitCloudletList(cloudletList);
    simulation.start();
    new CloudletsTableBuilder(broker0.getCloudletFinishedList()).build();
    broker0.getCloudletFinishedList().forEach(cloudlet -> {
      Map<String, Double> map = new HashMap<>();
      map.put("cloudletId", (double) cloudlet.getId());
      map.put("cost", cloudlet.getTotalCost());
      map.put("startTime", cloudlet.getExecStartTime());
      map.put("finishTime", cloudlet.getFinishTime());
      resultList.add(map);
    });

    return resultList;
  }

  private Datacenter createDataCenter(CloudSim simulation, int hosts, int hostPes, int hostMips,
      long ram, long bw, long storage) {
    final List<Host> hostList = new ArrayList<>(hosts);
    for(int i = 0; i < hosts; i++) {
      Host host = createHost(hostPes, hostMips, ram, bw, storage);
      host.setRamProvisioner(new ResourceProvisionerSimple())
          .setBwProvisioner(new ResourceProvisionerSimple())
          .setVmScheduler(new VmSchedulerSpaceShared());
      hostList.add(host);
    }

    Datacenter dc = new DatacenterSimple(simulation, hostList, new VmAllocationPolicySimple());
    DatacenterCharacteristics ch = dc.getCharacteristics();
    ch.setCostPerBw(1).setCostPerMem(1).setCostPerSecond(1).setCostPerSecond(1);
    return dc;
  }

  private Host createHost(int hostPes, int mips, long ram, long bw, long storage) {
    final List<Pe> peList = new ArrayList<>();
    PeProvisioner peProvisioner = new PeProvisionerSimple();
    for (int i = 0; i < hostPes; i++) {
      peList.add(new PeSimple(mips, peProvisioner));
    }

    return new HostSimple(ram, bw, storage, peList);
  }

  private List<Vm> createVms(int numberOfVm, int mips, int vmPes, long ram, long bw, long size) {
    final List<Vm> list = new ArrayList<>();
    for (int i = 0; i < numberOfVm; i++) {
      final Vm vm = new VmSimple(mips, vmPes);
      vm.setRam(ram).setBw(bw).setSize(size);
      vm.setCloudletScheduler(new CloudletSchedulerSpaceShared());
      list.add(vm);
    }

    return list;
  }

  private List<Cloudlet> createCloudlets(int numberOfCloudlets, long length, long cloudletPes) {
    final List<Cloudlet> list = new ArrayList<>();

    for (int i = 0; i < numberOfCloudlets; i++) {
      final Cloudlet cloudlet = new CloudletSimple(length, cloudletPes);
      cloudlet.setFileSize(300);
      cloudlet.setOutputSize(300);
      cloudlet.setUtilizationModel(new UtilizationModelFull());
      list.add(cloudlet);
    }

    return list;
  }

  public int getDataCenterHosts() {
    return dataCenterHosts;
  }

  public void setDataCenterHosts(int dataCenterHosts) {
    this.dataCenterHosts = dataCenterHosts;
  }

  public int getDataCenterHostPes() {
    return dataCenterHostPes;
  }

  public void setDataCenterHostPes(int dataCenterHostPes) {
    this.dataCenterHostPes = dataCenterHostPes;
  }

  public int getDataCenterHostMips() {
    return dataCenterHostMips;
  }

  public void setDataCenterHostMips(int dataCenterHostMips) {
    this.dataCenterHostMips = dataCenterHostMips;
  }

  public long getDataCenterRam() {
    return dataCenterRam;
  }

  public void setDataCenterRam(long dataCenterRam) {
    this.dataCenterRam = dataCenterRam;
  }

  public long getDataCenterBw() {
    return dataCenterBw;
  }

  public void setDataCenterBw(long dataCenterBw) {
    this.dataCenterBw = dataCenterBw;
  }

  public long getDataCenterStorage() {
    return dataCenterStorage;
  }

  public void setDataCenterStorage(long dataCenterStorage) {
    this.dataCenterStorage = dataCenterStorage;
  }

  public int getVmCount() {
    return vmCount;
  }

  public void setVmCount(int vmCount) {
    this.vmCount = vmCount;
  }

  public int getVmMips() {
    return vmMips;
  }

  public void setVmMips(int vmMips) {
    this.vmMips = vmMips;
  }

  public int getVmPes() {
    return vmPes;
  }

  public void setVmPes(int vmPes) {
    this.vmPes = vmPes;
  }

  public long getVmRam() {
    return vmRam;
  }

  public void setVmRam(long vmRam) {
    this.vmRam = vmRam;
  }

  public long getVmBw() {
    return vmBw;
  }

  public void setVmBw(long vmBw) {
    this.vmBw = vmBw;
  }

  public long getVmSize() {
    return vmSize;
  }

  public void setVmSize(long vmSize) {
    this.vmSize = vmSize;
  }

  public int getCloudletCount() {
    return cloudletCount;
  }

  public void setCloudletCount(int cloudletCount) {
    this.cloudletCount = cloudletCount;
  }

  public long getCloudletLength() {
    return cloudletLength;
  }

  public void setCloudletLength(long cloudletLength) {
    this.cloudletLength = cloudletLength;
  }

  public int getCloudletPes() {
    return cloudletPes;
  }

  public void setCloudletPes(int cloudletPes) {
    this.cloudletPes = cloudletPes;
  }
}
