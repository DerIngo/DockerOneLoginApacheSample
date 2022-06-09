# DockerOneLoginApacheSample
Sample App for a Apache based Reverse Proxy with OneLogin for authentication.

## Version 7
Apache Server with SSL, so you can use your IP, instead of localhost, to get OneLogin working.

### Configuration - in and from OneLogin
In OneLogin Administrative page for your app collect this information:
* Client ID
* Client Secret
* Issuer URL

![OneLoginConfig](../images/OneLoginConfig.png)


In Configuration page add Redirect URI:
* https://<YOUR-IP>/private/redirect_uri


### Configuration
Open reverseproxy/conf/reverseproxy-ssl.conf and enter the configuration from OneLogin.<br>
If you have any trusted Certificates, for example from ZScaler, put the *.crt-Files into the reverseproxy/conf folder.

### Test
Open ``http://localhost`` in your browser. Must redirect to ``https://localhost``.
You will find a simple page with four links:
*  ``https://localhost/public``
*  ``https://localhost/private``
*  ``https://localhost/index``
*  ``https://localhost/showheaders``

Open ``https://localhost/public`` and you will see the public page.<br>
Open ``https://localhost/private`` and you will see an "Internal Server Error"<br>
Open ``https://localhost/index`` and you will see the <i>Start</i> page.<br>
Open ``https://localhost/showheaders`` and you will see an "Internal Server Error"<br>

Open ``http://<YOUR-IP>`` in your browser. Must redirect to ``https://<YOUR-IP>``.
You will find a simple page with four links:
*  ``https://<YOUR-IP>/public``
*  ``https://<YOUR-IP>/private``
*  ``https://<YOUR-IP>/index``
*  ``https://<YOUR-IP>/showheaders``

Open ``https://<YOUR-IP>/public`` and you will see the public page.<br>
Open ``https://<YOUR-IP>/private`` and you will see the private page... NOT! Instead you'll should see a OneLogin login page and only after login you will see the private page.<br>
Open ``https://<YOUR-IP>/index`` and you will see the <i>Start</i> page.<br>
Open ``https://<YOUR-IP>/showheaders`` and you will see the ShowHeades page (if you are logged in). This page shows all Headers.<br>
