DROP DATABASE IF EXISTS buenaventura;
CREATE DATABASE buenaventura
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;