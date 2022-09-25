use rcttc;


    


-- Find all performances in the last quarter of 2021 (Oct. 1, 2021 - Dec. 31 2021).
select * from shows
	where show_date between '2021-10-1' and '2021-12-31';
    
-- List customers without duplication.
select * from customer;

-- Find all customers without a .com email address.
select * from customer
	where customer_email not like '%.com';
    
-- Find the three cheapest shows.
select * from shows
	order by ticket_price asc
    limit 3;

-- List customers and the show they're attending with no duplication.
	-- How?? customers attend multiple shows????

-- List customer, show, theater, and seat number in one query.
select concat(c.first_name, ' ', c.last_name),
    s.title,
    th.theater_name,
    t.seat
    from ticket t
		inner join customer c on c.customer_id = t.customer_id
		inner join shows s on s.show_id = t.show_id     
        inner join theater th on th.theater_id = s.theater_id   
        order by c.customer_id asc;   

-- Find customers without an address.
-- Recreate the spreadsheet data with a single query.
-- Count total tickets purchased per customer.
-- Calculate the total revenue per show based on tickets sold.
-- Calculate the total revenue per theater based on tickets sold.
-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?