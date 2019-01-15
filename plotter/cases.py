import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

'''
Input data:
    x              non-null int64
    cloudletId     non-null float64
    startTime      non-null float64
    finishTime     non-null float64
    cost           non-null float64
'''


def cloudlet_length_vs_time(input, output):
    data = pd.read_csv(input)
    plt.plot(data['x'], data['finishTime'], c='indianred')
    plt.plot(data['x'], data['finishTime'], 'o', c='indianred')
    plt.xlabel('Cloudlet length', weight='bold')
    plt.ylabel('Time', weight='bold')
    plt.axis([40000, 160000, 0, 700])
    plt.savefig('output/CloudletLengthVsTime.png')
    plt.clf()

    plt.plot(data['x'], data['cost'], c='indianred')
    plt.plot(data['x'], data['cost'], 'o', c='indianred')
    plt.xlabel('Cloudlet length', weight='bold')
    plt.ylabel('Cost', weight='bold')
    plt.axis([40000, 160000, 50, 150])
    plt.savefig('output/CloudletLengthVsCost.png')
    plt.clf()


def vm_processing_power_vs_process_timing(input, output):
    data = pd.read_csv(input).sort_values('x')
    plt.plot(data['x'], data['finishTime'], c='indianred')
    plt.plot(data['x'], data['finishTime'], 'o', c='indianred')
    plt.xlabel('VM Processing Power', weight='bold')
    plt.ylabel('Process Timing', weight='bold')
    plt.axis([250, 2500, 0, 180])
    plt.savefig('output/VmProcessingPowerVsProcessTiming.png')
    plt.clf()


def no_of_cloudlets_vs_cloudlet_completion_time_time_shared(input, output):
    data = pd.read_csv(input)
    width = 2  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(200))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(200))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    for i in range(len(sets)):
        sets[i][1] = list(sets[i][1])
        for j in range(len(x)):
            sets[i][1][j] = sets[i][1][j]*100.0 / sum[sets[i][0][j]]

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0])))
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        bawah[i][1] = list(bawah[i][1])
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('Number of cloudlets', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/NoOfCloudletsVsCloudletCompletionTimeTimeShared.png')
    plt.clf()


def no_of_cloudlets_vs_cloudlet_completion_time_space_shared(input, output):
    data = pd.read_csv(input)
    width = 2  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(200))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(200))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    for i in range(len(sets)):
        sets[i][1] = list(sets[i][1])
        for j in range(len(x)):
            sets[i][1][j] = sets[i][1][j]*100.0 / sum[sets[i][0][j]]

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0])))
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        bawah[i][1] = list(bawah[i][1])
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('Number of cloudlets', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/NoOfCloudletsVsCloudletCompletionTimeSpaceShared.png')
    plt.clf()


def bandwidth_vs_cloudlet_completion_time_time_shared(input, output):
    data = pd.read_csv(input)
    width = 200  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(10010))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(10010))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0]))).tolist()
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('Bandwidth', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/BandwidthVsCloudletCompletionTimeTimeShared.png')
    plt.clf()


def bandwidth_vs_cloudlet_completion_time_space_shared(input, output):
    data = pd.read_csv(input)
    width = 200  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(10010))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(10010))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0]))).tolist()
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('Bandwidth', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/BandwidthVsCloudletCompletionTimeSpaceShared.png')
    plt.clf()


def vm_ram_vs_cloudlet_completion_time_space_shared(input, output):
    data = pd.read_csv(input)
    width = 100  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(5200))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(5200))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0]))).tolist()
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('VM Ram', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/VmRamVsCloudletCompletionTimeSpaceShared.png')
    plt.clf()


def vm_ram_vs_cloudlet_completion_time_time_shared(input, output):
    data = pd.read_csv(input)
    width = 100  # Bar chart width
    sets = []
    times = data['finishTime'].unique()
    x = list(data['x'].unique())
    sum = list(np.zeros(5200))
    for time in times:
        subset = data.loc[data["finishTime"] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(5200))
        for e in subset:
            arr[int(e[0])] += 1
        for xx in x:
            sum[xx] += arr[xx]
            y.append(arr[xx])
        xy = zip(x, y)
        xy = sorted(xy, key=lambda val: val[0])
        xy = zip(*xy)
        sets.append(list(xy))

    bawah = np.zeros((len(sets), len(sets[0]), len(sets[0][0]))).tolist()
    for i in range(1, len(sets)):
        for k in range(len(sets[0][0])):
            bawah[i][1][k] = bawah[i-1][1][k] + sets[i-1][1][k]

    for i in range(len(sets)):
        plt.bar(sets[i][0], sets[i][1], bottom=bawah[i]
                [1], width=width, label=times[i])

    plt.xlabel('VM Ram', weight='bold')
    plt.ylabel('Completion Percentage(%)', weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig('output/VmRamVsCloudletCompletionTimeTimeShared.png')
    plt.clf()
