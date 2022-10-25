-- PostgreSQL database dump:
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_tablespace = '';
SET default_with_oids = false;

DROP TABLE IF EXISTS tb_motorcycle;

CREATE TABLE tb_motorcycle(
  id smallint NOT NULL,
  modelo character varying(70) NOT NULL,
  ano character varying(4) NOT NULL,
  placa character varying(7) NOT NULL,
  categoria character varying(70) NOT NULL,
  cor character varying(20) NOT NULL, 
  responsavel character varying(130) NOT NULL,
  dataRegistro date
);