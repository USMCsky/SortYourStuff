# 🏆 SortYourStuff

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.21.11-5E7C16?style=for-the-badge&logo=minecraft&logoColor=white" alt="Minecraft 1.21.11" />
  <img src="https://img.shields.io/badge/Spigot-API-F27B29?style=for-the-badge" alt="Spigot API" />
  <img src="https://img.shields.io/badge/Java-21-E76F00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Author-USMCsky-6A4C93?style=for-the-badge&logo=github&logoColor=white" alt="Author USMCsky" />
</p>

A lightweight Spigot plugin that sorts player inventories and containers with **Shift + F**, helping keep storage organized with a fast in-game shortcut.

## Features
- **Player inventory sorting** using the normal visual storage order.
- **Container sorting** for chests and other open inventories.
- **Stack merging** before sorting to reduce partial stacks.
- **Consistent ordering** by material, metadata, and stack size.
- **Optional shift requirement** to prevent accidental sorting.
- **Permission-controlled usage** with `sort.use`.

## Built For
- **Platform:** Spigot-compatible servers
- **Minecraft version:** 1.21.11
- **Language level:** Java 21

## How It Works
Once installed, the plugin listens for the offhand swap key while a player is interacting with an inventory:

- **Shift + F sorting:** players can sort quickly using the offhand swap keybind.
- **Player inventory support:** storage slots are sorted in the standard visual order players expect.
- **Container support:** open containers are sorted from the first slot to the last slot.
- **Smart stack handling:** similar items are merged together before the final order is applied.

## Usage
After installation, usage is simple:
- Open your inventory or a container.
- Hold **Shift** if `require-shift` is enabled.
- Press **F**.
- The inventory or container will be sorted automatically.

## Compatibility
- Spigot API `1.21.11-R0.1-SNAPSHOT`
- Minecraft 1.21.x servers matching the declared API version
