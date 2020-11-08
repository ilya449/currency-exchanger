#currency-exchanger test task

Implemented simple user interface.

Only authenticated user with "ADMIN" role can change and update currencies rates. After his work is finished, admin can logout.

Other users don't need to login, they can calculate currencies rate for changing. User have to input currency name, choose "sell" or "buy" operation type, choose currency they will pay and press "Calculate" button. And there will be shown a message about amount they will receive or pay.

Link, where we are getting rates can be changed in 'application.properties' file. Also, there can be added or deleted some currencies. Default admin login and password for testing: "admin", "admin";