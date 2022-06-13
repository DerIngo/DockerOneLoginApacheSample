# DockerOneLoginApacheSample
Sample App for a Apache based Reverse Proxy with OneLogin for authentication TBD: and authorization<br>
UPDATE: The authorization can not be done this way, because OneLogin does not provide application roles.

## Version 9
Three networks:
* Internet
    + User
    + OneLogin
* DMZ
    + Reverse Proxy
    + 2 Public Server
* Intern
    + 2 Private Servers

An unauthenticated user has access to public servers through the Reverse Proxy.<br>
Only authenticated users have access to private servers.<br>
Only authenticated users with role 'user' have access application on private servers.<br>

![LoadBalancerReverseProxy](../images/version9.png)


## Version 9 - API
There is also an example for API calls in project oneloginjavaappsample -> Class TestMain.java<br>


## Configuration
### in and from OneLogin
In OneLogin Administrative page for your app collect this information:
* Client ID
* Client Secret
* Issuer URL

![OneLoginConfig](../images/OneLoginConfig.png)


In Configuration page add Redirect URI:
* http://<YOUR-IP\>/private/redirect_uri

Enable Self Registration: Users -> Self registration

In OneLogin Developers -> API Credentials page create API Access with 'Manage all' for your app and collect this information:
* API Client ID
* API Client Secret


### Configuration
Open reverseproxy/conf/reverseproxy-ssl.conf and enter the configuration from OneLogin.<br>
Open public/public_html/index.html and edit registration link.<br>
Open oneloginjavaappsample/conf/server.properties and edit anything, if required.<br>
<br>
For API-Example: Open oneloginjavaappsample/src/resources/application.properties and enter the API configuration from OneLogin.<br>

### Start
Bevor the first start the public network needs to be created:<br>
``docker network create public_network`` <br>
<br>
``docker-compose up``

### Test
Open ``https://<YOUR-IP>`` in your browser.<br>
You can see the public pages.<br>
Click on Login to access private pages. You have to login to OneLogin first.<br>
Click on Registration to test Self Registration for your application.