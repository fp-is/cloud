import pandas as pd

from basecase import linePlot, linePlotData, stackedBarChart

'''
Input data:
    x              non-null int64
    cloudletId     non-null float64
    startTime      non-null float64
    finishTime     non-null float64
    cost           non-null float64
'''


def cloudlet_length_vs_time(input):
    # Single dataset, multiple output
    data = pd.read_csv(input)

    linePlotData(
        data,
        'finishTime',
        'Cloudlet length',
        'Time',
        'CloudletLengthVsTime',
        axis=[40000, 160000, 0, 700]
    )

    linePlotData(
        data,
        'cost',
        'Cloudlet length',
        'Cost ($)',
        'CloudletLengthVsCost',
        axis=[40000, 160000, 50, 150]
    )


def vm_processing_power_vs_process_timing(input):
    linePlot(
        input,
        'finishTime',
        'VM Processing Power',
        'Process Timing',
        axis=[250, 2500, 0, 180]
    )


def no_of_cloudlets_vs_cloudlet_completion_time_time_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'Number of cloudlets',
        'Completion Percentage(%)'
    )


def no_of_cloudlets_vs_cloudlet_completion_time_space_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'Number of cloudlets',
        'Completion Percentage(%)'
    )


def bandwidth_vs_cloudlet_completion_time_time_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'Bandwidth',
        'Completion Percentage(%)'
    )


def bandwidth_vs_cloudlet_completion_time_space_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'Bandwidth',
        'Completion Percentage(%)'
    )


def vm_ram_vs_cloudlet_completion_time_space_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'VM RAM',
        'Completion Percentage(%)'
    )


def vm_ram_vs_cloudlet_completion_time_time_shared(input):
    stackedBarChart(
        input,
        'finishTime',
        'VM RAM',
        'Completion Percentage(%)'
    )
