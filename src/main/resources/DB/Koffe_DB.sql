-- ================================== CREATE DATABASE ==================================
create database koffe_shop;
use koffe_shop;

-- ================================== CREATE TABLE ==================================

-- _______________ User _______________
create table `user`(
UserId int primary key auto_increment,
UserFullName varchar(50),
UserPhoneNumber varchar(10),
UserPassword varchar(50)
);
select * from `orderitems`;
-- _______________ Role _______________
create table `role`(
RoleId int primary key auto_increment,
RoleDetail varchar(10)
);

-- _______________ User Info _______________
create table `userinfo`(
UserInfoId int primary key auto_increment,
UserId int,
RoleId int default 4,
Gender tinyint default 0,
Avatar text,
`Status` bit not null default 1,
foreign key (UserId) references `user`(UserId),
foreign key (RoleId) references `role`(RoleId)
);



-- _______________ Products _______________
create table products(
ProductId int primary key auto_increment,
ProductName varchar(50),
ProductDescription varchar(300),
ProductAvatar text
);

-- _______________ Size _______________
create table size(
SizeId int primary key auto_increment,
SizeDetail char(1)
);

-- _______________ Price _______________
create table price (
PriceId int primary key auto_increment,
ProductId int not null,
SizeId int not null,
Price int not null,
foreign key (ProductId) references products(ProductId),
foreign key (SizeId) references size(SizeId)
);


-- _______________ Address _______________
create table address (
AddressId int primary key auto_increment,
Location varchar (255),
UserId int not null,
foreign key (UserId) references `user`(UserId)
);

-- _______________ Cart _______________
create table cart(
CartId int primary key auto_increment,
UserId int not null,
ProductId int not null,
SizeId int not null,
Price int,
Quantity int,
Total int,
`Date` timestamp default now(),
foreign key (ProductId) references products(ProductId),
foreign key (UserId) references `user`(UserId),
foreign key (SizeId) references size(SizeId)
);

-- _______________ Order _______________
create table `order` (
OrderId int primary key auto_increment,
UserId int not null,
AddressId int not null,
Total int,
`Status` tinyint default 0,
`Date` timestamp default now(),
foreign key (UserId) references `user`(UserId),
foreign key (AddressId) references address(AddressId)
);


-- _______________ Order Items_______________
create table `orderitems`(
OrderItemsId int primary key auto_increment,
OrderId int not null,
ProductId int not null,
SizeId int not null,
Price int not null,
Quantity int not null,
foreign key (OrderId) references `order`(OrderId),
foreign key (ProductId) references products(ProductId),
foreign key (SizeId) references size(Sizeid)
);

-- _______________ Book Table_______________
create table bookTable (
BookTableId int primary key auto_increment,
ArrivedTime timestamp,
AmountPeople tinyint,
PhoneNumber varchar(10),
`Name` varchar (50),
`Status` tinyint default 0
);

-- _______________ Comments _______________
create table comments (
CommentsId int primary key auto_increment,
CommentsFullName varchar(50),
CommentsEmail varchar(100),
CommentsSubject varchar(150),
CommentsMessage varchar(400),
CommentsTime timestamp
);

-- ================================== INSERT DATA ==================================

-- _______________ Size _______________
insert into size(SizeDetail) values ("S"), ("M"), ("L");

-- _______________ Products _______________
insert into products(ProductName, ProductDescription, ProductAvatar) values 
("Traditional Black", "Even bad coffee is better than no coffee", "BlackCoffee.jpg"),
("Traditional With Milk", "Coffee makes us strong, serious and wise", "MilkCoffee.jpg"),
("Espresso", "I measure my life in coffee spoons", "Espresso.jpg"),
("Latte", "As long as there's coffee in the world, how can it be so bad", "Latte.jpg"),
("Cappuccino", "Every morning when I wake up without coffee, I find myself tasteless", "Cappuccino.jpg"),
("Macchiato", "The morning cup of coffee brings the excitement and strength that afternoon or evening tea cannot bring", "Macchiato.jpg");

