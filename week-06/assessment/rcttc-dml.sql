use rcttc;
-- INSERT
-- insert into theater
insert into theater (theater_name, address, phone, email)
	select distinct theater, theater_address, theater_phone, theater_email
    from rcttc_data rd;
    
-- insert into customer
insert into customer (first_name, last_name, customer_email, customer_phone, customer_address)
	select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
    from rcttc_data rd;
    
-- insert into shows
insert into shows (theater_id, title, show_date, ticket_price)
	select distinct t.theater_id, `show`, `date`, ticket_price
    from rcttc_data rd
    inner join theater t on t.email = rd.theater_email; -- natural ID to get distinct theater

-- insert into ticket
insert into ticket (show_id, customer_id, seat)
	select s.show_id, c.customer_id, seat
    from rcttc_data rd
    inner join shows s on s.title = rd.`show` and s.show_date = rd.`date`
    inner join customer c on c.customer_email = rd.customer_email;
    

-- UPDATE

update shows set
	ticket_price = '22.25'
     where show_id = '5';
    
 
    