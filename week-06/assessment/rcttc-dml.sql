use rcttc;

-- insert into theater
insert into theater (theater_name, address, phone, email)
	select distinct (theater, theater_address, theater_phone, theater_email)
    from rcttc_data 