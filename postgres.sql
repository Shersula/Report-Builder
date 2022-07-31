--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: main; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA main;


ALTER SCHEMA main OWNER TO postgres;

--
-- Name: SCHEMA main; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA main IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Equipment; Type: TABLE; Schema: main; Owner: postgres
--

CREATE TABLE main."Equipment" (
    "ID" integer NOT NULL,
    "EquipmentName" text
);


ALTER TABLE main."Equipment" OWNER TO postgres;

--
-- Name: Eguipment_ID_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE main."Eguipment_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main."Eguipment_ID_seq" OWNER TO postgres;

--
-- Name: Eguipment_ID_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE main."Eguipment_ID_seq" OWNED BY main."Equipment"."ID";


--
-- Name: EmployeeEquipment; Type: TABLE; Schema: main; Owner: postgres
--

CREATE TABLE main."EmployeeEquipment" (
    "WorkerID" integer,
    "EquipmentID" integer,
    "WorkTime" integer[] DEFAULT '{0,0,0,0,0}'::integer[],
    "ID" integer NOT NULL
);


ALTER TABLE main."EmployeeEquipment" OWNER TO postgres;

--
-- Name: EmployeeEquipment_ID_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE main."EmployeeEquipment_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main."EmployeeEquipment_ID_seq" OWNER TO postgres;

--
-- Name: EmployeeEquipment_ID_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE main."EmployeeEquipment_ID_seq" OWNED BY main."EmployeeEquipment"."ID";


--
-- Name: Worker; Type: TABLE; Schema: main; Owner: postgres
--

CREATE TABLE main."Worker" (
    "ID" integer NOT NULL,
    "UserName" text
);


ALTER TABLE main."Worker" OWNER TO postgres;

--
-- Name: Worker_ID_seq; Type: SEQUENCE; Schema: main; Owner: postgres
--

CREATE SEQUENCE main."Worker_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main."Worker_ID_seq" OWNER TO postgres;

--
-- Name: Worker_ID_seq; Type: SEQUENCE OWNED BY; Schema: main; Owner: postgres
--

ALTER SEQUENCE main."Worker_ID_seq" OWNED BY main."Worker"."ID";


--
-- Name: EmployeeEquipment ID; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY main."EmployeeEquipment" ALTER COLUMN "ID" SET DEFAULT nextval('main."EmployeeEquipment_ID_seq"'::regclass);


--
-- Name: Equipment ID; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY main."Equipment" ALTER COLUMN "ID" SET DEFAULT nextval('main."Eguipment_ID_seq"'::regclass);


--
-- Name: Worker ID; Type: DEFAULT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY main."Worker" ALTER COLUMN "ID" SET DEFAULT nextval('main."Worker_ID_seq"'::regclass);


