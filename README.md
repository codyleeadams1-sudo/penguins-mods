# Penguin Mod — Fabric 1.21.11

A Fabric port of the original Penguin Mod. Adds a penguin mob that naturally spawns in snowy and icy biomes, plus a Penguin Spawn Egg in the Spawn Eggs creative tab.

## Target
- Minecraft Java **1.21.11**
- **Fabric**
- Java **21**
- Fabric Loader **0.18.2**
- Fabric API **0.141.1+1.21.11**
- Fabric Loom **1.13.6**

## Features
- Penguin entity with the original Blockbench model and texture
- Waddling/flipper animation
- Follows and is tempted by fish; fish can be used for breeding
- Natural spawning in snowy/icy biomes
- Penguin Spawn Egg in the Spawn Eggs tab

## Build
Run `gradle build --no-daemon` using JDK 21. The built mod jar will be in `build/libs/`.

A GitHub Actions workflow is included at `.github/workflows/build.yml`. Upload the project to a GitHub repository and the workflow will build the jar automatically. Download the `penguinmod-fabric-1.21.11` artifact from the Actions run.

## Install
Install Fabric Loader for Minecraft 1.21.11 and the matching Fabric API. Put the built `penguinmod-1.1.0.jar` in your Minecraft `mods` folder.

## Configuration
- Spawn biome list: `src/main/resources/data/penguinmod/tags/worldgen/biome/spawns_penguins.json`
- Spawn weight/group size: `PenguinMod.java` (`10`, `2`, `5`)
- Health and movement speed: `PenguinEntity.createAttributes()`
- Texture: `src/main/resources/assets/penguinmod/textures/entity/penguin.png`
