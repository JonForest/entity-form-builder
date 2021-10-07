create table component (
    key text not null primary key,
    description text,
    major_version integer default 0,
    minor_version integer default 0,
    patch_version integer default 0,
    code text not null
)