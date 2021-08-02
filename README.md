# **Ticketing Service**

### **Setting up database**

In the project folder, you'll find a `run.sh` file, Run the following commands:

``chmod +x db-setup.sh`` to make the file executable

``sudo ./db-setup.sh`` to run the shell file that sets up the docker containers for postgresdb and the admin page.

Then go to the browser to visit localhost on port 8080 i.e http://localhost:8080/ to have access to pgadmin. Enter email ``admin@admin.com`` and password `secret` to log in.