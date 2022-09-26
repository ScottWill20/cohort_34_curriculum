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
select distinct c.customer_id, concat(c.first_name, ' ', c.last_name) 'customer', s.title
	from ticket t
		inner join customer c on c.customer_id = t.customer_id
		inner join shows s on s.show_id = t.show_id
        order by c.customer_id;
        

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
select * from customer
where customer_address = '';

-- Recreate the spreadsheet data with a single query.
select c.first_name, c.last_name, c.customer_email, c.customer_phone, c.customer_address, 
t.seat, s.title, s.ticket_price, s.show_date, 
th.theater_name, th.address, th.phone, th.email
	from ticket t
		inner join customer c on c.customer_id = t.customer_id
		inner join shows s on s.show_id = t.show_id     
        inner join theater th on th.theater_id = s.theater_id   
        order by c.customer_id asc;

-- Count total tickets purchased per customer.
select  c.customer_id, concat(c.first_name, ' ', c.last_name) customer, count(ticket_id) total_tickets
	from ticket t
		inner join customer c on c.customer_id = t.customer_id
		group by t.customer_id
		order by count(ticket_id) desc;
        
-- Calculate the total revenue per show based on tickets sold.
select s.title, sum(s.ticket_price)
	from ticket ti
		inner join shows s on s.show_id = ti.show_id  
		group by s.title
		order by sum(s.ticket_price);

-- Calculate the total revenue per theater based on tickets sold.
        
select th.theater_name, sum(s.ticket_price)
	from ticket ti
		inner join shows s on s.show_id = ti.show_id
		inner join theater th on th.theater_id = s.theater_id  
        group by th.theater_id
        order by sum(s.ticket_price);
        

-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?
select c.customer_id, concat(c.first_name, ' ', c.last_name) customer, sum(ticket_price) top_customers
	from ticket ti
		inner join customer c on c.customer_id = ti.customer_id
        inner join shows s on s.show_id = ti.show_id
        group by c.customer_id
        order by top_customers desc
        limit 1;

