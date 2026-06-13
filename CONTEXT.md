# Architecture Context

The `core` package provides the fundamental building blocks for the VSSV database engine. The design prioritizes modularity and abstraction to support various storage strategies.

## Data Models (`com.vssv.database.core.models`)

This package contains the domain entities:

-   `Table`: Abstract base class representing a logical data collection.
-   `Cluster`: Abstract base class for managing a group of tables.
-   `Partition`: Abstract base class representing a physical storage segment.
-   `ReadRecord` & `WriteRecord`: Abstract base classes representing data rows.

### Storage Strategies

The system currently defines two primary storage strategies through concrete implementations of the abstract models:

1.  **File-based Strategy**:
    -   `FileTypePartition`: Data for a partition is stored in a single file.
    -   `FileTypeTable` & `FileTypeCluster`: Concrete classes that work within this strategy.
2.  **Folder-based Strategy**:
    -   `FolderTypePartition`: Data for a partition is managed within a specific folder.
    -   `FolderTypeTable` & `FolderTypeCluster`: Concrete classes that work within this strategy.

## Interfaces (`com.vssv.database.core.interfaces`)

These define the contracts for interacting with the core engine:

-   `DBReader`: Interface for reading data.
-   `DBWriter`: Interface for writing data.
-   `ClusterOperations`: Interface defining administrative operations on a cluster.

## Services (`com.vssv.database.core.services`)

This package contains manager classes responsible for the business logic associated with the models (e.g., `ClusterManager`, `PartitionManager`). These services orchestrate the interactions between tables, partitions, and the underlying file system.
