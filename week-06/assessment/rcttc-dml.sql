use rcttc;

	----------
	-- INSERT
	----------
    
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

	----------
	-- UPDATE
	----------

-- 1.) UPDATE ticket_price for The Little Fitz's 2021-03-01 performance of The Sky Lit Up
	-- $20 -> $22.25

update shows set
	ticket_price = '22.25'
     where show_id = '5';
     
-- select * from shows;

-- 2.) UPDATE seats for The Little Fitz's 2021-03-01 performance of The Sky Lit Up
	-- Pooh Bedburrow and Cullen Guirau tickets on same row
    -- keep groups together
    -- no double-bookings
    
-- select * from ticket
--  order by show_id asc, seat asc;
     
update ticket 
	set seat = (case when seat = 'A4' then 'B4'
					 when seat = 'B4' then 'C2'
					 when seat = 'C2' then 'A4' end)
	where seat in ('A4', 'B4', 'C2') and
		  show_id = '5';
          

-- 3.) UPDATE Jammie Swindles phone number
	-- 801-514-8648 -> 1-801-EAT-CAKE

set sql_safe_updates = 0;

update customer
	set	customer_phone = '1-801-EAT-CAKE'
    where first_name = 'Jammie' and last_name = 'Swindles';

set sql_safe_updates = 1;
    
-- select * from customer
-- where first_name = 'Jammie' and last_name = 'Swindles';

	----------
	-- DELETE
	----------

-- 1.) delete all single-ticket reservations at the 10 Pin.
-- FIRST SOLUTION - FORCES INDIVIDUAL ID LOOKUP BEFOREHAND

    -- HOW I FOUND THE TICKET ID's TO DELETE:
-- select c.customer_id,
-- 	count(ticket_id) 'single tickets',
--     s.show_id,
--     th.theater_id
--     from ticket t
-- 		inner join customer c on c.customer_id = t.customer_id
-- 		inner join shows s on s.show_id = t.show_id     
--         inner join theater th on th.theater_id = s.theater_id   
--         where th.theater_id = 1
--         group by c.customer_id, s.show_id
--         having count(ticket_id) = 1
--         order by c.customer_id asc, s.show_date desc;       

-- select * from customer;

-- select * from ticket
-- order by show_id asc, seat asc;

-- delete from ticket
-- where ticket_id in (25,26,39,41,50,51,59,67,68);

-- EASIER SOLUTION - DELETES ANY TICKET ASSOCIATED WITH A CUSTOMER WITH ONLY ONE TICKET PURCHASED PER SHOW AT THE 10 PIN:

set sql_safe_updates = 0;
delete from ticket
where customer_id in (
	select cid from (
		select c.customer_id as cid from customer c
			inner join ticket ti on ti.customer_id = c.customer_id
			inner join shows s on s.show_id = ti.show_id
			inner join theater th on th.theater_id = s.theater_id
			where th.theater_id = 1) as single_ticket
				group by cid having count(customer_id) = 1);
set sql_safe_updates = 1;

select * from ticket
where show_id = 1;
        
        
-- 2.) Delete the customer Liv Egle of Germany.

select * from customer
order by last_name;

-- Liv Egle of Germany's customer ID is 65.

delete from ticket -- delete from children first
where customer_id = 65;

delete from customer
where customer_id = 65;

select * from customer
where customer_id between 64 and 66; -- Liv is gone


