package com.example.HardwarePrimjer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    CPU(1, "Central Processor", "New"),
    GPU(2, "GPU", "New and used"),
    MBO(3, "MBO", "New and used"),
    RAM(4, "RAM", "New and used"),
    STORAGE(5, "Cars and motorcycles", "New and used"),
    OTHER(6, "Cars and motorcycles", "New and used");

    private final Integer id;
    private final String name;
    private final String description;
}
