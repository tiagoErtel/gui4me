-- === EMAIL VERIFICATION TOKENS ===
CREATE TABLE public.user_verification_tokens (
    id TEXT PRIMARY KEY,
    token TEXT NOT NULL UNIQUE,
    user_id TEXT NOT NULL UNIQUE,
    expires_at TIMESTAMP(6) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_verification_token_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

ALTER TABLE public.user_verification_tokens OWNER TO "admin";

GRANT ALL ON TABLE public.user_verification_tokens TO "admin";
