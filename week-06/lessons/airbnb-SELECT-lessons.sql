select * from sys.sys_config;

-- SQL Select

-- Single-line comments start with two dashes.

/* Multi-line
comment.
*/

use airbnb_nyc;                -- Statements end with a semicolon. sets the database context for the rest of the script.
							   -- Without it we may run a statement against an unintended database

-- Always set the context.
-- To simplify code samples, we will omit the `use` statement
-- moving forward and assume it is present.
use airbnb_nyc;

select        -- the `select` keyword
    name,     -- one or more column expressions, separated by commas
    neighbourhood,
    host_name,
    availability_365
from listing -- `from` keyword, then table name, then semicolon               
where host_name = 'Andrea' -- 'where' keyword, then a boolean expression 
	-- and availability_365 = 0
	or availability_365 = 0; -- `and`: both conditions must be true

-- select * from listing; -- `*` is shorthand for "all columns".
                       -- Any SQL statement can be formatted in one line.
                       -- Here, it makes the statement much more readable.
                       
                       
use airbnb_nyc;

-- varchar, char, text
select * from listing where neighbourhood = 'Astoria';
select * from listing where neighbourhood = 'astoria'; -- not case sensitive
select * from listing where neighbourhood < 'M';
select * from listing where neighbourhood <= 'M';
select * from listing where neighbourhood > 'M';
select * from listing where neighbourhood >= 'M';

-- numbers (int, decimal...)
select  * from listing where price = 100.0;
select  * from listing where price < 100.0;
select  * from listing where price <= 100.0;
select  * from listing where price > 100.0;
select  * from listing where price >= 100.0;

-- dates and times
select * from listing where last_review = '2019-02-15'; -- string literal is converted to a date for comparison
select * from listing where last_review < '2019-02-15';
select * from listing where last_review <= '2019-02-15';
select * from listing where last_review > '2019-02-15';
select * from listing where last_review >= '2019-02-15';     

select *
from listing
where ((neighbourhood_group = 'Manhattan' and price < 100)
    or neighbourhood = 'City Island')
and availability_365 > 0;
               
               
-- DOES NOT WORK.
select * from listing
where reviews_per_month <= 0;

-- DOES NOT WORK.
select * from listing
where reviews_per_month = 0;

-- DOES NOT WORK.
select * from listing
where reviews_per_month = '';

-- DOES NOT WORK.
-- Even an explicit check for the null value doesn't work.
select * from listing
where reviews_per_month = null;

-- Find listings in Manhattan with a null reviews_per_month value.

select *
from listing
where neighbourhood_group = 'Manhattan'
and reviews_per_month is null;

-- To exclude null reviews_per_month, use is not null.

select *
from listing
where neighbourhood_group = 'Manhattan'
and reviews_per_month is not null;

-- To find listings with no reviews (0 or null reviews_per_month) in Williamsburg, check both the 0 and null condition.

select *
from listing
where neighbourhood = 'Williamsburg'
and (reviews_per_month is null
    or reviews_per_month = 0);
    
-- LIKE recognizes two wildcard characters.
-- % - matches zero or more characters of any value.
-- _ - matches exactly one character of any value.    

-- To find listings that claim they're clean, apply the like operator to a column. Start its pattern with a leading %, then the literal value clean, then a trailing %

select
    name,
    host_name
from listing
where name like '%clean%';

-- To ensure "clean" is the final word, remove the trailing wildcard character.

select
    name,
    host_name
from listing
where name like '%clean';
                       
                       
-- To find a host whose name is four characters long with 'o' as the second character, use the pattern '_o__'  
    
select
    name,
    host_name
from listing
where host_name like '_o__';

-- The `in` operator accepts a comma-delimited list of values. 
-- If the named column matches one of the values, the expression evaluates to true and the row is included.

select *
from listing
where neighbourhood in ('Bayside', 'Eltingville', 'Jackson Heights', 'Van Nest');

-- To exclude values, the `not` operator reverses the in and excludes its values. 
-- Below, we exclude listings from Manhattan, the Bronx, and Brooklyn.

select *
from listing
where neighbourhood_group not in ('Manhattan', 'Bronx', 'Brooklyn');

-- The in operator works for all data types. We can filter down to 5, 6, 7, or 8 reviews_per_month.

select *
from listing
where reviews_per_month in (5, 6, 7, 8);

-- The between operator is probably a better choice here. It more clearly communicates the intent and protects us from scenarios where we might forget a value in a range.

select *
from listing
where reviews_per_month between 5 and 8;

-- BETWEEN is especially nice for data ranges. Below, we find listings last reviewed fall/winter in 2018-2019.

select *
from listing
where last_review between '2018-10-01' and '2019-02-01';

------------------------------------------------------

-- SQL Sorting and Calculations

