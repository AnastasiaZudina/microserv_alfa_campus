FROM postgres:latest
COPY init.sql /docker-entrypoint-initdb.d/
EXPOSE 5001
ENV POSTGRES_PASSWORD testuser
ENV POSTGRES_USER testpass 
ENV POSTGRES_DB users
 

