--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-07-09 14:00:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 196683)
-- Name: ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient (
    id character varying(4) NOT NULL,
    name character varying(20) NOT NULL,
    type character varying(10) NOT NULL
);


ALTER TABLE public.ingredient OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 196694)
-- Name: ingredient_reference; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient_reference (
    id character varying(4) NOT NULL,
    taco_id bigint NOT NULL,
    ingredient_id character varying(4) NOT NULL
);


ALTER TABLE public.ingredient_reference OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 196676)
-- Name: taco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.taco (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    taco_order_id bigint NOT NULL
);


ALTER TABLE public.taco OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 196682)
-- Name: taco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.taco ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.taco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 196643)
-- Name: taco_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.taco_order (
    id bigint NOT NULL,
    delivery_name character varying(30) NOT NULL,
    delivery_street character varying(30) NOT NULL,
    delivery_city character varying(20) NOT NULL,
    delivery_state character varying(2) NOT NULL,
    delivery_zip character varying(10) NOT NULL,
    cc_number character varying(16) NOT NULL,
    cc_expiration character varying(5) NOT NULL,
    cc_cvv character varying(3) NOT NULL,
    placed_on timestamp without time zone NOT NULL
);


ALTER TABLE public.taco_order OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 196681)
-- Name: taco_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.taco_order ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.taco_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3343 (class 0 OID 196683)
-- Dependencies: 218
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredient (id, name, type) FROM stdin;
FLTO	Flour Tortilla	WRAP
COTO	Corn Tortilla	WRAP
GRBF	Ground Beef	PROTEIN
CARN	Carnitas	PROTEIN
TMTO	Diced Tomatoes	VEGGIES
LETC	Lettuce	VEGGIES
CHED	Cheddar	CHEESE
JACK	Monterrey Jack	CHEESE
SLSA	Salsa	SAUCE
SRCR	Sour Cream	SAUCE
\.


--
-- TOC entry 3344 (class 0 OID 196694)
-- Dependencies: 219
-- Data for Name: ingredient_reference; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredient_reference (id, taco_id, ingredient_id) FROM stdin;
\.


--
-- TOC entry 3340 (class 0 OID 196676)
-- Dependencies: 215
-- Data for Name: taco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.taco (id, name, "createdOn", taco_order_id) FROM stdin;
\.


--
-- TOC entry 3339 (class 0 OID 196643)
-- Dependencies: 214
-- Data for Name: taco_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.taco_order (id, delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_on) FROM stdin;
\.


--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 217
-- Name: taco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.taco_id_seq', 1, false);


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 216
-- Name: taco_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.taco_order_id_seq', 1, false);


--
-- TOC entry 3191 (class 2606 OID 196687)
-- Name: ingredient ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 196698)
-- Name: ingredient_reference ingredient_reference_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient_reference
    ADD CONSTRAINT ingredient_reference_pkey PRIMARY KEY (id);


--
-- TOC entry 3187 (class 2606 OID 196649)
-- Name: taco_order taco_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taco_order
    ADD CONSTRAINT taco_order_pkey PRIMARY KEY (id);


--
-- TOC entry 3189 (class 2606 OID 196680)
-- Name: taco taco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taco
    ADD CONSTRAINT taco_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 196704)
-- Name: ingredient_reference ingredient_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient_reference
    ADD CONSTRAINT ingredient_id FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(id) NOT VALID;


--
-- TOC entry 3196 (class 2606 OID 196699)
-- Name: ingredient_reference taco_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient_reference
    ADD CONSTRAINT taco_id FOREIGN KEY (taco_id) REFERENCES public.taco(id) NOT VALID;


--
-- TOC entry 3194 (class 2606 OID 196709)
-- Name: taco taco_order_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taco
    ADD CONSTRAINT taco_order_id FOREIGN KEY (taco_order_id) REFERENCES public.taco_order(id) NOT VALID;


-- Completed on 2023-07-09 14:00:12

--
-- PostgreSQL database dump complete
--

