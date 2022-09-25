
-----------------------------
-- CREATE/DROP DATABASE

create database field_agent;

-- Always `use` the intended database.
use field_agent;

-- create tables and relationships
create table agency (
    agency_id int primary key auto_increment,
    short_name varchar(25) not null,
    long_name varchar(250) not null
);

-- create table [table name] (
--     [column name 1] [data type] [(not )null]? [modifiers...]?,
--     [column name 2] [data type] [(not )null]? [modifiers...]?,
--     [column name 3] [data type] [(not )null]? [modifiers...]?,
--     [column name n] [data type] [(not )null]? [modifiers...]?
-- );

-- Column Definitions
-- [name] [data type] [(not )null]? [modifiers...]?

-- DROP TABLE

	-- To delete a table, use the drop table statement.
-- drop table agency;
-- drop table if exists agency;

-- INLINE CONSTRAINTS

-- Create a table for agency locations. One agency can have many locations. 
-- In a one-to-many relationship, include the primary key of the one as a foreign key in the many.
-- Here, we include agency_id as a column in location and create a foreign key with the constraint clause.

create table location (
    location_id int primary key auto_increment,
    `name` varchar(25) not null,
    address varchar(125) not null,
    city varchar(50) not null,
    country_code varchar(5) not null,
    postal_code varchar(15) not null,
    agency_id int not null,
    constraint fk_location_agency_id
        foreign key (agency_id)
        references agency(agency_id)
);


-- The constraint clause is an optional clause in a create table statement. We can update the generic create table definition to include:
	-- One or more column definitions.
	-- Zero or more constraint definitions.
    -- It starts with the keyword constraint, followed by a name. 
    -- The second line specifies the type of constraint: foreign key.
    -- On the third line, the references clause names the linked table and columns.
    
    
-- create table [table name] (
-- [column definition]+
-- [constraint definition]*
-- );

-- ALTER TABLE
-- Add a table for agents - agency -> one to many -> agent

create table agent (
    agent_id int primary key auto_increment,
    first_name varchar(25) not null,
    middle_name varchar(25) null,
    last_name varchar(25) not null,
    dob date null,
    identifier varchar(50) not null,
    activation_date date not null,
    is_active bit not null default 1,
    agency_id int not null,
    constraint fk_agent_agency_id
        foreign key (agency_id)
        references agency(agency_id),
    constraint uq_agent_identifier_agency_id
        unique (identifier, agency_id)
);


-- BRIDGE TABLE

--  A bridge table is a required ingredient for a many-to-many relationship. 
--  It includes a primary key from one table (agency.agency_id) and a primary key from another (agent.agent_id) 
	--  to create an optional link between the two.
 
-- some bridge tables only use foreign keys, but they aren't limited to keys 
    
create table agency_agent (
    agency_id int not null,
    agent_id int not null,
    identifier varchar(50) not null,
    activation_date date not null,
    is_active bit not null default 1,
    constraint pk_agency_agent
        primary key (agency_id, agent_id),
    constraint fk_agency_agent_agency_id
        foreign key (agency_id)
        references agency(agency_id),
    constraint fk_agency_agent_agent_id
        foreign key (agent_id)
        references agent(agent_id),
    constraint uq_agency_agent_identifier_agency_id
        unique (identifier, agency_id)
); 


-- Alter agent

-- The agency-specific columns and constraints are no longer needed in the agent table. 
-- Use the alter table statement to remove them.

-- alter table [table name]
--     drop [something],
--     add [something],
--     change [something],
--     modify [something];

alter table agent 
    drop index uq_agent_identifier_agency_id,
    drop foreign key fk_agent_agency_id,
    drop column agency_id,
    drop column is_active,
    drop column activation_date,
    drop column identifier;

-- Maybe we want to track an agent's physical characteristics. 
-- We've established agents aren't necessarily trustable, so one or two physical characteristics might help 
-- 	identify them despite who they claim to be.

alter table agent
    modify first_name varchar(50) not null,
    modify last_name varchar(50) not null,
    add column height_in_inches int not null;


-- MORE TABLES
-- Alias
	-- An alias should include columns for a:
		-- unique identifier
		-- name
		-- persona: the alias's backstory

create table alias (
    alias_id int primary key auto_increment,
    `name` varchar(125) not null,
    persona varchar(2048) null,
    agent_id int not null,
    constraint fk_alias_agent_id
        foreign key (agent_id)
        references agent(agent_id)
);

-- A mission is an agency-sponsored secret operation. The agency/mission relationship is one-to-many. One agency can sponsor many missions.

create table mission (
    mission_id int primary key auto_increment,
    code_name varchar(50) not null,
    notes text,
    start_date date not null,
    projected_end_date date not null,
    actual_end_date date null,
    operational_cost decimal(10,2) not null,
    agency_id int not null,
    constraint fk_mission_agency_id
        foreign key(agency_id)
        references agency(agency_id)
);


-- Zero or more agents can be assigned to a mission. The mission/agent relationship is many-to-many. 
-- The mission_agent table is a bridge table. We use a compound mission_id/agent_id primary key to prevent duplicate assignments.

create table mission_agent (
    mission_id int not null,
    agent_id int not null,
    constraint pk_mission_agent 
        primary key(mission_id, agent_id),
    constraint fk_mission_agent_mission_id
        foreign key (mission_id)
        references mission(mission_id),
    constraint fk_mission_agent_agent_id
        foreign key (agent_id)
        references agent(agent_id)
);

-- A security clearance record includes an id and a name.
create table security_clearance (
    security_clearance_id int primary key auto_increment,
    `name` varchar(50) not null
);

-- Security clearance is part of the agency/agent relationship. An agent is given a security clearance 
	-- designation in the context of agency secrets. 
-- We need to alter the agency_agent table to include a security clearance.

-- Newest MySQL
alter table agency_agent
    add column security_clearance_id int not null,
    add constraint fk_agency_agent_security_clearance_id
         foreign key (security_clearance_id)
         references security_clearance(security_clearance_id);

-- Older versions of MySQL can't use the `constraint` form.
-- Use the `foreign key` form instead:
-- alter table agency_agent
--     add column security_clearance_id int not null,
--     add foreign key fk_agency_agent_security_clearance_id (security_clearance_id)
--          references security_clearance(security_clearance_id);






