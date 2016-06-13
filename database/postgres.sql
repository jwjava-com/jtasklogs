--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: daily_totals; Type: TABLE; Schema: public; Tablespace: 
--

CREATE TABLE daily_totals (
    totals_date date NOT NULL,
    task_id integer NOT NULL,
    total numeric NOT NULL
);


--
-- Name: entries; Type: TABLE; Schema: public; Tablespace: 
--

CREATE TABLE entries (
    task_id integer NOT NULL,
    start_timestamp timestamp with time zone NOT NULL
);


--
-- Name: tasks; Type: TABLE; Schema: public; Tablespace: 
--

CREATE TABLE tasks (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    is_billable boolean NOT NULL
);


--
-- Name: tasks_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE tasks_id_seq OWNED BY tasks.id;


--
-- Name: timesheets; Type: TABLE; Schema: public; Tablespace: 
--

CREATE TABLE timesheets (
    week_end_date date NOT NULL,
    billable_total numeric,
    nonbillable_total numeric
);


--
-- Name: weekly_totals; Type: TABLE; Schema: public; Tablespace: 
--

CREATE TABLE weekly_totals (
    week_end_date date NOT NULL,
    task_id integer NOT NULL,
    total numeric NOT NULL
);


--
-- Name: id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY tasks ALTER COLUMN id SET DEFAULT nextval('tasks_id_seq'::regclass);


--
-- Name: tasks_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('tasks_id_seq', 1, false);


--
-- Name: tasks_pkey; Type: CONSTRAINT; Schema: public; Tablespace: 
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: timesheets_pkey; Type: CONSTRAINT; Schema: public; Tablespace: 
--

ALTER TABLE ONLY timesheets
    ADD CONSTRAINT timesheets_pkey PRIMARY KEY (week_end_date);


--
-- Name: daily_totals_task_id_fkey; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY daily_totals
    ADD CONSTRAINT daily_totals_task_id_fkey FOREIGN KEY (task_id) REFERENCES tasks(id);


--
-- Name: entries_fkey; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY entries
    ADD CONSTRAINT entries_fkey FOREIGN KEY (task_id) REFERENCES tasks(id);


--
-- Name: weekly_totals_task_id_fkey; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY weekly_totals
    ADD CONSTRAINT weekly_totals_task_id_fkey FOREIGN KEY (task_id) REFERENCES tasks(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

