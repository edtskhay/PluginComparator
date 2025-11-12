# Plugin Comparator CLI Tool

A small CLI tool to extract plugin entries from ZIP/JAR files and compare them for similarities and uniqueness.

---

## Features

- **Extract**: Parse a plugin (ZIP/JAR) and output its internal file entries to a text file.
- **Compare**: Compare two entry files and calculate commonality and uniqueness percentages.
- Optional output file support for storing comparison results.

---

## Requirements

- Java 17+
- Maven 3.x

---

## Usage

### Extract plugin entries

```bash
mvn compile exec:java -Dexec.mainClass=Main -Dexec.args="extract -i <plugin.zip> -o <entries.txt>"
```
- -i / --input : path to the plugin file (ZIP or JAR) [required]
- -o / --output : path to the output text file [required]

**Example:**

```
mvn compile exec:java -Dexec.mainClass=Main -Dexec.args="extract -i plugin.zip -o entries.txt"
```
### Compare two entry files
```
mvn compile exec:java -Dexec.mainClass=Main -Dexec.args="compare -f <entries1.txt> -s <entries2.txt> [-o <output.txt>]"
```
- -f / --first : path to first entry file [required]

- -s / --second : path to second entry file [required]

- -o / --output : optional output file for comparison results

**Example:**

```
mvn compile exec:java -Dexec.mainClass=Main -Dexec.args="compare -f entries1.txt -s entries2.txt -o comparison.txt"
```
