package com.vssv.database.core.models;

/**
 * Defines the categories of clusters based on their storage location.
 * Clusters can be stored locally or on a network.
 */
public enum ClusterCategory {
    /**
     * A cluster stored on the local file system.
     */
    Local,
    /**
     * A cluster stored on a remote network location.
     */
    Network
}