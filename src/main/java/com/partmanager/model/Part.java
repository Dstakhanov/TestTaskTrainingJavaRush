package com.partmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "parts",schema = "testtask")
public class Part {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "part_name")
    private String partName;

    @Column(name = "part_need")
    private boolean partNeed;

    @Column(name = "part_amount")
    private int partAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public boolean isPartNeed() {
        return partNeed;
    }

    public void setPartNeed(boolean partNeed) {
        this.partNeed = partNeed;
    }

    public int getPartAmount() {
        return partAmount;
    }

    public void setPartAmount(int partCount) {
        this.partAmount = partCount;
    }
}
