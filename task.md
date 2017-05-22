#tasks
# /departments
* post
	* return 201 when create
	* try to save to db and can get that one from db;
	* contains url in location; --25 : 22

	* return 400 when field empty; --20 : 10
* get
	* return 200 when get all
	* try to get from db and can get all details -- 15 : 40


# /departments/did
* get
	* return 200 when get one
	* contains right details in response; 

	* return 404 when not exists --15 : 1
* update
 	* return 204 when update

	* return 400 when field empty --15
* delete
 	* return 204 when update --5

# /roles --11
* post
	* return 201 when create
	* try to save to db and can get that one from db;
	* contains url in location; --25

	* return 400 when field empty; --20
* get
	* return 200 when get all
	* try to get from db and can get all details -- 15


# /roles/rid
* get
	* return 200 when get one
	* contains right details in response; 

	* return 404 when not exists --15
* update
 	* return 204 when update

	* return 400 when field empty --15
* delete
 	* return 204 when update --5

# /employees --50
* post
	* return 201 when create
	* try to save to db and can get that one from db;
	* contains url in location; --15

	* return 400 when field empty, invalid; --20
* get
	* return 200 when get all
	* try to get from db and can get all details -- 20

# /employees/eid
* get
	* return 200 when get one
	* contains right details in response;

	* return 404 when not exists --15
* update
 	* return 204 when update

	* return 400 when field empty --15
* delete
 	* return 204 when update --5