-- _______________ Price _______________
insert into price (ProductId, SizeId, Price) values
(1, 1, 10), (1, 2, 11), (1, 3, 12), 
(2, 1, 10), (2, 2, 11), (2, 3, 12),
(3, 1, 10), (3, 2, 11), (3, 3, 12),
(4, 1, 10), (4, 2, 11), (4, 3, 12),
(5, 1, 10), (5, 2, 11), (5, 3, 12),
(6, 1, 10), (6, 2, 11), (6, 3, 12);





-- _______________ Role _______________
insert into `role`(RoleDetail) values
("ADMIN"),
("MANAGER"),
("STAFF"),
("CUSTOMER");

-- ================================== TRIGGER ==================================
create trigger afterInsertUser
after insert on `user`
for each row
insert `userinfo` (UserId) values (new.`UserId`);


-- ================================== PROCEDURE ==================================

-- _______________ User _______________

-- ___@findAll
DELIMITER //
create procedure findAllUser()
begin
select `user`.UserId, UserFullName, UserPhoneNumber, UserPassword, Gender, Avatar, `role`.RoleId, `Status` from `user` 
join `userinfo`on `user`.UserId = `userinfo`.UserId 
join `role` on `userinfo`.RoleId = `role`.RoleId;
end //
DELIMITER ;

-- ___ @findUserById
DELIMITER //
create procedure findUserById(in fId int)
begin
select `user`.UserId, UserFullName, UserPhoneNumber, UserPassword, Gender, Avatar, `role`.RoleId, `Status` from `user` 
join `userinfo`on `user`.UserId = `userinfo`.UserId 
join `role` on `userinfo`.RoleId = `role`.RoleId having `user`.UserId = fId;
end //
DELIMITER ;

-- ___ @findUserByName
DELIMITER //
create procedure findUserByName(in fName varchar(50))
begin
select `user`.UserId, UserFullName, UserPhoneNumber, UserPassword, Gender, Avatar, `role`.RoleId, `Status` from `user` 
join `userinfo`on `user`.UserId = `userinfo`.UserId 
join `role` on `userinfo`.RoleId = `role`.RoleId having UserFullName like concat("%", fName, "%") ;
end //
DELIMITER ;

-- ___ @saveUser
DELIMITER //
create procedure saveUser(in fullName varchar(50), in phoneNumber varchar(10), `password` varchar(50))
begin
insert into `user`(UserFullName, UserPhoneNumber, UserPassword) values (fullName, phoneNumber, `password`);
end //
DELIMITER ;


-- ___ @updateUser
DELIMITER //
create procedure updateUser(
in uUserId int,
in uFullName varchar(50), 
in uPhoneNumber varchar(10), 
in uPassword varchar(50),
in uGender tinyint,
in uAvatar text,
in uStatus bit,
in uRoleId int
)
begin
update `user` set UserFullName = uFullName, UserPhoneNumber = uPhoneNumber, UserPassword = uPassword where UserId = uUserId;
update `userinfo` set Gender = uGender, Avatar = uAvatar, `Status` = uStatus, RoleId = uRoleId where UserId = uUserId;
end //

select * from userinfo;
-- ___ @findUserByPhoneNumber
DELIMITER //
create procedure findUserByPhoneNumber(in phoneNumber varchar(10))
begin
select * from `user` where UserPhoneNumber = phoneNumber;
end //
DELIMITER ;

-- ___ @findUserByPhoneNumberAndPassword
DELIMITER //
create procedure findUserByPhoneNumberAndPassword(in phoneNumber varchar(10), in `password` varchar(50))
begin
select `user`.UserId, UserFullName, UserPhoneNumber, UserPassword, Gender, Avatar, `role`.RoleId, `Status` from `user` 
join `userinfo`on `user`.UserId = `userinfo`.UserId 
join `role` on `userinfo`.RoleId = `role`.RoleId and UserPhoneNumber like phoneNumber and UserPassword like `password`;
end //
DELIMITER ;

