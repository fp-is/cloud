import matplotlib.pyplot as plt
import pandas as pd

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
    plt.axis([40000, 160000, 500, 1420])
    plt.savefig('output/CloudletLengthVsCost.png')
    plt.clf()