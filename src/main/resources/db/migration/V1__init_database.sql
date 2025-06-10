-- Create schema
CREATE SCHEMA IF NOT EXISTS public AUTHORIZATION "admin";

COMMENT ON SCHEMA public IS 'Standard public schema';

-- === USERS ===
CREATE TABLE public.users (
    id TEXT PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT,
    username TEXT NOT NULL
);

ALTER TABLE public.users OWNER TO "admin";

GRANT ALL ON TABLE public.users TO "admin";

-- === PRODUCTS ===
CREATE TABLE public.products (id TEXT PRIMARY KEY, name TEXT NOT NULL);

ALTER TABLE public.products OWNER TO "admin";

GRANT ALL ON TABLE public.products TO "admin";

-- === STORES ===
CREATE TABLE public.stores (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    document TEXT NOT NULL UNIQUE,
    fantasy_name TEXT,
    type TEXT,
    size TEXT,
    email TEXT,
    phone TEXT,
    street TEXT,
    number TEXT,
    complement TEXT,
    neighborhood TEXT,
    city TEXT,
    state TEXT,
    zip_code TEXT
);

ALTER TABLE public.stores OWNER TO "admin";

GRANT ALL ON TABLE public.stores TO "admin";

-- === SHOPPING LISTS ===
CREATE TABLE public.shopping_lists (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    user_id TEXT NOT NULL,
    CONSTRAINT fk_shopping_lists_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

ALTER TABLE public.shopping_lists OWNER TO "admin";

GRANT ALL ON TABLE public.shopping_lists TO "admin";

-- === SHOPPING LIST ITEMS ===
CREATE TABLE public.shopping_list_item (
    id TEXT PRIMARY KEY,
    quantity DOUBLE PRECISION NOT NULL,
    product_id TEXT NOT NULL,
    shopping_list_id TEXT NOT NULL,
    CONSTRAINT fk_sli_product FOREIGN KEY (product_id) REFERENCES public.products (id),
    CONSTRAINT fk_sli_list FOREIGN KEY (shopping_list_id) REFERENCES public.shopping_lists (id)
);

ALTER TABLE public.shopping_list_item OWNER TO "admin";

GRANT ALL ON TABLE public.shopping_list_item TO "admin";

-- === INVOICES ===
CREATE TABLE public.invoices (
    id TEXT PRIMARY KEY,
    issuance_date TIMESTAMP(6) NOT NULL,
    key TEXT NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    store_id TEXT NOT NULL,
    user_id TEXT NOT NULL,
    CONSTRAINT fk_invoice_store FOREIGN KEY (store_id) REFERENCES public.stores (id),
    CONSTRAINT fk_invoice_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

ALTER TABLE public.invoices OWNER TO "admin";

GRANT ALL ON TABLE public.invoices TO "admin";

-- === INVOICE ITEMS ===
CREATE TABLE public.invoice_items (
    id TEXT PRIMARY KEY,
    quantity DOUBLE PRECISION NOT NULL,
    unit TEXT NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    invoice_id TEXT NOT NULL,
    product_id TEXT NOT NULL,
    CONSTRAINT fk_invoice_item_invoice FOREIGN KEY (invoice_id) REFERENCES public.invoices (id),
    CONSTRAINT fk_invoice_item_product FOREIGN KEY (product_id) REFERENCES public.products (id)
);

ALTER TABLE public.invoice_items OWNER TO "admin";

GRANT ALL ON TABLE public.invoice_items TO "admin";

-- === Permissions on schema ===
GRANT ALL ON SCHEMA public TO "admin";

GRANT USAGE ON SCHEMA public TO public;
