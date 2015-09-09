# EnergyPlus API

The main entry points to the library are the parsers.

## Data Dictionary

```java
File file = new File(path);
InputStream in = new FileInputStream(file);
IDD idd = new IDDParser().parseFile(in);
```

## Simulation Input

```java
File file = new File(path);
InputStream in = new FileInputStream(file);
IDF idf = new IDFParser().parseFile(in);
```

## Simulation Output

```java
File file = new File(path);
InputStream in = new FileInputStream(file);
ESO eso = new ESOParser().parseFile(in);
```

Objects in those files are represented by `Item`s. Each `Item` has a list of
`Field`s. Specialisations of the `Field` interface provide further accessors,
e.g. field annotations in the case of ESO items.

# Official Documentation

[Interface Developers' Guide](https://nrel.github.io/EnergyPlus/InterfaceDeveloper/InterfaceDeveloper/)