--
-- Data for Name: EmployeeEquipment; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO main."EmployeeEquipment" VALUES (5, 13, '{12,12,10,12,11}', 1);
INSERT INTO main."EmployeeEquipment" VALUES (10, 19, '{3,4,3,6,9}', 2);
INSERT INTO main."EmployeeEquipment" VALUES (7, 4, '{2,11,4,10,11}', 3);
INSERT INTO main."EmployeeEquipment" VALUES (12, 4, '{11,9,6,7,3}', 4);
INSERT INTO main."EmployeeEquipment" VALUES (3, 17, '{4,9,11,2,8}', 5);
INSERT INTO main."EmployeeEquipment" VALUES (13, 18, '{4,7,7,10,9}', 6);
INSERT INTO main."EmployeeEquipment" VALUES (4, 15, '{2,9,3,10,2}', 7);
INSERT INTO main."EmployeeEquipment" VALUES (2, 17, '{8,2,7,6,6}', 8);
INSERT INTO main."EmployeeEquipment" VALUES (9, 11, '{3,6,5,1,7}', 9);
INSERT INTO main."EmployeeEquipment" VALUES (12, 5, '{2,1,2,1,6}', 10);
INSERT INTO main."EmployeeEquipment" VALUES (14, 4, '{5,4,12,8,9}', 11);
INSERT INTO main."EmployeeEquipment" VALUES (14, 6, '{8,9,11,3,12}', 12);
INSERT INTO main."EmployeeEquipment" VALUES (13, 5, '{8,2,2,5,9}', 13);
INSERT INTO main."EmployeeEquipment" VALUES (2, 17, '{1,7,3,10,3}', 14);
INSERT INTO main."EmployeeEquipment" VALUES (2, 6, '{6,1,2,8,9}', 15);
INSERT INTO main."EmployeeEquipment" VALUES (4, 21, '{7,6,6,7,3}', 16);
INSERT INTO main."EmployeeEquipment" VALUES (10, 22, '{10,8,9,11,6}', 17);
INSERT INTO main."EmployeeEquipment" VALUES (2, 16, '{5,5,8,7,8}', 18);
INSERT INTO main."EmployeeEquipment" VALUES (10, 9, '{4,4,12,6,6}', 19);
INSERT INTO main."EmployeeEquipment" VALUES (14, 20, '{10,9,11,7,12}', 20);
INSERT INTO main."EmployeeEquipment" VALUES (15, 2, '{11,5,7,1,12}', 21);
INSERT INTO main."EmployeeEquipment" VALUES (8, 8, '{6,6,9,6,7}', 22);
INSERT INTO main."EmployeeEquipment" VALUES (15, 21, '{8,2,10,3,7}', 23);
INSERT INTO main."EmployeeEquipment" VALUES (10, 22, '{12,12,2,11,8}', 24);
INSERT INTO main."EmployeeEquipment" VALUES (13, 2, '{12,3,6,10,11}', 25);
INSERT INTO main."EmployeeEquipment" VALUES (2, 11, '{5,8,12,9,1}', 26);
INSERT INTO main."EmployeeEquipment" VALUES (8, 4, '{7,3,9,7,3}', 27);
INSERT INTO main."EmployeeEquipment" VALUES (12, 9, '{11,2,11,4,2}', 28);
INSERT INTO main."EmployeeEquipment" VALUES (6, 11, '{8,3,9,11,11}', 29);
INSERT INTO main."EmployeeEquipment" VALUES (9, 5, '{2,11,2,10,4}', 30);
INSERT INTO main."EmployeeEquipment" VALUES (2, 6, '{1,9,5,8,8}', 31);
INSERT INTO main."EmployeeEquipment" VALUES (9, 7, '{7,9,3,4,3}', 32);
INSERT INTO main."EmployeeEquipment" VALUES (8, 10, '{11,6,5,2,5}', 33);
INSERT INTO main."EmployeeEquipment" VALUES (7, 21, '{3,9,5,5,9}', 34);
INSERT INTO main."EmployeeEquipment" VALUES (7, 7, '{11,7,2,4,1}', 35);
INSERT INTO main."EmployeeEquipment" VALUES (9, 13, '{9,4,11,1,11}', 36);
INSERT INTO main."EmployeeEquipment" VALUES (1, 2, '{5,11,1,5,12}', 37);
INSERT INTO main."EmployeeEquipment" VALUES (9, 2, '{10,12,11,12,8}', 38);
INSERT INTO main."EmployeeEquipment" VALUES (7, 21, '{9,7,12,4,2}', 39);
INSERT INTO main."EmployeeEquipment" VALUES (13, 21, '{2,7,7,3,11}', 40);
INSERT INTO main."EmployeeEquipment" VALUES (12, 15, '{11,1,6,11,6}', 41);
INSERT INTO main."EmployeeEquipment" VALUES (5, 1, '{12,4,5,6,12}', 42);
INSERT INTO main."EmployeeEquipment" VALUES (5, 14, '{8,7,10,2,12}', 43);
INSERT INTO main."EmployeeEquipment" VALUES (15, 17, '{12,5,10,5,10}', 44);
INSERT INTO main."EmployeeEquipment" VALUES (12, 15, '{12,7,7,9,3}', 45);
INSERT INTO main."EmployeeEquipment" VALUES (11, 19, '{5,10,8,5,2}', 46);
INSERT INTO main."EmployeeEquipment" VALUES (10, 9, '{6,9,2,11,5}', 47);
INSERT INTO main."EmployeeEquipment" VALUES (8, 19, '{10,12,6,12,3}', 48);
INSERT INTO main."EmployeeEquipment" VALUES (1, 17, '{10,8,12,3,11}', 49);
INSERT INTO main."EmployeeEquipment" VALUES (13, 12, '{3,4,3,2,8}', 50);
INSERT INTO main."EmployeeEquipment" VALUES (14, 19, '{12,2,4,6,3}', 51);
INSERT INTO main."EmployeeEquipment" VALUES (5, 9, '{9,2,7,4,3}', 52);
INSERT INTO main."EmployeeEquipment" VALUES (14, 9, '{4,6,3,12,6}', 53);
INSERT INTO main."EmployeeEquipment" VALUES (5, 10, '{12,6,6,3,10}', 54);
INSERT INTO main."EmployeeEquipment" VALUES (12, 13, '{3,1,7,9,5}', 55);
INSERT INTO main."EmployeeEquipment" VALUES (1, 20, '{7,1,10,12,4}', 56);
INSERT INTO main."EmployeeEquipment" VALUES (14, 6, '{9,9,8,4,12}', 57);
INSERT INTO main."EmployeeEquipment" VALUES (11, 19, '{9,5,5,2,4}', 58);
INSERT INTO main."EmployeeEquipment" VALUES (7, 10, '{3,10,5,3,9}', 59);
INSERT INTO main."EmployeeEquipment" VALUES (3, 10, '{11,5,4,5,9}', 60);