-- getCountOfUser
DELIMITER //
create procedure getCountOfUser()
begin
select count(UserId) from `user`;
end //
DELIMITER ;


-- _______________ Booking Table _______________

-- ___ @findAllBookingTable
DELIMITER //
create procedure findAllBookingTable()
begin
select * from booktable;
end //
DELIMITER ;

-- ___ @findBookingTableById
DELIMITER //
create procedure findBookingTableById(in FBookTableId int)
begin
select * from booktable where BookTableId = FBookTableId;
end //
DELIMITER ;


-- ___ @findAcceptedBookingTableByUserName
DELIMITER //
create procedure findAcceptedBookingTableByUserName(in fName varchar(50))
begin
select * from booktable where `Name` like concat("%", fName,  "%") and `Status` = 1;
end //
DELIMITER ;

-- ___ @saveBookingTable
DELIMITER //
create procedure saveBookingTable(in SArrivedTime timestamp, in SAmountPeople tinyint, in PhoneNumber varchar(10), in SName varchar(50))
begin
insert into booktable(ArrivedTime, AmountPeople, PhoneNumber, `Name`) values 
(SArrivedTime, SAmountPeople, PhoneNumber, SName);
end //
DELIMITER ;

-- ___ @updateStatusBookingTable
DELIMITER //
create procedure updateStatusBookingTable(in UBookTableId int)
begin
update booktable set `Status` = 1 where BookTableId = UBookTableId;
end //
DELIMITER ;

-- ___ @deleteBookingTable
DELIMITER //
create procedure deleteBookingTable(in DBookTableId int)
begin
delete from booktable where BookTableId = DBookTableId;
end //
DELIMITER ;

-- getCountOfBookTable
DELIMITER //
create procedure getCountOfBookTable()
begin
select count(BookTableId) from booktable;
end //
DELIMITER ;

-- _______________ Comments _______________

-- ___ @findAllComments
DELIMITER //
create procedure findAllComments()
begin
select * from comments order by CommentsTime desc;
end //
DELIMITER ;

-- ___ @findAllCommentByCommentsFullName
DELIMITER //
create procedure findAllCommentByCommentsFullName(in fFullName varchar(50))
begin
select * from comments where CommentsFullName like concat("%", fFullName, "%");
end //
DELIMITER ;

-- ___ @saveComments
DELIMITER //
create procedure saveComments(
in ICommentsFullName varchar(50), 
in ICommentsEmail varchar(100), 
in ICommentsSubject varchar(150), 
in ICommentsMessage varchar(400),
in ICommentsTime timestamp)
begin
insert into comments(CommentsFullName, CommentsEmail, CommentsSubject, CommentsMessage, CommentsTime) values 
(ICommentsFullName, ICommentsEmail, ICommentsSubject, ICommentsMessage, ICommentsTime);
end //
DELIMITER ;

-- getTotalCommentInYear
DELIMITER //
create procedure getTotalCommentInYear()
begin
select month(CommentsTime) as `Month`, count(CommentsId) as TotalComment from `comments` 
where year(CommentsTime) = year(now()) group by month(CommentsTime);
end //
DELIMITER ;

-- _______________ Products _______________

-- ___ @findAllProducts
DELIMITER //
create procedure findAllProducts()
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId order by ProductId desc, SizeId asc;
end //
DELIMITER ;

-- ___ @findAllProductsBySize
DELIMITER //
create procedure findAllProductsBySize(in fSizeId char(1))
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and size.SizeId = fSizeId;
end //
DELIMITER ;

-- ___ @findListProductById
DELIMITER //
create procedure findListProductById(in fProductId int)
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and products.ProductId = fProductId;
end //
DELIMITER ;


-- ___ @findProductByProductIdAndSizeId
DELIMITER //
create procedure findProductByProductIdAndSizeId(in fProductId int, in fSizeId int)
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and products.ProductId = fProductId and size.SizeId = fSizeId;
end //
DELIMITER ;

