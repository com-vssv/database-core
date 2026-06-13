# Core Data Storage Engine

This package contains the foundational components of a custom, hierarchical data storage engine written in Java. It is designed to provide a flexible and extensible framework for managing structured data on disk.

## Core Concepts

The engine is built around a few key concepts:

- **Cluster:** The top-level container that manages a collection of tables and their partitions.
- **Table:** Represents a single, logical data table containing records.
- **Partition:** A physical subdivision of a table's data, allowing for distributed storage and optimized access. Partitions can be either folder-based or file-based.
- **Interfaces:** A set of simple, well-defined interfaces (`DBReader`, `DBWriter`) provide a public contract for all database interactions.

## Features

- **Hierarchical Storage:** Data is organized in a `Cluster -> Partition -> Table` hierarchy.
- **Pluggable Partitioning:** Supports multiple partitioning strategies, including folder-per-table and file-based storage.
- **Extensible by Design:** The use of abstract classes and interfaces makes it easy to add new features and storage formats.

## Getting Started

To use this package, you will typically start by creating a `Cluster` instance and defining your tables. From there, you can use the `DBReader` and `DBWriter` interfaces to interact with your data.

See the `CONTEXT.md` file for a more detailed overview of the architecture.
