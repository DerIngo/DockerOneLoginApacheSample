# DockerOneLoginApacheSample
Sample App for a Apache based Reverse Proxy with OneLogin for authentication

## Version 1
Reverse Proxy with a public page and a private page, only accessible for OneLogin users.

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
pen ``http://localhost`` in your browser.
You will find a simple page with three links:
*  ``http://localhost/public``
*  ``http://localhost/private``
*  ``http://localhost/index``

Open ``http://localhost/public`` and you will see the public page.
Open ``http://localhost/private`` and you will see the private page... NOT! Instead you'll should see a OneLogin login page and only after login you will see the private page.
Open ``http://localhost/index`` and you will see the <i>Start</i> page.
