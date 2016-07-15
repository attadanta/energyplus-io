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
=======
# EnergyPlus API

This library provides convenient programmatic access to EnergyPlus IO data
formats for the Java language. The coverage spans the data dictionary (`.idd`),
energy input (`.idf`) and energy output (`.eso`) formats.

The main entry points to the library are the parsers. The following sections
provide usage examples.

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

# Data Model

Lines in the EPlus files are represented by `Item`s. Each `Item` has a list of
`Field`s. Specializations of the `Field` interface provide further accessors,
e.g. field annotations in the case of ESO items.

The EnergyPlus output format can be explored with a `DataTable` instance. Once
initialized, it exposes `TimeSeries` for each variable in the output. Each time
series is associated with the environment the variable is reported in and
contains a list of `Measurement`s. See the package `data` for more information
on ESO postprocessing.

# Official Documentation

[Interface Developers' Guide](https://nrel.github.io/EnergyPlus/InterfaceDeveloper/InterfaceDeveloper/)
