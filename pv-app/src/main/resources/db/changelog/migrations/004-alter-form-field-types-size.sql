-- Alter name and short_name column sizes for form_field_types and form_field_data_types
ALTER TABLE form_field_types ALTER COLUMN name TYPE VARCHAR(255);
ALTER TABLE form_field_types ALTER COLUMN short_name TYPE VARCHAR(50);

ALTER TABLE form_field_data_types ALTER COLUMN name TYPE VARCHAR(255);
ALTER TABLE form_field_data_types ALTER COLUMN short_name TYPE VARCHAR(50);