-- ___ @findProductByName
DELIMITER //
create procedure findProductByName(in fName varchar(50))
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and ProductName like concat("%", fName, "%") order by ProductId desc, SizeId asc;
end //
DELIMITER ;

-- ___ @findProductById
DELIMITER //
create procedure findProductById(in fProductId int)
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and products.ProductId = fProductId;
end //
DELIMITER ;

-- ___ @findAllActiveProduct
DELIMITER //
create procedure findAllActiveProduct()
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and `Status` = 1;
end //
DELIMITER ;

-- ___ @findAllNotActiveProduct
DELIMITER //
create procedure findAllNotActiveProduct()
begin
select products.ProductId, ProductName, ProductDescription, size.SizeId, Price, ProductAvatar, `Status` from products 
join price on products.ProductId = price.ProductId 
join size on price.SizeId = size.SizeId and `Status` = 0;
end //
DELIMITER ;

-- ___ @saveProducts
DELIMITER //
create procedure saveProducts(
in IProductName varchar(50), 
in IProductDescription varchar(300),
in IProductAvatar text,
in IPriceS int,
in IPriceM int,
in IPriceL int)
begin
declare ProductIdTemp int;
insert into products(ProductName, ProductDescription, ProductAvatar) values (IProductName, IProductDescription, IProductAvatar);
select ProductId into ProductIdTemp from products where ProductName = IProductName;
insert into price (ProductId, SizeId, Price) values (ProductIdTemp, 1, IPriceS);
insert into price (ProductId, SizeId, Price) values (ProductIdTemp, 2, IPriceM);
insert into price (ProductId, SizeId, Price) values (ProductIdTemp, 3, IPriceL);
end //
DELIMITER ;

-- ___ @updateProducts
DELIMITER //
create procedure updateProducts(
in UProductId int,
in UProductName varchar(50), 
in UProductDescription varchar(300),
in UIProductAvatar text,
in UPriceS int,
in UPriceM int,
in UPriceL int)
begin
update products set ProductName = UProductName, ProductDescription = UProductDescription, ProductAvatar = UIProductAvatar where ProductId = UProductId;
update price set Price = UPriceS where ProductId = UProductId and SizeId = 1;
update price set Price = UPriceM where ProductId = UProductId and SizeId = 2;
update price set Price = UPriceL where ProductId = UProductId and SizeId = 3;
end //
DELIMITER ;


-- ___ @deleteProductsById
DELIMITER //
create procedure deleteProductsById(in delProductId int)
begin
delete from price where ProductId = delProductId;
delete from products where ProductId = delProductId;
end //
DELIMITER ;product

-- ___ @changeStatusProductById
DELIMITER //
create procedure changeStatusProductById(in UProductId int, in UStatus tinyint)
begin
update products set `Status` = UStatus where ProductId = UProductId;
end //
DELIMITER ;

-- _______________ Cart _______________

-- ___ @findAllCart
DELIMITER //
create procedure findAllCart()
begin
select * from cart;
end //
DELIMITER ;

-- ___ @findCartByUserId
DELIMITER //
create procedure findCartByUserId(in FUserId int)
begin
select * from cart where UserId = FUserId;
end //
DELIMITER ;


-- ___ @findCartById
DELIMITER //
create procedure findCartById(in FCartId int)
begin
select * from cart where CartId = FCartId;
end //
DELIMITER ;

-- ___ @findCartByUserIdAndProductIdAndSizeId
DELIMITER //
create procedure findCartByUserIdAndProductIdAndSizeId(in FUserId int, in FProductId int, in FSizeId int)
begin
select * from cart where UserId = FUserId and ProductId = FProductId and SizeId = FSizeId;
end //
DELIMITER ;

-- ___ @findAllCartDetail
DELIMITER //
create procedure findAllCartDetail()
begin
select CartId, cart.UserId, cart.ProductId, cart.SizeId, ProductName, size.SizeDetail, Price, Quantity, Total, products.ProductAvatar, `Date` from cart 
join products on cart.ProductId = products.ProductId 
join size on cart.SizeId = size.SizeId;
end //
DELIMITER ;


