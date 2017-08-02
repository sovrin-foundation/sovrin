### Build a sovrin package using docker

```
./build-sovrin-docker.sh <path-to-sources> <version>
```
Built package is placed in a docker volume `sovrin-deb-u1604`. 

### Build a sovrin package

```
./build-sovrin.sh <path to sources> <version> <output-path: default='.'>
```

Built package is placed in the `output-path` folder.
