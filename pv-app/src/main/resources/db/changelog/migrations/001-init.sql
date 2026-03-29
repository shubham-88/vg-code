--Stores plants/units - kashipur, roorkee, haridwar
CREATE TABLE business_units
(
    id         BIGSERIAL PRIMARY KEY,
    code       VARCHAR(20)  NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX idx_business_units_code ON business_units (code);
CREATE UNIQUE INDEX idx_business_units_name ON business_units (name);

--Stores System Users - employee working in the business unit
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    code       VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    enabled    BOOLEAN     DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX idx_users_code ON users (code);
CREATE UNIQUE INDEX idx_users_email ON users (email);

--Stores users roles - ADMIN, CFT
CREATE TABLE roles
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

--Sores privileges given to a user - PERFORM_STORE_VISIT, SUBMIT_DATA,EXPORT
CREATE TABLE privileges
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

--Stores Users to Roles mapping
CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

--Stores Roles to Privileges mapping
CREATE TABLE roles_privileges
(
    role_id      BIGINT NOT NULL,
    privilege_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, privilege_id),
    CONSTRAINT fk_role_rel FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT fk_privilege_rel FOREIGN KEY (privilege_id) REFERENCES privileges (id) ON DELETE CASCADE
);

--Store form templetes - used to store diffrent locations form staructure
CREATE TABLE form_template
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    loc_id     BIGINT       NOT NULL,
    attributes JSONB        NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_form_template_name_loc UNIQUE (name, loc_id),
    CONSTRAINT fk_form_template_location
        FOREIGN KEY (loc_id)
            REFERENCES locations (id)

);

CREATE TABLE items
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    loc_id     BIGINT       NOT NULL,
    attributes JSONB        NOT NULL, --  FIELD_NAME, FIELD_VALUE
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_item_location FOREIGN KEY (loc_id) REFERENCES location (id)
);
CREATE INDEX idx_item_attr_path_ops ON item USING GIN (attributes jsonb_path_ops);

-- Indexing the JSONB column for faster lookups
CREATE INDEX idx_form_template_attr ON form_template USING GIN (attributes);
CREATE INDEX idx_form_template_name ON form_template (name);
CREATE INDEX idx_form_template_loc_id ON form_template (loc_id);

-- Stores Location types - BASE_LOC-1, SUB_LOCATION-2 , SUB_SUB_LOCATION-3
CREATE TABLE location_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(20)  NOT NULL UNIQUE
);

CREATE INDEX idx_location_type_code ON location_type (code);
CREATE INDEX idx_location_type_name ON location_type (name);

--Stores Locations - Production , SCM , Blade Packing
CREATE TABLE location
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    location_type_id BIGINT       NOT NULL,
    parent_loc_id    BIGINT,
    unit_id          BIGINT,
    created_at       TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_location_type
        FOREIGN KEY (location_type_id) REFERENCES location_type (id),

    CONSTRAINT fk_parent_location
        FOREIGN KEY (parent_loc_id) REFERENCES location (id),

    CONSTRAINT fk_location_unit
        FOREIGN KEY (unit_id) REFERENCES business_units (id)
);


CREATE INDEX idx_location_parent ON location (parent_loc_id);
CREATE INDEX idx_location_name ON location (name);
CREATE INDEX idx_location_unit_id ON location (unit_id);

--Stores form fields types - Dropdown. radio button, input text, text area
CREATE TABLE form_field_types
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    short_name  VARCHAR(10),
    description VARCHAR(255)
);

--Stores form fields data types - integer, long, flot , double, string
CREATE TABLE form_field_data_types
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    short_name  VARCHAR(10),
    description VARCHAR(255)
);

--Stores all available master data in the system - Basket type, Spool , Bobbin
CREATE TABLE master_data_registry
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL UNIQUE,
    table_name VARCHAR(63)  NOT NULL
    -- 63 is the max length for identifiers in Postgres
);


CREATE INDEX idx_master_code ON master_data_registry (name);
CREATE INDEX idx_data_type_short ON form_field_data_types (name);
CREATE INDEX idx_type_short ON form_field_types (name);

--Store inspection status , PENDING / APPROVED
CREATE TABLE inspection_status_md
(
    id           SERIAL PRIMARY KEY,
    code         VARCHAR(20) UNIQUE NOT NULL, -- e.g., 'PENDING'
    display_name VARCHAR(50)        NOT NULL  -- e.g., 'Pending Approval'
);

