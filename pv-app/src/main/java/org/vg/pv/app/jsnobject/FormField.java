package org.vg.pv.app.jsnobject;

public class FormField {

    private Long id;
    private String name;
    private Long fieldType;
    private Long fieldDataType;
    private boolean required;
    private boolean active;
    private String label;
    private String placeholder;
    private String defaultValue;
    private int displayOrder;
    private Long master;

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

    public Long getFieldType() {
        return fieldType;
    }

    public void setFieldType(Long fieldType) {
        this.fieldType = fieldType;
    }

    public Long getFieldDataType() {
        return fieldDataType;
    }

    public void setFieldDataType(Long fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Long getMaster() {
        return master;
    }

    public void setMaster(Long master) {
        this.master = master;
    }
}
