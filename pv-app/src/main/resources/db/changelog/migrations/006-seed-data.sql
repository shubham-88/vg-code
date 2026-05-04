-- Seed data for Business Units
INSERT INTO business_units (code, name)
VALUES ('BU_KASHIPUR_001', 'Kashipur Unit')
ON CONFLICT (code) DO NOTHING;
INSERT INTO business_units (code, name)
VALUES ('BU_ROORKEE_002', 'Roorkee Unit')
ON CONFLICT (code) DO NOTHING;


-- Seed data for Location Types
INSERT INTO location_type (name, code) VALUES 
('Root Location', 'ROOT_LOC'),
('SUB Location', 'SUB_LOC')
ON CONFLICT DO NOTHING;

-- Seed data for Locations
-- Note: Using subqueries to get IDs based on codes/names
INSERT INTO location (name, location_type_id, unit_id, parent_loc_id)
SELECT 'PRODUCTION', lt.id, bu.id, NULL
FROM location_type lt, business_units bu
WHERE lt.code = 'ROOT_LOC' AND bu.code = 'BU_KASHIPUR_001'
ON CONFLICT DO NOTHING;

INSERT INTO location (name, location_type_id, unit_id, parent_loc_id)
SELECT 'MMH', lt.id, bu.id, p.id
FROM location_type lt, business_units bu, location p
WHERE lt.code = 'SUB_LOC' 
  AND bu.code = 'BU_KASHIPUR_001'
  AND p.name = 'PRODUCTION'
ON CONFLICT DO NOTHING;

-- Seed data for Form Field Types
INSERT INTO form_field_types (name, short_name, description) VALUES 
('Text', 'text', 'A single-line text field.'),
('Password', 'password', 'A single-line text field where the characters are masked.'),
('Checkbox', 'checkbox', 'A checkbox that can be toggled on or off.'),
('Radio', 'radio', 'A radio button for selecting one option from a set.'),
('Button', 'button', 'A clickable button.'),
('Date', 'date', 'A control for entering a date (year, month, and day).'),
('Datetime Local', 'datetime-local', 'A control for entering a date and time, with no time zone.'),
('Email', 'email', 'A field for entering an e-mail address.'),
('File', 'file', 'A control that lets the user select a file.'),
('Hidden', 'hidden', 'A control that is not displayed but whose value is submitted to the server.'),
('Image', 'image', 'A graphical submit button.'),
('Month', 'month', 'A control for entering a month and year.'),
('Number', 'number', 'A control for entering a number.'),
('Range', 'range', 'A control for entering a number whose exact value is not important (like a slider).'),
('Reset', 'reset', 'A button that resets all form controls to their initial values.'),
('Search', 'search', 'A single-line text field for entering search strings.'),
('Submit', 'submit', 'A button that submits the form.'),
('Tel', 'tel', 'A control for entering a telephone number.'),
('Time', 'time', 'A control for entering a time (hour, minute, second, and fraction of a second).'),
('URL', 'url', 'A field for entering a URL.'),
('Week', 'week', 'A control for entering a week-year number and a week number.'),
('Color', 'color', 'A color picker.'),
('Textarea', 'textarea', 'A multi-line text input control.'),
('Select', 'select', 'A drop-down list.'),
('Multi-Select', 'multi-select', 'A multi-select list.')
ON CONFLICT (name) DO NOTHING;

-- Seed data for Form Field Data Types
INSERT INTO form_field_data_types (name, short_name, description) VALUES 
('java.lang.Byte', 'Byte', 'Standard byte data.'),
('java.lang.Short', 'Short', 'Standard short data.'),
('java.lang.Integer', 'int', 'Whole numbers without decimal points.'),
('java.lang.Long', 'long', 'Numbers with decimal precision.'),
('Decimal', 'DEC', 'Numbers with decimal precision.'),
('java.lang.Float', 'float', 'Numbers with decimal precision.'),
('java.lang.Double', 'double', 'Numbers with decimal precision.'),
('java.lang.Boolean', 'boolean', 'True or false values.'),
('java.lang.Char', 'char', 'Character values.'),
('java.lang.Date', 'date', 'Calendar date values.'),
('java.time.LocalDate', 'LocalDate', 'Calendar date values.'),
('java.time.LocalTime', 'LocalTime', 'Calendar date values.'),
('java.time.LocalDateTime', 'LocalDateTime', 'Calendar date values.'),
('java.lang.String', 'String', 'Standard String value'),
('java.lang.Enum', 'enum', 'Standard Enum value'),
('java.util.List', 'List', 'Standard List value'),
('java.util.Arrays', 'Array', 'Standard Array value'),
('java.lang.Object', 'Object', 'Standard Object value'),
('org.vg.pv.app.types.CustomTypes', 'type', 'Standard Object value')
ON CONFLICT (name) DO NOTHING;

-- Seed data for Form Template
INSERT INTO form_template (name, loc_id, attributes, headers)
SELECT 
    'Production Form Template', 
    l.id, 
    '[{"name": "machine_id", "fieldType": 1, "fieldDataType": 1, "required": true, "active": true, "label": "Machine ID", "placeholder": "Enter machine ID", "displayOrder": 1}]'::jsonb,
    '[{"name": "Product Code", "key": "prod_code", "formula": null, "displayOrder": 1}, {"name": "Total Weight", "key": "total_weight", "formula": "gross_weight - tare_weight", "displayOrder": 2}]'::jsonb
FROM location l
WHERE l.name = 'PRODUCTION'
ON CONFLICT (name, loc_id) DO NOTHING;

-- Seed data for Baskets
INSERT INTO basket (name, code, basket_number, self_weight, is_active, sort_order) VALUES 
('Standard Basket A1', 'BSK-A1', 101, 15.500, true, 1),
('Heavy Duty Basket B2', 'BSK-B2', 102, 25.750, true, 2),
('Heavy Duty Basket B3', 'BSK-B3', 103, 35.750, true, 3),
('Heavy Duty Basket B4', 'BSK-B4', 104, 40.750, true, 4),
('Heavy Duty Basket B5', 'BSK-B5', 105, 45.750, true, 5),
('Heavy Duty Basket B6', 'BSK-B6', 106, 55.750, true, 6),
('Heavy Duty Basket B7', 'BSK-B7', 107, 66.750, true, 7),
('Heavy Duty Basket B8', 'BSK-B8', 108, 77.750, true, 8),
('Heavy Duty Basket B9', 'BSK-B9', 109, 88.750, true, 9),
('Heavy Duty Basket B10', 'BSK-B10', 110, 150.750, true, 10)
ON CONFLICT (code) DO NOTHING;

-- Seed data for Suppliers
INSERT INTO supplier (name, code, is_active, sort_order) VALUES 
('Acme Manufacturing', 'SUP-ACME', true, 1),
('Global Supplies Co.', 'SUP-GLOB', true, 2)
ON CONFLICT (code) DO NOTHING;

-- Seed data for Master Data Registry
INSERT INTO master_data_registry (name, table_name) VALUES 
('Basket', 'basket'),
('Supplier', 'supplier'),
('Location', 'location')
ON CONFLICT (name) DO NOTHING;
