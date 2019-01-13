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
    plt.xlabel('Cloudlet length')
    plt.ylabel('Time')
    plt.axis([40000, 160000, 0, 700])
    plt.savefig(output)