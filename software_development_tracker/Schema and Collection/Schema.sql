CREATE TABLE Members (
    Id int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(max),
	Email nvarchar(max),
	Password nvarchar(max),
	CreatedOn DateTime,
	ModifiedOn DateTime
)

CREATE TABLE Project (
    Id int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(max),
	Description nvarchar(max),
	CreatedBy int,
	CreatedOn DateTime,
	ModifiedBy int,
	ModifiedOn DateTime
)

CREATE TABLE Task (
    Id int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(max),
	Description nvarchar(max),
	CreatedBy int,
	CreatedOn DateTime,
	ModifiedBy int,
	ModifiedOn DateTime,
	AssignedTo int FOREIGN KEY REFERENCES Members(Id),
	ReportTo int FOREIGN KEY REFERENCES Members(Id),
	StatusId int FOREIGN KEY REFERENCES SdtConfiguration(Id),
	IssueTypeId int FOREIGN KEY REFERENCES SdtConfiguration(Id),
	ProjectId int FOREIGN KEY REFERENCES Project(Id)
)

CREATE TABLE SdtConfiguration(
	Id int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(max),
	ConfigurationType nvarchar(max), --- Status/IssueType
)


CREATE TABLE ProjectUserMapping (
    Id int IDENTITY(1,1) PRIMARY KEY,
    UserId int,
	ProjectId int
)

