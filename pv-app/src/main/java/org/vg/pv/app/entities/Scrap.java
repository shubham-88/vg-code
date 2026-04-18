package org.vg.pv.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "scrap")
public class Scrap extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrap_type_id", nullable = false)
    private ScrapType scrapType;

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

    public ScrapType getScrapType() {
        return scrapType;
    }

    public void setScrapType(ScrapType scrapType) {
        this.scrapType = scrapType;
    }
}
