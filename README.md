# SlingCMS.io

## To get started

Using bash run:

```bash
./start.sh
```

Or, manually

1. Build project 

```bash
mvn clean package
```

2. Start the generated jar with

```bash
    cd quickstart
    java -jar $(find ./target -name io.slingcms*[^sources].jar)
```

3. Browse SlingCMS on:

[http://localhost:8080](http://localhost:8080)
