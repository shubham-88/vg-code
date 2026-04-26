-- Liquibase Migration: Add headers column to form_template
-- Target Schema: postgres-vg-sc1

ALTER TABLE "postgres-vg-sc1".form_template ADD COLUMN headers JSONB;
