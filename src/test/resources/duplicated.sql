select COLUMN_NAME
from information_schema.COLUMNS
where TABLE_SCHEMA = ?
group by COLUMN_NAME
having count(*) > 1