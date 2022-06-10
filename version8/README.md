# DockerOneLoginApacheSample
Sample App for a Apache based Reverse Proxy with OneLogin for authentication.

## Version 8
LoadBalancer with mutual SSL to two backend servers.<br>
Server 1 is protected through mutual SSL.<br>
Server 2 is not protected.

![OneLoginConfig](../images/MutualSSL.png)


### Configuration
No configuration required, just start: ``docker-compose up``


### Test
Open ``http://localhost`` in your browser. Must redirect to ``https://localhost``.<br>
Open ``https://localhost`` and reload several times. You must see Public Page from server 1 and server 2 alternate.<br>
Open ``https://localhost:8091`` and you must see an error message from server 1.<br>
Open ``https://localhost:8092`` and you can see Public Page from server 2.<br>