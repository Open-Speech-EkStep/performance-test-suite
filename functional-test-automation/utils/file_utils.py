import json


def read_json_file(file):
    with open(file) as json_file:
        return json.load(json_file)


def read_file(file):
    f = open(file, "r")
    return f.read()