-- To sort by a column, name the column after the order by keyword. 

select
    name,
    host_name
from listing
order by host_name;

-- To reverse the order, modify order by with the desc keyword.

select
    name,
    host_name
from listing
order by host_name desc;

-- An ascending sort is specified by the asc modifier.
-- These queries produce the same result.
select
    name,
    host_name
from listing
order by host_name asc; -- asc is default, but here it's explicit

select
    name,
    host_name
from listing
order by host_name;     -- here it's not clear if the author forgot

-- We may sort by one or more columns. 
-- If more than one column is included, the first column is fully sorted, then the second is sorted within the first's order.

select
    name,
    neighbourhood_group,
    neighbourhood,
    host_name
from listing
where price > 200
order by neighbourhood_group asc, neighbourhood asc;


-- Below, we sort by neighbourhood_group, then neighbourhood, and finally, put the most expensive listing in each neighborhood first.
select
    name,
    neighbourhood_group,
    neighbourhood,
    price
from listing
order by neighbourhood_group asc, neighbourhood asc, price desc;

-- Below, we sort by the price but the price isn't included in the results.

select 
    name,
    host_name
from listing
where neighbourhood = 'Hell''s Kitchen'
order by price desc;

-- Given a collection of rows produced by a select statement, the limit clause further restricts the rows returned. 
-- It limits the results.
-- To view the 7 least expensive listings, sort by price ascending and limit 7.

select
    name,
    neighbourhood,
    price
from listing
order by price asc
limit 7;

-- To find the 7 most expensive listings, sort by price descending and then limit.

select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 7;

-- When the limit clause includes 2 numbers, the first represents an offset and the second is the rows to return. 
-- The offset is the number of rows to skip before including rows. 
-- The count is the number of rows to include.

-- To find the 11th - 18th most expensive listings, we offset (skip) 10 rows and then include 7.

select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 10, 7; -- 10 is offset, 7 is count

-- A limit clause that returns zero rows or "goes off the end" of the result set isn't an error. 
-- It returns column names but no rows.

select * from listing limit 0;        -- zero rows returned
select * from listing limit 5000, 10; -- no rows available after row 4849

-- Below we define columns that calculate a value instead of grabbing a value from a table. 
-- Calculations in column expressions are called computed columns.

select
    name,
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing;

-- Expressions are also valid in where and order by clauses.

select
    name,  
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;

-- Expressions aren't calculated a second or third time. They're calculated once and used where appropriate.
-- Just like columns, the expression need not appear in the select column expressions.

select
    name,
    host_name
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;


-- SQL Functions
-- To concatenate string columns and literals, use the CONCAT function.
select
    name,
    concat(neighbourhood_group, ": ", neighbourhood)
from listing;

-- Below, we grab the current date and time with the NOW function, 
-- extract its year with YEAR and then look for listings last reviewed in the year before.

select
    name,
    host_name,
    price,
    year(last_review)
from listing
where year(last_review) + 1 = year(now());

-- IFNULL checks if an expression is null. If it is, it replaces the value with the second argument. 
-- If it's not, it leaves the value alone. This allows us to normalize null to some standard non-null value.

select
    name
from listing
where ifnull(reviews_per_month, 0) = 0; -- null values are replaced with 0

-- NULLIF compares two values. If they're equal or the first argument is null, it returns null. 
-- This allows us to normalize a non-null value to null.

-- If reviews_per_month is 0, make it null.
-- Then exclude all null values.
select
    name,
    reviews_per_month
from listing
where nullif(reviews_per_month, 0) is not null;


-- Column Aliases
-- When we add calculations to column expressions, the calculation becomes the name of the column.

select
    name,  
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing;

-- we can create an alias for any column. We rename the column with a bit of SQL syntax. 
-- After a column definition, but before the comma, add the `as` keyword and then provide a new name for the column.

select
    name,  
    price * minimum_nights as 'min_price',
    number_of_reviews / 25.0 
        * calculated_host_listings_count as 'credibility'
from listing;

-- If you're feeling lazy, the as keyword and string delimiters are not strictly necessary.

select
    name,  
    price * minimum_nights min_price, -- as and quotes not required
    number_of_reviews / 25.0 
        * calculated_host_listings_count credibility
from listing;

-- Say that our initial host name was stored in a column named host. 
-- We decide that host isn't a great name because a host is a combination of values, not just a name. 
-- So we change the host column to host_name. With column aliases, we can still present the column name host to the 
	-- outside world while our internal schema changes. Our result set interface stays consistent.

select
    name 'description', -- while we're at it, name is more like a description
    host_name 'host',   -- host remains host even after schema changes
    price * minimum_nights 'min_price',
    number_of_reviews / 25.0 
        * calculated_host_listings_count 'credibility'
from listing;


























                 