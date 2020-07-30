desc user;
-- select
select * from user;
-- 회원가입
insert into user values(null, '엄지윤', 'yunn1209@gmail.com', password('1234'), 'female', now());

-- login
select no, name from user where email = 'yunn1209@gmial.com' and password = password('1234');