-- ___ @findAllCartDetailByUserId
DELIMITER //
create procedure findAllCartDetailByUserId(in FUserId int)
begin
select CartId, cart.UserId, cart.ProductId, cart.SizeId, ProductName, size.SizeDetail, Price, Quantity, Total, products.ProductAvatar, `Date` from cart 
join products on cart.ProductId = products.ProductId 
join size on cart.SizeId = size.SizeId and cart.UserId = FUserId;
end //
DELIMITER ;

-- ___ @getTotalPriceOfUserId
DELIMITER //
create procedure getTotalPriceOfUserId(in FUserId int)
begin
select sum(Total) as Total from cart 
join products on cart.ProductId = products.ProductId 
join size on cart.SizeId = size.SizeId and cart.UserId = FUserId group by cart.UserId;
end //
DELIMITER ;



-- ___ @saveCart
DELIMITER //
create procedure saveCart(in IUserId int, in IProductId int, in ISizeId int, in IQuantity int)
begin
declare tempPrice int;
declare tempTotal int;
select Price into tempPrice from price where ProductId = IProductId and SizeId = ISizeId;
insert into cart(UserId, ProductId, SizeId, Price, Quantity, Total) values (IUserId, IProductId, ISizeId, tempPrice, IQuantity, tempPrice * IQuantity);
end //
DELIMITER ;
select * from cart;

-- ___ @updateCart
DELIMITER //
create procedure updateCart(in UCartId int, in UQuantity int)
begin
update cart set total = Price * UQuantity, Quantity = UQuantity where CartId = UCartId;
end //
DELIMITER ;

-- ___ @updatePlusQuantityToOneInCart
DELIMITER //
create procedure updatePlusQuantityToCart(in UUserId int, in UProductId int, in USizeId int, in UQuantity int)
begin
update cart set Total = (Quantity + UQuantity) * Price, Quantity = Quantity + UQuantity where UserId = UUserId and ProductId = UProductId and SizeId = USizeId;
end //
DELIMITER ;


-- ___ @updateCartPlusQuantiyByOne
DELIMITER //
create procedure updateCartPlusQuantityByOne(in UCartId int)
begin
update cart set Total = (Quantity + 1) * Price, Quantity = Quantity + 1 where CartId = UCartId;
end //
DELIMITER ;

-- ___ @updateMinusCartPlusQuantityByOne
DELIMITER //
create procedure updateMinusCartPlusQuantityByOne(in UCartId int)
begin
update cart set Total = (Quantity - 1) * Price, Quantity = Quantity - 1 where CartId = UCartId;
end //
DELIMITER ;


-- ___ @deleteCartById
DELIMITER //
create procedure deleteCartById(in DCartId int)
begin
delete from cart where CartId = DCartId;
end //
DELIMITER ;

-- ___ @deleteCartByUserId
DELIMITER //
create procedure deleteCartByUserId(in DUserId int)
begin
delete from cart where UserId = DUserId;
end //
DELIMITER ;


-- _______________ Address _______________

-- ___ @findAllAddress
DELIMITER //
create procedure findAllAddress()
begin
select * from address;
end //
DELIMITER ;

-- ___ @findAllAddressByUserId
DELIMITER //
create procedure findAddressByUserId(in FUserId int)
begin
select * from address where UserId = FUserId;
end //
DELIMITER ;

-- ___ @findAllAddressById
DELIMITER //
create procedure findAddressById(in FAddressId int)
begin
select * from address where AddressId = FAddressId;
end //
DELIMITER ;

-- ___ @saveAddress
DELIMITER //
create procedure saveAddress(in ILocation varchar(255), in IUserId int)
begin
insert into address(Location, UserId) values (ILocation, IUserId);
end //
DELIMITER ;

-- ___ @getLastAddressId
DELIMITER //
create procedure getLastAddressId()
begin
select AddressId from address ORDER BY AddressId DESC LIMIT 1;
end //
DELIMITER ;

