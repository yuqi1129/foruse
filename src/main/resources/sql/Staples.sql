CREATE TABLE Staples
(
    `Item Count` INTEGER NOT NULL,
    `Ship Priority` VARCHAR(14) NOT NULL,
    `Order Priority` VARCHAR(15) NOT NULL,
    `Order Status` VARCHAR(13) NOT NULL,
    `Order Quantity` DOUBLE NOT NULL,
    `Sales Total` DOUBLE  NOT NULL,
    `Discount` DOUBLE  NOT NULL,
    `Tax Rate` DOUBLE  NOT NULL,
    `Ship Mode` VARCHAR(25) NOT NULL,
    `Fill Time` DOUBLE  NOT NULL,
    `Gross Profit` DOUBLE  NOT NULL,
    `Price` DOUBLE NOT NULL,
    `Ship Handle Cost` DOUBLE NOT NULL,
    `Employee Name` VARCHAR(50) NOT NULL,
    `Employee Dept` VARCHAR(4) NOT NULL,
    `Manager Name` VARCHAR(255) NOT NULL,
    `Employee Yrs Exp` DOUBLE  NOT NULL,
    `Employee Salary` DOUBLE NOT NULL,
    `Customer Name` VARCHAR(50) NOT NULL,
    `Customer State` VARCHAR(50) NOT NULL,
    `Call Center Region` VARCHAR(25) NOT NULL,
    `Customer Balance` DOUBLE  NOT NULL,
    `Customer Segment` VARCHAR(25) NOT NULL,
    `Prod Type1` VARCHAR(50) NOT NULL,
    `Prod Type2` VARCHAR(50) NOT NULL,
    `Prod Type3` VARCHAR(50) NOT NULL,
    `Prod Type4` VARCHAR(50) NOT NULL,
    `Product Name` VARCHAR(100) NOT NULL,
    `Product Container` VARCHAR(25) NOT NULL,
    `Ship Promo` VARCHAR(25) NOT NULL,
    `Supplier Name` VARCHAR(25) NOT NULL,
    `Supplier Balance` DOUBLE  NOT NULL,
    `Supplier Region` VARCHAR(25) NOT NULL,
    `Supplier State` VARCHAR(50) NOT NULL,
    `Order ID` VARCHAR(10) NOT NULL,
    `Order Year` INTEGER NOT NULL,
    `Order Month` INTEGER NOT NULL,
    `Order Day` INTEGER NOT NULL,
    `Order Date` TIMESTAMP NOT NULL,
    `Order Quarter` VARCHAR(2) NOT NULL,
    `Product Base Margin` DOUBLE  NOT NULL,
    `Product ID` VARCHAR(5) NOT NULL,
    `Receive Time` DOUBLE  NOT NULL,
    `Received Date` TIMESTAMP NOT NULL,
    `Ship Date` TIMESTAMP NOT NULL,
    `Ship Charge` DOUBLE NOT NULL,
    `Total Cycle Time` DOUBLE  NOT NULL,
    `Product In Stock` VARCHAR(3) NOT NULL,
    `PID` INTEGER NOT NULL,
    `Market Segment` VARCHAR(25) NOT NULL
) distribute by hash(`Item Count`);
