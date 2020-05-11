Create Table Invoice_header(
	Invoice_ID serial Primary key,
	Invoice_to varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	subtotal real NOT NULL,
	discount real NOT Null,
	total real NOT NULL
);

Create table Invoice_details(
	detail_id serial primary key,
	Invoice_ID serial,
	Product_name varchar(50) NOT NULL,
	qty integer NOT NULL,
	total_price real ,
	FOREIGN KEY (Invoice_ID) REFERENCES invoice_header(Invoice_ID)
);

{
	"invoice_to":"Head Honcho",
	"email": "hhoncho@gmail.com",
	"subtotal": 500,
	"discount": 100,
	"total": 400,
	"invoice_details": [
		{
			"product_name":"NPK Fertilizer super",
			"qty": 1,
			"total_price": 250
		},
		{
			"product_name":"shovel",
			"qty": 1,
			"total_price": 10
		},
		{
			"product_name":"banana non-organic full chemical",
			"qty":1,
			"total_price": 240
		}
	]
}