package org.vg.pv.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_material")
public class RawMaterial extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raw_material_type_id", nullable = false)
    private RawMaterialType rawMaterialType;

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

    public RawMaterialType getRawMaterialType() {
        return rawMaterialType;
    }

    public void setRawMaterialType(RawMaterialType rawMaterialType) {
        this.rawMaterialType = rawMaterialType;
    }
}
