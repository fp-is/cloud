import argparse
import cases
import os
import pathlib
import re
import sys


def snakeify(name):
    # Convert text into snake case
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()


def get_args():
    # Get argument list
    parser = argparse.ArgumentParser(description='Generate plots for cloudsim.')
    parser.add_argument('--data-path', metavar='PATH', type=str,
                        default='../output', help='location of the input data')
    parser.add_argument('--output-path', metavar='PATH', type=str,
                        default='./output', help='output directory')
    return parser.parse_args()


def main():
    args = get_args()
    inpath = os.path.abspath(args.data_path)
    if not os.path.exists(inpath):
        print("Input file path does not exist!", file=sys.stderr)

    outpath = os.path.abspath(args.output_path)
    pathlib.Path(outpath).mkdir(parents=True, exist_ok=True)

    for file in os.listdir(args.data_path):
        infile = os.path.join(inpath, file)
        outfile = os.path.join(outpath, os.path.splitext(file)[0]) + '.png'

        try:
            case_name = snakeify(os.path.splitext(file)[0])
            print("Opening %s" % case_name)
            plotter = getattr(cases, case_name)
            plotter(infile, outfile)
        except AttributeError:
            print("No plotter found for %s." % file)


if __name__ == '__main__':
    main()