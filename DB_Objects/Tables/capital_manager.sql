ALTER TABLE CAPITAL_MANAGER.LOGIN_DETAILS
 DROP PRIMARY KEY CASCADE;

DROP TABLE CAPITAL_MANAGER.LOGIN_DETAILS CASCADE CONSTRAINTS;

CREATE TABLE CAPITAL_MANAGER.LOGIN_DETAILS
(
  ID        NUMBER,
  EMAILID   VARCHAR2(100 BYTE)                  NOT NULL,
  PASSWORD  VARCHAR2(256 BYTE)                  NOT NULL
)
TABLESPACE TBS_ANUJA
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
MONITORING;


--  There is no statement for index CAPITAL_MANAGER.SYS_C007206.
--  The object is created when the parent object is created.

ALTER TABLE CAPITAL_MANAGER.LOGIN_DETAILS ADD (
  PRIMARY KEY
  (ID)
  USING INDEX
    TABLESPACE TBS_ANUJA
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               )
  ENABLE VALIDATE);
