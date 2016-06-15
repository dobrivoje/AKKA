/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  root
 * Created: Jun 15, 2016
 */

create table USERS (
    IDU bigint NOT NULL primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),  
    Name varchar(40) NOT NULL,
    Surname varchar(40) NOT NULL,
    Username varchar(40) NOT NULL
);

create table CLICK (
    IDC  bigint NOT NULL primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1), 
    FK_IDU  bigint NOT NULL,
    Date DATE,
    IPADDRESS varchar(40),
    ClicksTotal int,
    FOREIGN KEY (FK_IDU) REFERENCES USERS (IDU)
)
