-- Seed data for Spools
INSERT INTO spool (name, code, spool_number, self_weight, is_active, sort_order) VALUES 
('Standard Spool S1', 'SPL-S1', 201, 10.250, true, 1)
ON CONFLICT (code) DO NOTHING;

-- Register Spool in Master Data Registry
INSERT INTO master_data_registry (name, table_name) VALUES 
('Spool', 'spool')
ON CONFLICT (name) DO NOTHING;
