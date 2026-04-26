package org.vg.pv.app.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.vg.pv.app.jsnobject.FormField;
import org.vg.pv.app.jsnobject.GridHeader;
import java.util.List;

@Entity
@Table(name = "form_template")
public class FormTemplate extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loc_id", nullable = false)
    private Location location;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", nullable = false)
    private List<FormField> attributes;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "headers")
    private List<GridHeader> headers;

    public FormTemplate() {
    }

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<FormField> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<FormField> attributes) {
        this.attributes = attributes;
    }

    public List<GridHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<GridHeader> headers) {
        this.headers = headers;
    }
}
