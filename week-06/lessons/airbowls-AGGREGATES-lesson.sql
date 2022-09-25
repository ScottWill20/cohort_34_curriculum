
-- AGGREGATES

-- To aggregate is to take many values and reduce them down to one value.

-- COUNT
use airbnb_nyc;

select count(listing_id) -- counts non-null listing_ids
from listing;

-- The count function only counts non-null values, so if we count a nullable column we see fewer results.
select count(last_review)
from listing;

-- Count is the only aggregate function that can accept the star * expression.
select count(*)
from listing;


-- AVG

-- Any aggregate query can include a where clause. Records are filtered before the aggregate is applied.
select avg(price)
from listing
where neighbourhood_group = 'Manhattan'
and price > 0;
-- The average price of a listing in Manhattan excluding zero-priced listings is 195.070712.

-- Below, we calculate the average minimum price in Harlem, excluding zero-priced listings.
select 
    avg(price * minimum_nights) avg_min_price
from listing
where neighbourhood = 'Harlem'
and price > 0;

-- MIN and MAX

-- We can find the latest review for any listing in Williamsburg with this query.
select 
    max(last_review)
from listing
where neighbourhood = 'Williamsburg';

-- Multiple aggregates in one query

-- Below, we count listings in Williamsburg, calculate their min, max, and average price, 
	-- calculate the standard deviation in prices, and sum reviews across all listings.

select 
    count(listing_id) listing_count,
    min(price) min_price,
    max(price) max_price,
    avg(price) avg_price,
    std(price) price_std,
    sum(reviews_per_month) total_reviews
from listing
where neighbourhood = 'Williamsburg';

----------------
-- GROUP BY

-- To define a group, add a column name after the group by. 
-- Then add the column to the select column expressions. 
-- An aggregate is calculated for each distinct value in the column.

-- EX., to calculate the average price per neighborhood include neighbourhood in the select column expressions along with avg(price). 
	-- Then add neighbourhood to the group by clause. 
	-- Each row returned includes a unique neighborhood name and the average price of a listing in that neighborhood.

select
    neighbourhood,
    avg(price) avg_price
from listing
group by neighbourhood;

-- Below, we create a group that includes neighborhood and room type. 
-- This gives a more accurate average if we're trying to decide between a room or an entire house/apartment.

select
    neighbourhood,
    room_type,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
order by neighbourhood, room_type;

-- Say that we want to find the least expensive average price. We sort by the average price ascending. 
-- In addition, we include the minimum price, maximum price, and the number of listings per group. 
-- This gives us a better sense of the range of prices. If there are many listing per group, the average is more accurate.

select
    neighbourhood,
    room_type,
    min(price) min_price,
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
order by avg(price) asc;


------------
-- HAVING

-- The having clause is a post-aggregation filter. It runs after aggregation occurs, so we can omit results based on aggregated values.

-- If our room budget is between $45 and $65, we might add a having clause to omit average prices that are out of our range.

select
    neighbourhood,
    room_type,
    min(price) min_price,
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
having avg(price) between 45.00 and 65.00
order by avg(price) asc;

-- The having clause works the same as a where clause. We can combine conditions with and and or. 
	-- We can use any comparison operator or SQL function. 
-- The only difference is that having allows aggregate values and where does not.

-- Order of Operations

-- A query does not run top-to-bottom. Instead, the SQL engine resolves operations in dependency order. 
	-- When all clauses are included in a query, the order of operations is as follows.
    
select
    neighbourhood_group,
    neighbourhood,
    room_type,
    min(price) min_price,                              -- 3
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing                                           -- 1
where availability_365 > 0                             -- 2
    and price > 0
group by neighbourhood_group, neighbourhood, room_type -- 3
having avg(price) between 45.00 and 65.00              -- 4
    and count(listing_id) > 1
order by avg(price) asc                                -- 5
limit 5;                                               -- 6

---------------------------
-- AGGREGATE JOINED TABLES

-- To find the customer with the most orders, join the customer table to `order`, 
	-- filter out cancelled orders, count the orders, and sort by count descending. 
    
use bowls;

select
    c.customer_id,
    concat(c.first_name, ' ', c.last_name) customer_name,
    count(o.order_id) order_count
from customer c
inner join `order` o on c.customer_id = o.customer_id
where o.order_status_id != 9
group by c.customer_id
order by count(o.order_id) desc;

-- There is a six-way tie for orders. Six customers ordered four times.

-- Next, join the customer, `order`, order_item, and item tables. 
-- Group by customer_id and order_id, then sum the cost of each item on the order. Sort by customer and order_date.

select
    concat(c.first_name, ' ', c.last_name) customer_name,
    o.order_id,
    o.order_date,
    sum(oi.quantity * i.price) order_total
from customer c
inner join `order` o on c.customer_id = o.customer_id
inner join order_item oi on o.order_id = oi.order_id
inner join item i on oi.item_id = i.item_id
where c.customer_id in ( -- customer_ids from previous result
    '8ad51114-50ab-4a36-9ee9-fc3f01ec74cc',
    '80bd6ffa-ae27-4d06-8017-16b835e2d3b1',
    '6a1d0276-8eba-4e55-ad30-453091e835fb',
    '1514f6a9-b426-4bb7-bd98-4ab6e96608a3',
    'e6b763b2-2e30-409a-91c8-720d0a68bb29',
    'b25f562a-e547-4b59-a3ef-b7c18f249b92'
)
and o.order_status_id != 9
group by c.customer_id, o.order_id
order by customer_name, o.order_date; -- can sort with an alias