--
-- Data for Name: Equipment; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO main."Equipment" VALUES (1, 'Комплекс контрольно-моделирующий');
INSERT INTO main."Equipment" VALUES (2, 'Устройство поворотное трехстепенное');
INSERT INTO main."Equipment" VALUES (3, 'Генераторы нормированного магнитного поля');
INSERT INTO main."Equipment" VALUES (4, 'Модуль эталонный трехкомпонентный магнитометрический');
INSERT INTO main."Equipment" VALUES (5, 'Комплекс автоматизированный магнитометрический');
INSERT INTO main."Equipment" VALUES (6, 'Комплекс измерительный автоматизированный');
INSERT INTO main."Equipment" VALUES (7, 'Уровнемеры');
INSERT INTO main."Equipment" VALUES (8, 'Пульты проверки систем');
INSERT INTO main."Equipment" VALUES (9, 'Расходомеры трехкомпонентных газожидкостных потоков природного газа');
INSERT INTO main."Equipment" VALUES (10, 'Комплекты устройств поверки');
INSERT INTO main."Equipment" VALUES (11, 'Радиопередающие устройства');
INSERT INTO main."Equipment" VALUES (12, 'Системы автоматического управления САУиР ГПА');
INSERT INTO main."Equipment" VALUES (13, 'Комплексы бортовые телеметрические микропроцессорные');
INSERT INTO main."Equipment" VALUES (14, 'Устройства радиоприемные');
INSERT INTO main."Equipment" VALUES (15, 'Устройства пристроенного контроля');
INSERT INTO main."Equipment" VALUES (16, 'Комплексы автоматизированные измерительные морские');
INSERT INTO main."Equipment" VALUES (17, 'Радиоинтерферометры');
INSERT INTO main."Equipment" VALUES (18, 'Комплексы телемеханики');
INSERT INTO main."Equipment" VALUES (19, 'Контроллеры');
INSERT INTO main."Equipment" VALUES (20, 'Комплексы телемеханики');
INSERT INTO main."Equipment" VALUES (21, 'Системы управления вспомогательным оборудованием компрессорного цеха');
INSERT INTO main."Equipment" VALUES (22, 'Уровнемеры ультразвуковые');


--
-- Data for Name: Worker; Type: TABLE DATA; Schema: main; Owner: postgres
--

INSERT INTO main."Worker" VALUES (1, 'Иванов Иван Иванович');
INSERT INTO main."Worker" VALUES (2, 'Осипова Алёна Данииловна');
INSERT INTO main."Worker" VALUES (3, 'Колесова Полина Данииловна');
INSERT INTO main."Worker" VALUES (4, 'Комаров Матвей Иванович');
INSERT INTO main."Worker" VALUES (5, 'Волков Александр Львович');
INSERT INTO main."Worker" VALUES (6, 'Агеев Мирон Николаевич');
INSERT INTO main."Worker" VALUES (7, 'Майоров Константин Иванович');
INSERT INTO main."Worker" VALUES (8, 'Захарова Александра Максимовна');
INSERT INTO main."Worker" VALUES (9, 'Константинова Амелия Антоновна');
INSERT INTO main."Worker" VALUES (10, 'Лавров Пётр Макарович');
INSERT INTO main."Worker" VALUES (11, 'Алексеев Илья Фёдорович');
INSERT INTO main."Worker" VALUES (12, 'Григорьев Артём Давидович');
INSERT INTO main."Worker" VALUES (13, 'Савина Кристина Егоровна');
INSERT INTO main."Worker" VALUES (14, 'Иванова Дарья Григорьевна');
INSERT INTO main."Worker" VALUES (15, 'Егоров Георгий Максимович');


--
-- Name: Eguipment_ID_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('main."Eguipment_ID_seq"', 23, true);


--
-- Name: EmployeeEquipment_ID_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('main."EmployeeEquipment_ID_seq"', 60, true);


--
-- Name: Worker_ID_seq; Type: SEQUENCE SET; Schema: main; Owner: postgres
--

SELECT pg_catalog.setval('main."Worker_ID_seq"', 15, true);


--
-- Name: Equipment Eguipment_ID_key; Type: CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY main."Equipment"
    ADD CONSTRAINT "Eguipment_ID_key" UNIQUE ("ID");


--
-- Name: Worker Worker_ID_key; Type: CONSTRAINT; Schema: main; Owner: postgres
--

ALTER TABLE ONLY main."Worker"
    ADD CONSTRAINT "Worker_ID_key" UNIQUE ("ID");


--
-- Name: ID; Type: INDEX; Schema: main; Owner: postgres
--

CREATE UNIQUE INDEX "ID" ON main."EmployeeEquipment" USING btree ("ID");


--
-- PostgreSQL database dump complete
--