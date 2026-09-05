-- Seed data for Size
INSERT INTO size (name, val, uom, sort_order, category, is_active) VALUES 
('0.5 & 0.75 Sq.MM', 0.5000, 'Sq.MM', 1, 'MMH', true),
('1 Sq.MM', 1.0000, 'Sq.MM', 2, 'MMH', true),
('1.5 Sq.MM', 1.5000, 'Sq.MM', 3, 'MMH', true),
('2.5 Sq.MM', 2.5000, 'Sq.MM', 4, 'MMH', true),
('4 Sq.MM', 4.0000, 'Sq.MM', 5, 'MMH', true),
('6 Sq.MM', 6.0000, 'Sq.MM', 6, 'MMH', true),
('10 Sq.MM', 10.0000, 'Sq.MM', 7, 'MMH', true)
ON CONFLICT (name, category) DO NOTHING;

-- Register Size in Master Data Registry
INSERT INTO master_data_registry (name, table_name) VALUES 
('Size', 'size')
ON CONFLICT (name) DO NOTHING;
