[![trophy](https://github-profile-trophy.vercel.app/?username=USMCsky&theme=onedark&margin-w=10&margin-h=10)](https://github.com/ryo-ma/github-profile-trophy)

# SortYourStuff

A lightweight Java/Spigot plugin that sorts player inventories and containers with **Shift + F**.

## Features

- Sorts player inventory storage in visual order.
- Sorts container inventories in place.
- Merges similar item stacks before reordering.
- Orders items consistently by material, metadata, and stack size.
- Optional shift requirement to prevent accidental sorting.
- Permission-gated with `sort.use`.

## How it works

When a player presses **F** (offhand swap) while interacting with an inventory:

- If `require-shift` is enabled, the player must also be sneaking or shift-clicking.
- Player inventories are sorted using the normal visual storage layout.
- Chests and other containers are sorted from slot `0` to the last slot.
- The plugin merges similar stacks where possible, then rewrites the inventory in sorted order.

## Configuration

Default `config.yml`:

```yaml
require-shift: true
```

- `true`: sorting only works when shift is held or the player is sneaking.
- `false`: pressing **F** while in an inventory can trigger sorting without shift.

## Permission

```text
sort.use
```

Players with this permission can use the sorting feature. It defaults to `true`.

## Plugin details

- **Name:** `sort`
- **Main class:** `particles.usmcsky.Sort`
- **API version:** `1.21`
- **Java version:** `21`
- **Spigot dependency:** `org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT`

## Build

Use the Gradle wrapper included in the repository:

```bash
./gradlew build
```

On Windows:

```bash
gradlew.bat build
```

## Installation

1. Build the plugin jar.
2. Place the generated jar in your server's `plugins/` folder.
3. Start or restart the server.
4. Adjust `config.yml` if needed.

## Usage

1. Open your inventory or a container.
2. Hold **Shift** if `require-shift` is enabled.
3. Press **F**.
4. Your inventory or the open container will be sorted.

## Notes

This repository currently contains a built jar file in the root, but the recommended workflow is to build fresh artifacts from source for releases and testing.
