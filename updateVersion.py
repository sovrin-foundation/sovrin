#! /usr/bin/env python3
import argparse
import json
import semver

ap = argparse.ArgumentParser("Updates Version in json")
group = ap.add_mutually_exclusive_group(required=True)
group.add_argument("-t", "--tag", help="Version to be set to")
group.add_argument("--timestamp", help="Timestamp to be set for the version")
group.add_argument("--getVersion", help="Prints the current version", action="store_true")
args = vars(ap.parse_args())


def updateWithTag(ver):
    if not semver.VersionInfo.isvalid(ver):
        raise ValueError('No Valid Semver in Tag')
    return ver


def updateWithTimestamp(timestamp):
    version = "str"
    with open('sovrin/metadata.json', 'r') as f:
        data = json.load(f)
        v = semver.VersionInfo.parse(data["version"])
        v = v.replace(prerelease="dev" + timestamp)
        version = str(v)
    return version


version = "string"

if args['getVersion']:
    with open('sovrin/metadata.json', 'r') as f:
        data = json.load(f)
        v = semver.VersionInfo.parse(data["version"])
        print(v)
    quit()

if args['tag'] is not None:
    version = updateWithTag(args['tag'])
    print("Version will be updated to: " + version)
elif args['timestamp'] is not None:
    version = updateWithTimestamp(args['timestamp'])
    print("Replacing  Dev-Version with UX-timestamp: " + version)
else:
    ap.print_help()
    quit()

with open('sovrin/metadata.json', 'r') as f:
    data = json.load(f)
    data["version"] = version
    json.dump(data, open("sovrin/metadata.json", "w"), indent=2)


print("Updated version of sovtoken and sovtokenfees metadata.json to: ", version)