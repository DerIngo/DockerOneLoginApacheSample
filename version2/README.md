# DockerOneLoginApacheSample
Sample App for a Apache based Reverse Proxy with OneLogin for authentication

## Version 2
Reverse Proxy with a public page. A private page and ShowHeaders, only accessible for OneLogin users.

### Configuration - in and from OneLogin
In OneLogin Administrative page for your app collect this information:
* Client ID
* Client Secret
* Issuer URL

<!-- wp:image {"id":383,"sizeSlug":"large","linkDestination":"none"} -->
<figure class="wp-block-image size-large"><img src="https://ingo.kaulbach.de/wp-content/uploads/2022/05/image-1024x768.png" alt="" class="wp-image-383"/></figure>
<!-- /wp:image -->

In Configuration page add Redirect URI:
* http://localhost/private/redirect_uri

### Configuration
Open reverseproxy/conf/reverseproxy.conf and enter the configuration from OneLogin

### Start
``docker-compose up``

### Test
Open ``http://localhost`` in your browser.
You will find a simple page with four links:
*  ``http://localhost/public``
*  ``http://localhost/private``
*  ``http://localhost/index``
*  ``http://localhost/showheaders``

Open ``http://localhost/public`` and you will see the public page.<br>
Open ``http://localhost/private`` and you will see the private page... NOT! Instead you'll should see a OneLogin login page and only after login you will see the private page.<br>
Open ``http://localhost/index`` and you will see the <i>Start</i> page.
Open ``http://localhost/private`` and you will see the ShowHeades page (if you are logged in). This page shows all Headers.<br>
