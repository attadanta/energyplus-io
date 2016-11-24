# EnergyPlus API

This library provides convenient programmatic access to EnergyPlus IO data
formats for the Java language. The coverage spans the data dictionary (`.idd`),
energy input (`.idf`) and energy output (`.eso`) formats.

The main entry points to the library are the parsers. The following sections
provide usage examples.

```java
// Data Dictionary

File file = new File(path);
InputStream in = new FileInputStream(file);
IDD idd = new IDDParser().parseFile(in);

// Simulation Input

File file = new File(path);
InputStream in = new FileInputStream(file);
IDF idf = new IDFParser().parseFile(in);

// Simulation Output

File file = new File(path);
InputStream in = new FileInputStream(file);
ESO eso = new ESOParser().parseFile(in);
```

## Data Model

Lines in the EPlus files are represented by `Item`s. Each `Item` has a list of
`Field`s. Specializations of the `Field` interface provide further accessors,
e.g. field annotations in the case of ESO items.

The EnergyPlus output format can be explored with a `DataTable` instance. Once
initialized, it exposes `TimeSeries` for each variable in the output. Each time
series is associated with the environment the variable is reported in and
contains a list of `Measurement`s. See the package `data` for more information
on ESO postprocessing.

## Official Documentation

 * [Guide for Interface Developers](http://bigladdersoftware.com/epx/docs/8-6/interface-developer/index.html)
 * [Input-Output Reference](http://bigladdersoftware.com/epx/docs/8-6/input-output-reference/)

## Acknowledgement

This work originated in the [DAREED](http://dareed.eu) project. DAREED has received funding from the the Seventh
framework programme of the European union (EU) under grant agreement 609082.