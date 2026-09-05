-- Seed data for Wire
INSERT INTO wire (name, val, uom, sort_order, category, is_active) VALUES 
('7 Wires', 7.0000, 'Wires', 1, 'MMH', true),
('8 Wires', 8.0000, 'Wires', 2, 'MMH', true),
('14 Wires', 14.0000, 'Wires', 3, 'MMH', true),
('15 Wires', 15.0000, 'Wires', 4, 'MMH', true),
('16 Wires', 16.0000, 'Wires', 5, 'MMH', true)
ON CONFLICT (name, category) DO NOTHING;

-- Register Wire in Master Data Registry
INSERT INTO master_data_registry (name, table_name) VALUES 
('Wire', 'wire')
ON CONFLICT (name) DO NOTHING;
