-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Group4_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Group4_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Group4_db` DEFAULT CHARACTER SET utf8 ;
USE `Group4_db` ;

-- -----------------------------------------------------
-- Table `Group4_db`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Customer` (
  `idCustomer` INT AUTO_INCREMENT NOT NULL COMMENT '會員ID',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `profic` BLOB NULL COMMENT '大頭貼',
  `nickName` VARCHAR(50) NULL COMMENT '暱稱',
  `account` VARCHAR(25) NOT NULL COMMENT '帳號',
  `password` VARCHAR(50) NOT NULL COMMENT '密碼',
  `email` VARCHAR(50) NOT NULL COMMENT 'Email',
  `phone` VARCHAR(50) NOT NULL COMMENT '行動電話',
  `createdTime` TIMESTAMP(6) NOT NULL COMMENT '創建時間',
  `suspended` TINYINT(1) NOT NULL COMMENT '停權與否(0 : 未被停權; 1 : 被停權)',
  `externalAcc` INT NOT NULL COMMENT '連接第三方帳號(0 : 無; 1 : Google; 2 : Facebook; 3：Apple)',
  `externalIdToken` VARCHAR(50) NULL COMMENT '第三方帳號idToken',
  `commentReportedNum` INT NULL COMMENT '留言被檢舉成功次數',
  `diaryReportedNum` INT NULL COMMENT '日誌被檢舉成功次數',
  PRIMARY KEY (`idCustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Address` (
  `idAddress` INT AUTO_INCREMENT NOT NULL COMMENT '送達地址ID',
  `idCustomer` INT NOT NULL COMMENT '會員ID',
  `address` VARCHAR(50) NULL COMMENT '地址',
  `tag` VARCHAR(25) NOT NULL COMMENT '地址標籤(家, 工作...etc)',
  `longitude` DECIMAL(5) NOT NULL COMMENT '經度',
  `latitude` DECIMAL(5) NOT NULL COMMENT '緯度',
  `createdTime` TIMESTAMP(6) NOT NULL COMMENT '創建時間',
  `defaultOption` TINYINT(1) NULL COMMENT '預設選項(0 : 非預設; 1 : 預設)',
  PRIMARY KEY (`idAddress`),
  constraint FK_Address_cust_id FOREIGN KEY(`idCustomer`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Card` (
  `idCard` INT AUTO_INCREMENT NOT NULL COMMENT '信用卡ID',
  `idCustomer` INT NOT NULL COMMENT '會員ID',
  `number` VARCHAR(25) NOT NULL COMMENT '信用卡卡號',
  `type` TINYINT(1) NOT NULL COMMENT '信用卡種類(0 : Visa; 1 : MasterCard)',
  `expiryDate` DATE NOT NULL COMMENT '信用卡有效日期',
  `createdTime` TIMESTAMP(6) NOT NULL COMMENT '創建時間',
  `defaultOption` TINYINT(1) NOT NULL COMMENT '預設選項(0 : 非預設; 1 : 預設)',
  PRIMARY KEY (`idCard`),
  constraint FK_Card_cust_id FOREIGN KEY(`idCustomer`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`FoodDiary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`FoodDiary` (
  `diaryID` INT AUTO_INCREMENT NOT NULL  COMMENT  '日誌編號' ,
  `custID` INT NOT NULL COMMENT '會員ID',
  `subject` VARCHAR(50) NOT NULL COMMENT '日誌標題',
  `text` TEXT NOT NULL COMMENT '日誌文章',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  `photo_video_1` BLOB NULL COMMENT '圖片/影片檔案',
  `photo_video_2` BLOB NULL COMMENT '圖片/影片檔案',
  `photo_video_3` BLOB NULL COMMENT '圖片/影片檔案',
  `status` TINYINT(1) NULL COMMENT '公開狀態(0: 不公開, 1: 公開)',
  `forumLikeNum` INT NULL COMMENT '按讚數',
  `diaryStatus` TINYINT(1) NULL COMMENT '日誌檢舉狀態(0: 未被檢舉, 1: 被檢舉)',
  `diaryType` INT NULL COMMENT '日誌分類',
  PRIMARY KEY (`diaryID`) ,
  constraint FK_diary_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`),
  constraint FK_diary_diaryType_id FOREIGN KEY(`diaryType`) REFERENCES diaryType(`diaryTypeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Comment` (
  `commentID` INT AUTO_INCREMENT NOT NULL COMMENT '留言ID',
  `diaryID` INT NOT NULL COMMENT '日誌ID',
  `custID` INT NOT NULL COMMENT '會員ID',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  `commentText` VARCHAR(200) NOT NULL COMMENT '留言內容',
  `commentStatus` TINYINT(1) NOT NULL COMMENT '留言檢舉狀態(0: 未被檢舉, 1: 被檢舉)',
  PRIMARY KEY (`commentID`),
  constraint FK_comment_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`),
  constraint FK_comment_diary_id FOREIGN KEY(`diaryID`) REFERENCES foodDiary(`diaryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`DiaryType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`DiaryType` (
  `diaryTypeID` INT AUTO_INCREMENT NOT NULL COMMENT '日誌分類ID',
  `diaryTypeName` VARCHAR(25) NOT NULL COMMENT '日誌類別名稱',
  PRIMARY KEY (`diaryTypeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`DiaryLike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`DiaryLike` (
  `diarylikeID` INT AUTO_INCREMENT NOT NULL COMMENT '按讚ID',
  `diaryID` INT NOT NULL COMMENT '日誌ID',
  `custID` INT NOT NULL COMMENT '會員ID',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  PRIMARY KEY (`diarylikeID`),
  constraint FK_like_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`),
  constraint FK_like_diary_id FOREIGN KEY(`diaryID`) REFERENCES foodDiary(`diaryID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`FriendChat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`FriendChat` (
  `friendChatID` INT AUTO_INCREMENT NOT NULL COMMENT '聊天室ID (friendChatID): int, not null',
  `custID` INT NOT NULL COMMENT '會員ID (custID): int, not null',
  `myFriendID` INT NOT NULL COMMENT '朋友會員ID (myFriendID): int, not null',
  `chatText` VARCHAR(200) NOT NULL COMMENT '訊息內容 (chatText): varchar(200), not null',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間 (creatTime): TimeStamp, not null',
  PRIMARY KEY (`friendChatID`),
  constraint FK_FriendChat_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`),
  constraint FK_FriendChat_myFriend_cust_id FOREIGN KEY(`myFriendID`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Friend` (
  `friendChatID` INT AUTO_INCREMENT NOT NULL COMMENT '朋友資料ID (friendChatID): int, not null',
  `custID` INT NOT NULL COMMENT '會員ID (custID): int, not null',
  `myFriendID` INT NOT NULL COMMENT '朋友會員ID (myFriendID): int, not null',
  `friendStatusNum` INT NOT NULL COMMENT '好友狀態 (friendStatusNum): int, not null',
  `statusUpdate` TIMESTAMP NOT NULL COMMENT '狀態最後異動日 (statusUpdate): TimeStamp, not null',
  PRIMARY KEY (`friendChatID`),
  constraint FK_Friend_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`),
  constraint FK_Friend_myFriend_cust_id FOREIGN KEY(`myFriendID`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`ChatRoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`ChatRoom` (
  `mesgID` INT AUTO_INCREMENT NOT NULL COMMENT '聊天訊息編號 (mesgID): int, not null ',
  `custID` INT NOT NULL COMMENT '會員編號 (custID): int, not null',
  `custMesg` TINYINT(1) NOT NULL COMMENT '會員訊息(custMesg): boolean, not null',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間 (createdTime): TimeStamp, not null',
  `message` VARCHAR(200) NOT NULL COMMENT '訊息內容 (message): varchar(200), not null',
  PRIMARY KEY (`mesgID`),
  constraint FK_chatRoom_cust_id FOREIGN KEY(`custId`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`OrderIngreList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`OrderIngreList` (
  `idOrder` INT NOT NULL COMMENT '訂單編號',
  `idIngre` INT NOT NULL COMMENT '食材編號',
  `orderQuan` INT NOT NULL COMMENT '購買數量',
  `price` INT NOT NULL COMMENT '單價',
  PRIMARY KEY (`idOrder`, `idIngre`),
  constraint FK_OrderIngreList_id FOREIGN KEY(`idIngre`) REFERENCES Ingre(`idIngre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`RecipeIngre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`RecipeIngre` (
  `idRecipeIngre` INT NOT NULL COMMENT '食譜食材編號',
  `idRecipe` INT NOT NULL COMMENT '食譜編號',
  `idIngre` INT NOT NULL COMMENT '食材編號',
  `ingreQuan` INT NOT NULL COMMENT '食材數量',
  PRIMARY KEY (`idRecipeIngre`),
  constraint FK_RecipeIngre_Recipe_id FOREIGN KEY(`idRecipe`) REFERENCES Recipe(`idRecipe`),
  constraint FK_RecipeIngre_Ingre_id FOREIGN KEY(`idIngre`) REFERENCES Ingre(`idIngre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Recipe` (
  `idRecipe` INT NOT NULL COMMENT '食譜編號\n',
  `recipeName` VARCHAR(45) NOT NULL COMMENT '食譜名稱\n',
  `descrip` VARCHAR(2000) NOT NULL COMMENT '步驟說明',
  `text` VARCHAR(400) NOT NULL COMMENT '內容',
  `photo` LONGBLOB NOT NULL COMMENT '圖片',
  PRIMARY KEY (`idRecipe`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`FinalOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`FinalOrder` (
  `idFinalOrder` INT AUTO_INCREMENT NOT NULL COMMENT '訂單ID',
  `idCustomer` INT NOT NULL COMMENT '會員ID',
  `recipient` VARCHAR(50) NOT NULL COMMENT '收件人姓名',
  `recipientAddress` VARCHAR(50) NOT NULL COMMENT '收件地址',
  `orderAmount` DECIMAL(10) NOT NULL COMMENT '交易金額',
  `createdTime` TIMESTAMP(6) NULL COMMENT '訂單成立時間',
  `shipTime` TIMESTAMP(6) NULL COMMENT '出貨時間',
  `arrivalTime` TIMESTAMP(6) NULL COMMENT '送達時間',
  `footnote` VARCHAR(200) NULL COMMENT '備註',
  PRIMARY KEY (`idFinalOrder`),
  constraint FK_FinalOrder_cust_id FOREIGN KEY(`idCustomer`) REFERENCES Customer(`idCustomer`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`DiaryReport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`DiaryReport` (
  `diaryReportID` INT AUTO_INCREMENT NOT NULL COMMENT '檢舉ID\n檢舉狀態碼:0:檢舉失敗、1:檢舉成功',
  `diaryID` INT NOT NULL COMMENT '日誌ID',
  `custID` INT NOT NULL COMMENT '會員ID',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  `reportReason` VARCHAR(200) NOT NULL COMMENT '檢舉理由',
  `reportResult` TINYINT(1) NOT NULL COMMENT '檢舉結果',
  PRIMARY KEY (`diaryReportID`),
  constraint FK_DiaryReport_cust_id FOREIGN KEY(`custID`) REFERENCES Customer(`idCustomer`),
  constraint FK_DiaryReport_diary_id FOREIGN KEY(`diaryID`) REFERENCES foodDiary(`diaryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`CommentReport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`CommentReport` (
  `commentReportID` INT AUTO_INCREMENT NOT NULL AUTO_INCREMENT COMMENT '留言檢舉\n0:檢舉失敗、1:檢舉成功',
  `custID` INT NOT NULL COMMENT '會員ID',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  `reportReason` VARCHAR(200) NOT NULL COMMENT '檢舉理由',
  `reportResult` TINYINT NOT NULL COMMENT '檢舉結果',
  PRIMARY KEY (`commentReportID`),
  constraint FK_CommentReport_cust_id FOREIGN KEY(`custID`) REFERENCES Customer(`idCustomer`),
  constraint FK_CommentReport_comment_id FOREIGN KEY(`commentReportID`) REFERENCES comment(`commentID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group4_db`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Admin` (
  `adminID` INT NOT NULL AUTO_INCREMENT COMMENT '管理者ID\n0:沒有權限、1:有權限',
  `adminAcco` VARCHAR(45) NOT NULL COMMENT '帳號',
  `adminPass` VARCHAR(45) NOT NULL COMMENT '密碼',
  `createdTime` TIMESTAMP NOT NULL COMMENT '創建時間',
  `adminAuthority` TINYINT(1) NOT NULL COMMENT '聊天室回覆權限\n',
  PRIMARY KEY (`adminID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`IngreType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`IngreType` (
  `idIngreType` INT NOT NULL COMMENT '類別編號',
  `typeName` VARCHAR(50) NOT NULL COMMENT '食材類別名稱',
  PRIMARY KEY (`idIngreType`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`Ingre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Ingre` (
  `idIngre` INT NOT NULL COMMENT '食材編號',
  `idIngreType` INT NOT NULL COMMENT '食材類別編號',
  `name` VARCHAR(25) NOT NULL COMMENT '食材品名\\n',
  `purPrice` DECIMAL(10,0) NOT NULL COMMENT '進貨價格',
  `price` DECIMAL(10,0) NOT NULL COMMENT '單價',
  `unit` VARCHAR(45) NOT NULL COMMENT '單位',
  `gran` INT NOT NULL COMMENT '克',
  `sell` INT NOT NULL COMMENT '銷售數量',
  `descrip` VARCHAR(2000) NOT NULL COMMENT '食品描述',
  `photo` LONGBLOB NOT NULL COMMENT '圖片',
  `launch` TINYINT NOT NULL COMMENT '上架狀態\\n0:未上架\\n1:上架',
  PRIMARY KEY (`idIngre`),
  constraint FK_Ingre_IngreType_id FOREIGN KEY(`idIngreType`) REFERENCES IngreType(`idIngreType`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Group4_db`.`TempOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Group4_db`.`Temporder` (
  `custID` INT AUTO_INCREMENT NOT NULL COMMENT '會員編號 (custID): int, not null',
  `ingreID` INT NOT NULL COMMENT '食材編號 (ingreID): int, not null',
  `orderQuan` INT NOT NULL COMMENT '購買數量 (orderQuan): int, not null',
  `price` INT NOT NULL COMMENT '單價 (price): int, not null',
  PRIMARY KEY (`custID`, `ingreID`),
  constraint FK_Temporder_id FOREIGN KEY(`ingreID`) REFERENCES Ingre(`idIngre`))
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;