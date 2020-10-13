#Bidding System

##Supported Apis

1.Add Bid
2.Close bid
3.Fetch running bids
4.Updated bid or quoting bid


##Secuirty

Uses data base authenticatio with encrypted passwords and roles assigned to each usser.

Eg: user1,password,user(role)
	admin1,password,admin(role)
	
Authentication supported is basic.

1.http://localhost:8080/v1/addBid  --->Add Build

{
    "itemCode":"A10007",
    "bidPrice":1333.33,
    "stepRate":2333,
    "bidStatus":"Running",
    "lastUpdatedBy":"Admin"

}

2.http://localhost:8080/v1/updatebid/1 -->Updated Build

 {
        "itemCode": "A10004",
        "bidPrice": 55555.33,
        "stepRate": 2333.0,
        "bidStatus": "Running",
        "updatedDate": null,
        "versionId": 0
 }
 
 3.http://localhost:8080/v1/closebid/17
 
 {
        "itemCode": "A10007",
        "bidPrice": 1333.33,
        "stepRate": 2333.0,
        "bidStatus": "OVER",
        "updatedDate": null,
        "versionId": 0
    }
 4.http://localhost:8080/v1/fetchBid?status=OVER   

	

	