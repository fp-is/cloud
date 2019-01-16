import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import os

_basepath = 'output/'


def linePlot(input, linePlot, xlabel, ylabel, name=None, axis=None, color='indianred'):
    if name is None:
        name = os.path.splitext(os.path.basename(input))[0]

    data = pd.read_csv(input).sort_values('x')
    linePlotData(data, linePlot, xlabel, ylabel, name, axis, color=color)


def linePlotData(data, key, xlabel, ylabel, name, axis=None, color='indianred'):
    plt.plot(data['x'], data[key], c=color)
    plt.plot(data['x'], data[key], 'o', c=color)
    plt.xlabel(xlabel, weight='bold')
    plt.ylabel(ylabel, weight='bold')
    if axis is not None:
        plt.axis(axis)
    plt.savefig(_basepath + name + '.png')
    plt.clf()


def stackedBarChart(input, key, xlabel, ylabel, name=None, width=0.5):
    if name is None:
        name = os.path.splitext(os.path.basename(input))[0]

    data = pd.read_csv(input).sort_values('x')
    stackedBarChartData(data, key, xlabel, ylabel, name, width=width)


def stackedBarChartData(data, key, xlabel, ylabel, name, width=0.5):
    ax = data['x']
    width = width * (ax.max() - ax.min()) / len(ax.unique())

    x = list(ax.unique())
    times = data[key].unique()
    sets = []
    sum = list(np.zeros(ax.max() + 1))
    for time in times:
        subset = data.loc[data[key] == time]
        subset = subset.values.tolist()
        y = list()
        arr = list(np.zeros(ax.max() + 1))
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

    plt.xlabel(xlabel, weight='bold')
    plt.ylabel(ylabel, weight='bold')
    plt.xticks(x, x)
    plt.legend()
    plt.savefig(_basepath + name + '.png')
    plt.clf()
