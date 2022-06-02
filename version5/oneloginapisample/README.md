# OneLogin API Sample Project

## Configuration
Put your configuration in src/main/resources/application.properties<br>

## Run Sample
Just run TestMain.java<br>
The output should look like this:

```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Access Token: 9d126d73e99558742d9caffabc73b019d22a6dc922ea184b9bd5ca0cb8cda283
HTTP/1.1 201 Created
{"activated_at":"2022-06-02T11:00:33.456Z","email":null,"lastname":"Gilmore","manager_ad_id":null,"custom_attributes":{"my_role":null},"samaccountname":null,"group_id":null,"id":179010582,"comment":null,"last_login":null,"state":1,"preferred_locale_code":null,"firstname":"Happy","manager_user_id":null,"title":null,"created_at":"2022-06-02T11:00:33.458Z","password_changed_at":"2022-06-02T11:00:33.447Z","status":1,"directory_id":null,"distinguished_name":null,"external_id":null,"invitation_sent_at":null,"member_of":null,"phone":null,"updated_at":"2022-06-02T11:00:33.458Z","trusted_idp_id":null,"department":null,"invalid_login_attempts":0,"locked_until":null,"username":"happy.gilmore","role_ids":[],"userprincipalname":null,"company":null}
new User ID: 179010582
HTTP/1.1 200 OK
{"created_at":"2022-06-02T11:00:33.458Z","member_of":null,"updated_at":"2022-06-02T11:00:34.594Z","invitation_sent_at":null,"state":1,"username":"happy.gilmore","preferred_locale_code":null,"manager_ad_id":null,"comment":null,"distinguished_name":null,"email":null,"samaccountname":null,"external_id":null,"password_changed_at":"2022-06-02T11:00:33.447Z","status":1,"id":179010582,"invalid_login_attempts":0,"last_login":null,"phone":null,"title":null,"directory_id":null,"firstname":"Very Happy","group_id":null,"locked_until":null,"userprincipalname":null,"manager_user_id":null,"custom_attributes":{"my_role":null},"activated_at":"2022-06-02T11:00:33.456Z","trusted_idp_id":null,"role_ids":[],"lastname":"Gilmore","department":null,"company":null}
User ID: 179010582
Firstname: Very Happy
HTTP/1.1 200 OK
[{"id":179010582}]
HTTP/1.1 204 No Content
HTTP/1.1 204 No Content
```