create table component (
    id integer primary key autoincrement,
    key text not null unique,
    description text,
    major_version integer default 0,
    minor_version integer default 0,
    patch_version integer default 0,
    code text not null
)