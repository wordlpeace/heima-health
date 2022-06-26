create table health.checkGroupsID
(
    id        bigint        not null comment '主键'
        primary key,
    attention varchar(255)  null comment '注意事项',
    code      bigint        null comment '编码',
    name      varchar(255)  null comment '名称',
    remark    varchar(255)  null comment '说明',
    sex       int default 0 null comment '"0"是不限 "1"是男 "2"是女',
    helpCode  varchar(255)  null comment '助记码'
)
    comment '检查组';

create table health.setmeal
(
    id          bigint        not null comment '主键'
        primary key,
    age         varchar(255)  null comment '年龄',
    attention   varchar(255)  null comment '操作',
    checkGroups bigint        null comment '检查组',
    code        bigint        null comment '套餐编码',
    helpCode    bigint        null comment '助记码',
    price       bigint        null comment '价格',
    remark      varchar(255)  null comment '说明',
    sex         int default 0 null comment '性别 "0" 不限 "1"男 "2"女',
    name        varchar(255)  null comment '姓名',
    img         varchar(255)  null comment '图片'
)
    comment '套餐';


create table health.setmeal_checkGroup
(
    id            bigint        not null comment '主键'
        primary key,
    setmealid     bigint        null comment '套餐id',
    checkGroupsID bigint        null comment '检查组id',
    name          varchar(255)  null comment '名称',
    code          bigint        null comment '编码',
    remark        varchar(255)  null comment '说明',
    sex           int default 0 null comment '性别 默认0 男1 女2',
    heloCode      bigint        null comment '助记码',
    attention     varchar(255)  null comment '注意事项'
)
    comment '套餐检查组关系表';


