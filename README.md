# Logistics App: Factory Method + Abstract Factory

Java console application for the Software Design Patterns course (Assignment 2).
It delivers cargo by road or sea (Factory Method) and renders a matching Windows or
macOS button and checkbox (Abstract Factory). Both parts run together in one program.

## Prerequisites

- JDK 17 (`javac -version` should print 17.x)

## Package structure

| Package | Content |
|---|---|
| `factorymethod` | `Transport`, `Truck`, `Ship`, `Logistics`, `RoadLogistics`, `SeaLogistics` |
| `abstractfactory` | `Button`, `Checkbox`, `GUIFactory`, `WindowsFactory`, `MacOSFactory`, Windows and macOS components |
| `app` | `Main` (startup and validation), `DeliveryApplication` (client), `DeliveryMode`, `UiPlatform`, `ChoiceParser`, `InvalidChoiceException` |

## Build

Linux / macOS:

```
javac -d out $(find src -name "*.java")
```

Windows (cmd):

```
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

Windows (PowerShell):

```
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

## Run

With arguments:

```
java -cp out app.Main ROAD WINDOWS
```

Interactive (no arguments, you will be prompted):

```
java -cp out app.Main
```

## Supported input (case-insensitive)

- Delivery mode: `ROAD`, `SEA`
- UI platform: `WINDOWS`, `MACOS`

## Validation behavior

- Unsupported value: prints `Error: ...` with the supported values, exit code 1. Nothing is rendered or delivered.
- Missing value (empty line or end of input in interactive mode): prints `Error: Missing ...`, exit code 1.
- Wrong number of arguments (not 0 and not 2): prints an error with usage, exit code 1.
- There is no default value and no retry.

## Sample run

```
> java -cp out app.Main ROAD WINDOWS
Delivery mode: ROAD
UI platform: WINDOWS
Rendering Windows button
Rendering Windows checkbox
Truck delivers laboratory equipment to Aktau warehouse by road
```
