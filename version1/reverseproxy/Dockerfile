FROM httpd:2.4
RUN apt update && apt install -y \
	libapache2-mod-auth-openidc \
	ca-certificates
RUN cp /usr/lib/apache2/modules/mod_auth_openidc.so /usr/local/apache2/modules/
RUN mv conf/httpd.conf conf/container_httpd.conf
CMD ["httpd-foreground"]