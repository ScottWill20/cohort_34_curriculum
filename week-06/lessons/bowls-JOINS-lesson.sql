
-- SQL JOINS

---------------
-- INNER JOINS 

use bowls;

select
    customer.customer_id, -- customer_id from customer, not login
    customer.last_name,   -- last_name is in the customer table
    login.user_name       -- user_name is in the login table
from customer
inner join login on customer.customer_id = login.customer_id;
-- The customer table's customer_id is a foreign key in the login table.
-- The `on` condition defines how rows relate between the two tables.

-- Below, we filter the customer table's last_name down to names that start with "M". 
-- Then we sort by the login's user_name descending.

select *
from customer
inner join login on customer.customer_id = login.customer_id
where customer.last_name like 'M%'
order by login.user_name desc;

-- Below, we match customers to menu items. We start with a customer, move to their order, join the order_item bridge table, 
-- and finally get the item name from the item table.

select
    customer.last_name,
    `order`.order_id,
    item.name
from customer
inner join `order` on customer.customer_id = `order`.customer_id
inner join order_item on `order`.order_id = order_item.order_id
inner join item on order_item.item_id = item.item_id
where date(order_date) = '2020-07-28';

-- Typing full table names in join queries is a source of frustration and error. 
-- We can create a short, easy-to-remember alias for each table by providing a name immediately after the table in the join clause.

-- The following queries are equivalent. They return exactly the same results.
-- Without aliases.
select
    customer.last_name,
    `order`.order_id,
    item.name
from customer
inner join `order` on customer.customer_id = `order`.customer_id
inner join order_item on `order`.order_id = order_item.order_id
inner join item on order_item.item_id = item.item_id
where date(order_date) = '2020-07-28';

-- With aliases.
-- customer aliased as c
-- `order` aliased as o
-- order_item aliased as oi
-- item aliased as i
select
    c.last_name,
    o.order_id,
    i.name
from customer c
inner join `order` o on c.customer_id = o.customer_id
inner join order_item oi on o.order_id = oi.order_id
inner join item i on oi.item_id = i.item_id
where date(o.order_date) = '2020-07-28';

-- Below, we join the order_status table to itself to determine child and parent statuses. 
-- We use column aliases and table aliases to keep our logic straight.

select
    child.order_status_id child_id,
    child.name child_name,
    parent.order_status_id parent_id,
    parent.name parent_name
from order_status child
inner join order_status parent 
    on child.parent_order_status_id = parent.order_status_id;

-- Below, we calculate the cost of an order line item by multiplying the item's price by the order_item quantity.

select
    i.name,
    oi.quantity,
    i.price,
    oi.quantity * i.price line_item_total
from order_item oi
inner join item i on oi.item_id = i.item_id
where oi.order_id = 205
order by oi.quantity * i.price desc;

-------------------
-- LEFT OUTER JOINS 

-- The left outer join retains all rows on the left side of the join regardless of whether they have a matched row on the right. 
-- "Left side" and "right side" are a little confusing here. 
	-- Left means the table before the join clause. 
    -- Right is the table named in the clause.
    
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c -- customer is on the "left", its rows are always included
left outer join login l on c.customer_id = l.customer_id; -- login is on the "right"

-- Two customers in the sample below don't have logins. The login values are null because there's no login row that matches the customer. 
-- There are 470 login records. This query returns all 1000 customers. 
-- It includes the 470 logins for the customers that match. For the remaining customers, login values are null.

-- When we filter in the where clause, the results may be surprising. When we filter from the left table, the query works as expected. 
-- There are 75 customers whose last name starts with "M". 
-- The query below returns those customers. Some have logins and some don't.

select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l on l.customer_id = c.customer_id
where c.last_name like 'M%';

-- But when we filter the right table, the SQL engine evaluates our left outer join like an inner join. 
-- The like condition evaluates to false for all null values, so every customer without a login is filtered out. 
-- This is effectively an inner join enforced through a where condition.

select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l on l.customer_id = c.customer_id
where l.user_name like '%.com'; -- find emails ending with .com

-- First, we can move the filter to the on. 
-- This includes all 1000 customers, but only includes logins with a user_name that ends with ".com".

select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l 
    on l.customer_id = c.customer_id -- compound on condition
    and l.user_name like '%.com';
    
-- The ifnull function allows null values through and replaces them with a default value. 
-- If the default is ".com", all null logins and logins ending with ".com" will match. 
-- This query returns 791 customers.

select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l 
    on l.customer_id = c.customer_id
where ifnull(l.user_name, '.com') like '%.com'; -- give null a default value

-- Finding Missing Records

-- To find records without a match, put an is null filter in the where clause.

select
    c.customer_id,
    c.first_name,
    c.last_name  
from customer c
left outer join login l on l.customer_id = c.customer_id
where l.customer_id is null;

-- Bowls has customers who haven't ordered. To find them, left join the customer to the order and look for null orders.

select
    c.customer_id,
    c.first_name,
    c.last_name  
from customer c
left outer join `order` o on c.customer_id = o.customer_id
where o.order_id is null;
 -- 446 customers don't have an order.

-- Finally, determine if there are menu items that have never been ordered. 
-- Left join the item table to order_item and filter for null order_items.

select
    i.name
from item i
left outer join order_item oi on i.item_id = oi.item_id
where oi.order_item_id is null;

-- As a final demonstration, we join all 6 tables in the database and display a summary. 
-- Our primary goal is to view all customers. 
-- The secondary goal is to view logins, orders, order statuses, and ordered items when they exist. 
-- All joins must be left outer so that we don't eliminate customers.

select
    concat(c.first_name, " ", c.last_name) customer,
    l.user_name,
    o.order_date,
    oi.order_id,
    i.name menu_item,
    os.name 'status'
from customer c
left outer join login l on c.customer_id = l.customer_id
left outer join `order` o on c.customer_id = o.customer_id
left outer join order_item oi on o.order_id = oi.order_id
left outer join item i on oi.item_id = i.item_id
left outer join order_status os on o.order_status_id = os.order_status_id;

-- Results "fan out". Each customer can have many orders which can have many items. The query returns well over 1000 rows.