--Stores basket master data - 1,2,3
CREATE TABLE basket_md
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL UNIQUE,
    code          VARCHAR(50)    NOT NULL UNIQUE,
    basket_number INTEGER        NOT NULL UNIQUE,
    self_weight   NUMERIC(10, 3) NOT NULL, -- Precision for weight
    is_active     BOOLEAN     DEFAULT TRUE,
    sort_order    INTEGER        NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Index for code as it will likely be used in search/barcode lookups
CREATE INDEX idx_basket_md_basket_number ON basket_md (basket_number);

--Stores suppliers master data
CREATE TABLE supplier_md
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    code       VARCHAR(50)  NOT NULL UNIQUE,
    is_active  BOOLEAN               DEFAULT TRUE,
    sort_order INTEGER      NOT NULL,
    created_at TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_supplier_md_code ON supplier_md (code);
CREATE INDEX idx_supplier_md_name ON supplier_ms (name);

CREATE TABLE spool_md
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL UNIQUE,
    code         VARCHAR(50)    NOT NULL UNIQUE,
    spool_number INTEGER        NOT NULL UNIQUE,
    self_weight  NUMERIC(10, 3) NOT NULL, -- Precision for weight
    is_active    BOOLEAN     DEFAULT TRUE,
    sort_order   INTEGER        NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_spool_md_spool_number ON size_md (spool_number);

CREATE TABLE size_md
(
    id         BIGSERIAL PRIMARY KEY,
    full_name  VARCHAR(50)    NOT NULL, -- val+uom
    val        NUMERIC(15, 4) NOT NULL, -- Stores: 1.00 - value
    uom        VARCHAR(10)    NOT NULL, -- Stores: 'Sq.MM' -- unit of measure
    sort_order INTEGER        NOT NULL,
    category   VARCHAR(50),             --MMH/RBD etc
    is_active  BOOLEAN     DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_size_md_code_category UNIQUE (full_name, category)
);

-- Index for fast filtering by category and active status
CREATE INDEX idx_size_md_lookup ON size_md (category, is_active);


CREATE TABLE wire_md
(
    id         BIGSERIAL PRIMARY KEY,
    full_name  VARCHAR(50)    NOT NULL, -- e.g; 7 Wires
    val        NUMERIC(15, 4) NOT NULL, -- Stores: 1.00 - value
    uom        VARCHAR(10)    NOT NULL, -- Stores: 'calipers/micrometer' -- unit of measure
    sort_order INTEGER        NOT NULL,
    category   VARCHAR(50),             -- MMH/RBD etc
    is_active  BOOLEAN     DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_size_md_code_category UNIQUE (name, category)
);

-- Index for fast filtering by category and active status
CREATE INDEX idx_wire_md_cat ON size_md (category, is_active);
CREATE INDEX idx_wire_md_full_name ON size_md (full_name, is_active);
CREATE INDEX idx_wire_md_val_uom ON size_md (val, uom, is_active);

CREATE TABLE color_md
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL UNIQUE,
    code       VARCHAR(50)  NOT NULL UNIQUE,
    hex_code   VARCHAR(7), -- Format: #FFFFFF
    is_active  BOOLEAN     DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Index on code for fast lookup in your Java services
CREATE INDEX idx_color_name ON color_master (name);

CREATE TABLE bobbin_md
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    code          VARCHAR(50)  NOT NULL UNIQUE,
    bobbin_number INTEGER      NOT NULL UNIQUE,
    is_active     BOOLEAN     DEFAULT TRUE,
    sort_order    INTEGER      NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bobbin_md_bobbin_number ON color_master (bobbin_number);

CREATE TABLE pallet_table_md
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    code          VARCHAR(50)  NOT NULL UNIQUE,
    pallet_number INTEGER      NOT NULL UNIQUE,
    is_active     BOOLEAN     DEFAULT TRUE,
    sort_order    INTEGER      NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bobbin_md_pallet_number ON color_master (pallet_number);


CREATE TABLE uom_md
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(100)    NOT NULL,        -- Meter , LENGTH_TO_WEIGHT
    code              VARCHAR(20)     NOT NULL UNIQUE, -- M
    category          VARCHAR(50)     NOT NULL,        -- LENGTH /  AREA / WEIGHT
    conversion_factor NUMERIC(19, 10) NOT NULL DEFAULT 1.0,
    formula           VARCHAR(255)    NOT NULL,
    decimal_places    INTEGER                  DEFAULT 4,
    is_active         BOOLEAN                  DEFAULT TRUE,
    created_at        TIMESTAMPTZ              DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMPTZ              DEFAULT CURRENT_TIMESTAMP
);

-- Indexing category for faster filtered dropdowns in the UI
CREATE INDEX idx_uom_lookup ON uom_master (category, is_active);

