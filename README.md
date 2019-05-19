# DPOOProject

The screen has to be 1080p resolution at least.

It's prefered to be executed with a Windows or Ubuntu OS due to MacOS Swing changes.

* To run the software you must have run the SQL Database file, creating the database.
* Then you will run the MainServer starting the Server, and then the MainCient to start the Client.

There's the option of using the ConfigurationCreation main in order to autogenerate a config.json, adding the setting you wish and the configuration of keys of the piano you prefer.

**Client**

Once the client has started a Frame of Login appears giving two options enter the username and password to login or register a new user. When you log in a main window appears showing 4 options:

* Piano: where you can play the piano.
    Here you have the option of recording and saving a song you play.
    To select song from the system or friends or mute the song selected.
    And also you can configure the keys you use to play the piano or exit to the main screen.
    - Default keys for the piano are written in uppercase -> So the user can only execute by default in uppercase.
* Social: where you can search and add friends. With the friendship code of other users, you can search them in Social window.
* Sing out: where you can log out of the actual user and you will be sent to the log in screen.
* Delete account: where you can delete the account and you will be sent to the log in screen.

Above these the alphanumeric code of the user you have log in with is shown.


**Server**

Once the server opens, 4 buttons are displayed:

* Register User: where the server administrator can add as many users as desired.
* Top 5 Songs: A window that shows a top of the 5 most listened songs.
* Song Manager: This window allows you to delete songs that are not from the server, add new ones and see what's in the entire system.
* User Graph: Graph that shows the activity of the different connections of users to the server.