
-- Drop the person table if it exists
DROP TABLE IF EXISTS public.person;

CREATE TABLE public.person (
	id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
	full_name varchar NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
);
