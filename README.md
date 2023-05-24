# pharmacy-comment
In this project, a basic architecture was created with spring boot
first call the 1st endpoint "{"username":"muco","password":"xxx"}" and get the token, then call the 2nd endpoint with the token.
1st endpoint-->http://localhost:8080/authenticate
2nd endpoint-->http://localhost:8080/pharmacy/getopenpharmacy?city=hamburg
