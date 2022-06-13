FROM httpd:2.4
RUN apt update && apt install -y \
	libapache2-mod-auth-openidc \
	ca-certificates
RUN cp /usr/lib/apache2/modules/mod_auth_openidc.so /usr/local/apache2/modules/
RUN mv conf/httpd.conf conf/container_httpd.conf
RUN openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /usr/local/apache2/conf/server.key -out /usr/local/apache2/conf/server.crt -subj "/"
COPY ./conf/*.crt /usr/local/share/ca-certificates/
RUN update-ca-certificates
CMD ["httpd-foreground"]