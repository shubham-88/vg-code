package org.vg.pv.app.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "spool")
public class Spool extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "spool_number", nullable = false, unique = true)
    private Integer spoolNumber;

    @Column(name = "self_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal selfWeight;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSpoolNumber() {
        return spoolNumber;
    }

    public void setSpoolNumber(Integer spoolNumber) {
        this.spoolNumber = spoolNumber;
    }

    public BigDecimal getSelfWeight() {
        return selfWeight;
    }

    public void setSelfWeight(BigDecimal selfWeight) {
        this.selfWeight = selfWeight;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
