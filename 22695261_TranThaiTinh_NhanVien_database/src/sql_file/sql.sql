USE [employee]
GO

/****** Object:  Table [dbo].[Employee]    Script Date: 13/10/2024 11:50:35 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Employee](
	[employee_id] [nvarchar](50) NOT NULL,
	[employee_surname] [nvarchar](50) NOT NULL,
	[employee_name] [nvarchar](50) NOT NULL,
	[employee_age] [int] NOT NULL,
	[employee_gender] [nvarchar](50) NOT NULL,
	[employee_salary] [float] NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