-- ___ @updateAddressByAddressId
DELIMITER //
create procedure updateAddressByAddressId(in UAddressId int, in ULocation varchar(255))
begin
update address set Location = ULocation where AddressId = UAddressId;
end //
DELIMITER ;

-- ___ @deleteAddressById
DELIMITER //
create procedure deleteAddress(in DAddressId int)
begin
delete from address where AddressId = DAddressId;
end //
DELIMITER ;

-- _______________ Order _______________

-- ___ @findAllOrder
DELIMITER //
create procedure findAllOrder()
begin
select * from `order`;
end //
DELIMITER ;

-- ___ @findAllOrderByUserId
DELIMITER //
create procedure findAllOrderByUserId(in FUserId int)
begin
select * from `order` where UserId = FUserId;
end //
DELIMITER ;

-- ___ @findAcceptedOrderById
DELIMITER //
create procedure findAcceptedOrderById(in FOrderId int)
begin
select * from  `order` where OrderId = FOrderId and `Status` = 1;
end //
DELIMITER ;

-- ___ @findOrderById
DELIMITER //
create procedure findOrderById(in FOrderId int)
begin
select * from  `order` where OrderId = FOrderId;
end //
DELIMITER ;

-- ___ @updateStatusOrderByOrderId
DELIMITER //
create procedure updateStatusOrderByOrderId(in UOrderId int)
begin 
update `order` set `Status` = 1 where OrderId = UOrderId;
end //
DELIMITER ;

-- ___ @getLastOrderId
DELIMITER //
create procedure getLastOrderId()
begin
select OrderId from `order` ORDER BY OrderId DESC LIMIT 1;
end //
DELIMITER ;

-- ___ @saveOrder
DELIMITER //
create procedure saveOrder(
in IUserId int, 
in IAddressId int, 
in ITotal int)
begin
insert into `order`(UserId, AddressId, Total) values (IUserId, IAddressId, ITotal);
end //
DELIMITER ;

-- ___ @findAllOrderItemsByOrderId
DELIMITER //
create procedure findAllOrderItemsByOrderId(in FOrderId int)
begin 
select * from orderitems where OrderId = FOrderId;
end //
DELIMITER ;

-- ___ @findOrderItemsDetailByOrderId
DELIMITER //
create procedure findOrderItemsDetailByOrderId(in FOrderId int)
begin 
select OrderItemsId, ProductName, SizeDetail, Quantity, (Quantity * Price) as Total, ProductAvatar from orderitems 
join products on orderitems.ProductId = products.ProductId
join size on orderitems.SizeId = size.SizeId and OrderId = FOrderId;
end //
DELIMITER ;

-- ___ @saveOrderItems
DELIMITER //
create procedure saveOrderItems(
in IOrderId int, 
in IProductId int, 
in ISizeId int,
in IPrice int,
in IQuantity int)
begin
insert into `orderitems` (OrderId, ProductId, SizeId, Price, Quantity) values (IOrderId, IProductId, ISizeId, IPrice, IQuantity);
end //
DELIMITER ;

-- getCountOfOrder
DELIMITER //
create procedure getCountOfOrder()
begin
select count(OrderId) from `order`;
end //
DELIMITER ;

-- getSumTotalOrder
DELIMITER //
create procedure getSumTotalOrder()
begin
select sum(Total) from `order`;
end //
DELIMITER ;

-- getOrderAmountProduct
DELIMITER //
create procedure getOrderAmountProduct()
begin
select products.ProductId, ProductName, sum(Quantity) as OrderAmount from orderitems 
right join products on orderitems.ProductId = products.ProductId group by products.ProductId;
end //
DELIMITER ;

-- getTotalEachMonth
DELIMITER //
create procedure getTotalEachMonthInYear()
begin
select month(`Date`) as `Month`, sum(Total) as Total from `order` where year(`Date`) = year(now()) group by month(`Date`);
end //
DELIMITER ;

