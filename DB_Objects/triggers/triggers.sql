create or replace trigger tr_user
before insert
on "USER"
for each row

declare
u_id varchar2(11);

begin
u_id := 'U' + user_userid.nextval;
insert into user
values(
u_id,
:new.firstname,
:new.middlename,
:new.lastname,
:new.emailid,
:new.groupid);
end;