-- Liquibase Migration: Add headers column to form_template
-- Target Schema: vg-sc1

ALTER TABLE "vg-sc1".form_template ADD COLUMN headers JSONB;
